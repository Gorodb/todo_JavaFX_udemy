package com.rvakazov.todoapp.controller;

import com.rvakazov.todoapp.dto.TaskDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TaskCardController {
    public Label taskName;
    public Label taskTimeStamp;
    public Label taskStatus;

    private TaskDTO task;

    public void handleViewTask(ActionEvent actionEvent) {
        System.out.println("Viewing task: " + taskName.getText());
        showViewTaskDialog(task);
    }

    private void showViewTaskDialog(TaskDTO task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rvakazov/todoapp/task_view_dialog.fxml"));
            VBox dialogPane = loader.load();
            TaskViewDialogController dialogController = loader.getController();
            dialogController.setTaskDetails(task, this);
            Stage dialogStage = new Stage();
            dialogStage.setTitle(taskName.getText());
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(dialogPane);
            // adding custom css to specific stage
            String css = Objects.requireNonNull(this.getClass().getResource("/com/rvakazov/todoapp/viewDialogStyles.css")).toExternalForm();
            scene.getStylesheets().add(css);

            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTaskDetails(TaskDTO task) {
        this.task = task;
        taskName.setText(task.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, dd.MM.yyyy");
        taskTimeStamp.setText(task.getDateAdded().format(formatter));
        taskStatus.setText(task.getStatus());
        applyStatusColor(task.getStatus());
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
