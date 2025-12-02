module com.example.dataeditor2025 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dataeditor2025 to javafx.fxml;
    exports com.example.dataeditor2025;
}