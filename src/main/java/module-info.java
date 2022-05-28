module com.ksr.ksr2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ksr.ksr2 to javafx.fxml;
    exports com.ksr.ksr2;
    exports com.ksr.ksr2.controllers;
    opens com.ksr.ksr2.controllers to javafx.fxml;
}