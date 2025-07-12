module com.rvakazov.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.rvakazov.todoapp to javafx.fxml;
    exports com.rvakazov.todoapp;
    exports com.rvakazov.todoapp.controller;
    opens com.rvakazov.todoapp.controller to javafx.fxml;
}