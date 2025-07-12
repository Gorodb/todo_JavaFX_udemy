package com.rvakazov.todoapp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskCardController {
    public Label taskName;
    public Label taskTimeStamp;
    public Label taskStatus;

    public void handleViewTask(ActionEvent actionEvent) {
        System.out.println("Viewing task: " + taskName.getText());
        // TODO: add viewing task logic
    }

    public void setTaskDetails(String name, LocalDateTime timestamp, String status) {
        taskName.setText(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, dd.MM.yyyy");
        taskTimeStamp.setText(timestamp.format(formatter));
        taskStatus.setText(status);
        applyStatusColor(status);
    }

    private void applyStatusColor(String status) {
        switch (status) {
            case "ToDo":
                taskStatus.setStyle("-fx-text-fill: grey;");
                break;
            case "InProgress":
                taskStatus.setStyle("-fx-text-fill: orange;");
                break;
            case "Done":
                taskStatus.setStyle("-fx-text-fill: green;");
                break;
            default:
                taskStatus.setStyle("-fx-text-fill: blue;");
                break;
        }
    }
}
