package exercise1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private Connection conn;
    private ComboBox<Player> playerComboBox;
    private ComboBox<Game> gameComboBox;
    private ListView<String> reportListView;

    @Override
    public void start(Stage primaryStage) {
        try {
            conn = DbCon.connection();
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Connection Error", e.getMessage());
            return;
        }

        TabPane tabPane = new TabPane();

        Tab createGameTab = new Tab("Create Game", createGameUI());
        Tab addPlayerTab = new Tab("Add Player", addPlayerUI());
        Tab playerGameTab = new Tab("Add Player Game Info", playerGameUI());
        Tab reportTab = new Tab("Reports", reportUI());
        Tab updatePlayerTab = new Tab("Update Player", updatePlayerUI());

        createGameTab.setClosable(false);
        addPlayerTab.setClosable(false);
        playerGameTab.setClosable(false);
        reportTab.setClosable(false);
        updatePlayerTab.setClosable(false);

        tabPane.getTabs().addAll(createGameTab, addPlayerTab, playerGameTab, reportTab, updatePlayerTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Game Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGameUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label gameNameLabel = new Label("Game Name:");
        TextField gameNameField = new TextField();
        Button createGameButton = new Button("Create Game");

        createGameButton.setOnAction(e -> {
            String gameName = gameNameField.getText();
            if (gameName.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Game name is required.");
                return;
            }
            createGame(gameName);
        });

        gridPane.add(gameNameLabel, 0, 0);
        gridPane.add(gameNameField, 1, 0);
        gridPane.add(createGameButton, 1, 1);

        return gridPane;
    }

    private GridPane addPlayerUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        Label postalCodeLabel = new Label("Postal Code:");
        TextField postalCodeField = new TextField();
        Label provinceLabel = new Label("Province:");
        TextField provinceField = new TextField();
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        Button addPlayerButton = new Button("Add Player");

        addPlayerButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String province = provinceField.getText();
            String phone = phoneField.getText();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "First name and last name are required.");
                return;
            }
            addPlayer(firstName, lastName, address, postalCode, province, phone);
        });

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(addressField, 1, 2);
        gridPane.add(postalCodeLabel, 0, 3);
        gridPane.add(postalCodeField, 1, 3);
        gridPane.add(provinceLabel, 0, 4);
        gridPane.add(provinceField, 1, 4);
        gridPane.add(phoneLabel, 0, 5);
        gridPane.add(phoneField, 1, 5);
        gridPane.add(addPlayerButton, 1, 6);

        return gridPane;
    }

    private VBox playerGameUI() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        playerComboBox = new ComboBox<>();
        gameComboBox = new ComboBox<>();
        refreshPlayerAndGameOptions();

        DatePicker playingDatePicker = new DatePicker();
        playingDatePicker.setValue(LocalDate.now());

        TextField scoreField = new TextField();
        scoreField.setPrefWidth(100); // Set a reasonable width for the score field
        scoreField.setMaxWidth(100);  // Ensures it does not stretch beyond 100px

        Button addPlayerGameButton = new Button("Add Player Game Info");
        Button refreshButton = new Button("Refresh");

        addPlayerGameButton.setOnAction(e -> {
            Player player = playerComboBox.getValue();
            Game game = gameComboBox.getValue();
            LocalDate playingDate = playingDatePicker.getValue();
            String scoreText = scoreField.getText();

            if (player == null || game == null || playingDate == null || scoreText.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled.");
                return;
            }

            try {
                int score = Integer.parseInt(scoreText);
                addPlayerGame(player.getPlayerId(), game.getGameId(), Date.valueOf(playingDate), score);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Score must be a number.");
            }
        });

        refreshButton.setOnAction(e -> refreshPlayerAndGameOptions());

        vbox.getChildren().addAll(
                new Label("Player:"), playerComboBox,
                new Label("Game:"), gameComboBox,
                new Label("Playing Date:"), playingDatePicker,
                new Label("Score:"), scoreField,
                addPlayerGameButton, refreshButton
        );

        return vbox;
    }

    private VBox reportUI() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        ComboBox<Player> playerReportComboBox = new ComboBox<>();
        reportListView = new ListView<>();
        Button refreshButton = new Button("Refresh");

        refreshPlayerList(playerReportComboBox);

        playerReportComboBox.setOnAction(e -> {
            Player player = playerReportComboBox.getValue();
            if (player != null) {
                loadPlayerGameReport(player.getPlayerId());
            }
        });

        refreshButton.setOnAction(e -> refreshPlayerList(playerReportComboBox));

        vbox.getChildren().addAll(
                new Label("Select Player:"), playerReportComboBox,
                reportListView, refreshButton
        );

        return vbox;
    }

    private GridPane updatePlayerUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ComboBox<Player> playerComboBox = new ComboBox<>();
        refreshPlayerList(playerComboBox);

        TextField addressField = new TextField();
        TextField postalCodeField = new TextField();
        TextField phoneField = new TextField();

        playerComboBox.setOnAction(e -> {
            Player selectedPlayer = playerComboBox.getValue();
            if (selectedPlayer != null) {
                loadPlayerDetails(selectedPlayer.getPlayerId(), addressField, postalCodeField, phoneField);
            }
        });

        Button updateButton = new Button("Update Player");

        updateButton.setOnAction(e -> {
            Player player = playerComboBox.getValue();
            if (player == null) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "No player selected.");
                return;
            }

            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String phone = phoneField.getText();

            updatePlayer(player.getPlayerId(), address, postalCode, phone);
        });

        gridPane.add(new Label("Select Player:"), 0, 0);
        gridPane.add(playerComboBox, 1, 0);
        gridPane.add(new Label("Address:"), 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(new Label("Postal Code:"), 0, 2);
        gridPane.add(postalCodeField, 1, 2);
        gridPane.add(new Label("Phone Number:"), 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(updateButton, 1, 4);

        return gridPane;
    }

    private void createGame(String gameName) {
        String sql = "INSERT INTO Game (game_id, game_title) VALUES (seq_game_id.NEXTVAL, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gameName);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Game created successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void addPlayer(String firstName, String lastName, String address, String postalCode, String province, String phone) {
        String sql = "INSERT INTO Player (player_id, first_name, last_name, address, postal_code, province, phone_number) VALUES (seq_player_id.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setString(4, postalCode);
            stmt.setString(5, province);
            stmt.setString(6, phone);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Player added successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void addPlayerGame(int playerId, int gameId, Date playingDate, int score) {
        String sql = "INSERT INTO PlayerAndGame (player_game_id, game_id, player_id, playing_date, score) " +
                "VALUES (seq_player_game_id.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gameId);
            stmt.setInt(2, playerId);
            stmt.setDate(3, playingDate);
            stmt.setInt(4, score);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Player game info added successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void refreshPlayerAndGameOptions() {
        refreshPlayerList(playerComboBox);
        refreshGameList(gameComboBox);
    }

    private void refreshPlayerList(ComboBox<Player> comboBox) {
        ObservableList<Player> players = FXCollections.observableArrayList(loadPlayers());
        comboBox.setItems(players);
    }

    private void refreshGameList(ComboBox<Game> comboBox) {
        ObservableList<Game> games = FXCollections.observableArrayList(loadGames());
        comboBox.setItems(games);
    }

    private List<Player> loadPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT player_id, first_name, last_name FROM Player";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("player_id"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
        return players;
    }

    private List<Game> loadGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT game_id, game_title FROM Game";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                games.add(new Game(rs.getInt("game_id"), rs.getString("game_title")));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
        return games;
    }

    private void loadPlayerDetails(int playerId, TextField addressField, TextField postalCodeField, TextField phoneField) {
        String sql = "SELECT address, postal_code, phone_number FROM Player WHERE player_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                addressField.setText(rs.getString("address"));
                postalCodeField.setText(rs.getString("postal_code"));
                phoneField.setText(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void updatePlayer(int playerId, String address, String postalCode, String phone) {
        String sql = "UPDATE Player SET address = ?, postal_code = ?, phone_number = ? WHERE player_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address);
            stmt.setString(2, postalCode);
            stmt.setString(3, phone);
            stmt.setInt(4, playerId);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Player updated successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void loadPlayerGameReport(int playerId) {
        String sql = "SELECT g.game_title, pg.playing_date, pg.score " +
                "FROM PlayerAndGame pg " +
                "JOIN Game g ON pg.game_id = g.game_id " +
                "WHERE pg.player_id = ?";
        ObservableList<String> reportItems = FXCollections.observableArrayList();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String report = String.format("Game: %s | Date: %s | Score: %d",
                        rs.getString("game_title"), rs.getDate("playing_date"), rs.getInt("score"));
                reportItems.add(report);
            }
            reportListView.setItems(reportItems);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Player {
    private final int playerId;
    private final String firstName;
    private final String lastName;

    public Player(int playerId, String firstName, String lastName) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Game {
    private final int gameId;
    private final String gameName;

    public Game(int gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return gameName;
    }
}
