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
            dropdownCourses.getItems().addAll("Economics", "Finance", "Marketing");
        });

        dropdownCourses.setOnAction(e -> {
            String course = dropdownCourses.getValue();
            if (course != null && !selectedSubjects.contains(course)) {
                selectedSubjects.add(course);
                listCourses.getItems().add(course);
            }
        });

        inputForm.addRow(0, labelName, textName);
        inputForm.add(labelAddress, 0, 1);
        inputForm.add(textAddress, 1, 1);
        inputForm.add(labelProvince, 0, 2);
        inputForm.add(textProvince, 1, 2);
        inputForm.add(labelCity, 0, 3);
        inputForm.add(textCity, 1, 3);
        inputForm.add(labelPostalCode, 0, 4);
        inputForm.add(textPostalCode, 1, 4);
        inputForm.add(labelPhone, 0, 5);
        inputForm.add(textPhone, 1, 5);
        inputForm.add(labelEmail, 0, 6);
        inputForm.add(textEmail, 1, 6);
        inputForm.add(checkboxCouncil, 2, 1);
        inputForm.add(boxMajor, 3, 0);
        inputForm.add(checkboxVolunteer, 2, 5);
        inputForm.add(boxCourses, 3, 1, 1, 6);

    }
}
