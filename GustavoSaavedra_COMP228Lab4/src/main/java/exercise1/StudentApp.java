package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashSet;

public class StudentApp extends Application {

    private final HashSet<String> selectedSubjects = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) {
        mainWindow.setTitle("Student Registration");

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(5));

        GridPane inputForm = new GridPane();
        inputForm.setPadding(new Insets(5));
        inputForm.setHgap(5);
        inputForm.setVgap(5);

        for (int i = 0; i < 7; i++) {
            RowConstraints formRow = new RowConstraints();
            formRow.setMinHeight(40);
            inputForm.getRowConstraints().add(formRow);
        }

        TextField textName = new TextField();
        Label labelName = new Label("Name:");

        TextField textAddress = new TextField();
        Label labelAddress = new Label("Address:");

        TextField textCity = new TextField();
        Label labelCity = new Label("City:");

        TextField textProvince = new TextField();
        Label labelProvince = new Label("Province:");

        TextField textPostalCode = new TextField();
        Label labelPostalCode = new Label("Postal Code:");

        TextField textPhone = new TextField();
        Label labelPhone = new Label("Phone Number:");

        TextField textEmail = new TextField();
        Label labelEmail = new Label("Email:");

        CheckBox checkboxCouncil = new CheckBox("Student Council");
        CheckBox checkboxVolunteer = new CheckBox("Volunteer Work");

        ToggleGroup groupMajor = new ToggleGroup();
        RadioButton radioCS = new RadioButton("Computer Science");
        RadioButton radioBusiness = new RadioButton("Business");
        radioCS.setToggleGroup(groupMajor);
        radioBusiness.setToggleGroup(groupMajor);
        HBox boxMajor = new HBox(5, radioCS, radioBusiness);

        ComboBox<String> dropdownCourses = new ComboBox<>();
        ListView<String> listCourses = new ListView<>();
        listCourses.setPrefHeight(150);
        VBox boxCourses = new VBox(5, dropdownCourses, listCourses);

        radioCS.setOnAction(e -> {
            dropdownCourses.getItems().clear();
            dropdownCourses.getItems().addAll("Python", "C#", "Java");
        });

        radioBusiness.setOnAction(e -> {
            dropdownCourses.getItems().clear();
            dropdownCourses.getItems().addAll("Business 1", "Business 2", "Business 3");
        });

        dropdownCourses.setOnAction(e -> {
            String course = dropdownCourses.getValue();
            if (course != null && !selectedSubjects.contains(course)) {
                selectedSubjects.add(course);
                listCourses.getItems().add(course);
            }
        });

        inputForm.addRow(0, labelName, textName);
        inputForm.addRow(1, labelAddress, textAddress);
        inputForm.addRow(2, labelProvince, textProvince);
        inputForm.addRow(3, labelCity, textCity);
        inputForm.addRow(4, labelPostalCode, textPostalCode);
        inputForm.addRow(5, labelPhone, textPhone);
        inputForm.addRow(6, labelEmail, textEmail);
        inputForm.add(checkboxCouncil, 2, 1);
        inputForm.add(boxMajor, 3, 0);
        inputForm.add(checkboxVolunteer, 2, 5);
        inputForm.add(boxCourses, 3, 1, 1, 6);


        TextArea areaDisplay = new TextArea();
        areaDisplay.setEditable(false);
        areaDisplay.setWrapText(false);

        // Adjust the size for better readability and scrolling
        areaDisplay.setPrefSize(1200, 800);

        ScrollPane paneScroll = new ScrollPane(areaDisplay);
        paneScroll.setPrefHeight(200);
        paneScroll.setPrefWidth(600);
        paneScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        paneScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Button buttonShow = new Button("Display");
        buttonShow.setOnAction(e -> {
            StringBuilder studentInfo = new StringBuilder();
            studentInfo.append(textName.getText()).append(", ");
            studentInfo.append(textAddress.getText()).append(", ");
            studentInfo.append(textCity.getText()).append(", ");
            studentInfo.append(textProvince.getText()).append(", ");
            studentInfo.append(textPostalCode.getText()).append(", ");
            studentInfo.append(textPhone.getText()).append(", ");
            studentInfo.append(textEmail.getText()).append("\n");

            studentInfo.append("Major: ");
            if (radioCS.isSelected()) {
                studentInfo.append("Computer Science\n");
            } else if (radioBusiness.isSelected()) {
                studentInfo.append("Business\n");
            }

            studentInfo.append("Courses: ").append(String.join(", ", selectedSubjects)).append("\n");

            studentInfo.append("Activities: ");
            if (checkboxCouncil.isSelected()) studentInfo.append("Student Council ");
            if (checkboxVolunteer.isSelected()) studentInfo.append("Volunteer Work");

            areaDisplay.setText(studentInfo.toString());
        });

        HBox boxButton = new HBox(buttonShow);
        boxButton.setAlignment(Pos.CENTER);
        boxButton.setPadding(new Insets(5));

        VBox boxDisplay = new VBox(5, boxButton, paneScroll);
        boxDisplay.setPadding(new Insets(5));

        layout.setCenter(inputForm);
        layout.setBottom(boxDisplay);

        mainWindow.setScene(new Scene(layout, 700, 650));
        mainWindow.show();
    }
}
