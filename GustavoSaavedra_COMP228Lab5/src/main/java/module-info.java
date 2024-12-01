module com.saavedra.gustavosaavedra_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Allow reflection on the exercise1 package for JavaFX
    opens exercise1 to javafx.graphics;

    // Export exercise1 package if needed outside the module
    exports exercise1;
}
