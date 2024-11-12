module com.saavedra.gustavosaavedra_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.saavedra.gustavosaavedra_comp228lab4 to javafx.fxml;
    exports com.saavedra.gustavosaavedra_comp228lab4;
}