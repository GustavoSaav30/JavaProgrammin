package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainApp extends Application {

    private Connection conn;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Connect to the database
            conn = db.connection();
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Connection Error", e.getMessage());
            return;
        }

        // Create the UI
        TabPane tabPane = new TabPane();

        Tab createGameTab = new Tab("Create Game", createGameUI());
        Tab addPlayerTab = new Tab("Add Player", addPlayerUI());
        Tab updatePlayerTab = new Tab("Update Player Info", updatePlayerUI());

        createGameTab.setClosable(false);
        addPlayerTab.setClosable(false);
        updatePlayerTab.setClosable(false);

        tabPane.getTabs().addAll(createGameTab, addPlayerTab, updatePlayerTab);

        Scene scene = new Scene(tabPane, 600, 400);
        primaryStage.setTitle("Game Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGameUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label titleLabel = new Label("Game Title:");
        TextField titleField = new TextField();
        Button createButton = new Button("Create Game");

        createButton.setOnAction(e -> {
            String title = titleField.getText();
            if (title.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Game title is required.");
                return;
            }
            createGame(title);
        });

        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleField, 1, 0);
        gridPane.add(createButton, 1, 1);

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
        Button addButton = new Button("Add Player");

        addButton.setOnAction(e -> {
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
        gridPane.add(addButton, 1, 6);

        return gridPane;
    }

    private GridPane updatePlayerUI() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label playerIdLabel = new Label("Player ID:");
        TextField playerIdField = new TextField();
        Label addressLabel = new Label("New Address:");
        TextField addressField = new TextField();
        Label postalCodeLabel = new Label("New Postal Code:");
        TextField postalCodeField = new TextField();
        Label phoneLabel = new Label("New Phone Number:");
        TextField phoneField = new TextField();
        Button updateButton = new Button("Update Player");

        updateButton.setOnAction(e -> {
            String playerIdText = playerIdField.getText();
            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String phone = phoneField.getText();

            if (playerIdText.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Player ID is required.");
                return;
            }

            try {
                int playerId = Integer.parseInt(playerIdText);
                updatePlayer(playerId, address, postalCode, phone);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Player ID must be a number.");
            }
        });

        gridPane.add(playerIdLabel, 0, 0);
        gridPane.add(playerIdField, 1, 0);
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(postalCodeLabel, 0, 2);
        gridPane.add(postalCodeField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(updateButton, 1, 4);

        return gridPane;
    }

    private void createGame(String title) {
        String sql = "INSERT INTO Game (game_id, game_title) VALUES (seq_game_id.NEXTVAL, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Game created successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void addPlayer(String firstName, String lastName, String address, String postalCode, String province, String phone) {
        String sql = "INSERT INTO Player (player_id, first_name, last_name, address, postal_code, province, phone_number) " +
                "VALUES (seq_player_id.NEXTVAL, ?, ?, ?, ?, ?, ?)";
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

    private void updatePlayer(int playerId, String address, String postalCode, String phone) {
        String sql = "UPDATE Player SET address = ?, postal_code = ?, phone_number = ? WHERE player_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address);
            stmt.setString(2, postalCode);
            stmt.setString(3, phone);
            stmt.setInt(4, playerId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Player updated successfully.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Not Found", "No player found with the given ID.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
