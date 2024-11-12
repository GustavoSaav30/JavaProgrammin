module com.saavedra.gustavosaavedra_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports exercise1;

    exports com.saavedra.gustavosaavedra_comp228lab4;
    opens com.saavedra.gustavosaavedra_comp228lab4 to javafx.fxml;
}
