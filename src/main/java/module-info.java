module com.ksr.ksr2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ksr.ksr2 to javafx.fxml;
    exports com.ksr.ksr2;
}