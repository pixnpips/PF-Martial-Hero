module com.example.pfmartialhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


//    opens com.example.pfmartialhero to javafx.fxml;
//    exports com.example.pfmartialhero;
    opens View to javafx.fxml;
    exports View;
    opens Controller to javafx.fxml;
    exports Controller;
}