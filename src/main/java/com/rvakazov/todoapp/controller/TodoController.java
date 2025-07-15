package com.rvakazov.todoapp.controller;

import com.rvakazov.todoapp.dto.TaskDTO;
import com.rvakazov.todoapp.managers.TaskList;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TodoController {
    @FXML
    public MFXTextField taskTitle;
    @FXML
    public MFXComboBox<String> statusComboBox;
    @FXML
    public VBox taskListVBox;

    private TaskList taskList;

    public void initialize() {
        taskList = new TaskList();
        statusComboBox.getItems().addAll("All", "ToDo", "InProgress", "Done");
        statusComboBox.setValue("All");
        statusComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                filterTaskByStatus(newValue);
            }
        });

        redrawTaskList();
//        addTask("Create a javaFX Project", "First task description", LocalDateTime.now().minusMinutes(4), "InProgress");
//        addTask("Create a SpringBoot Project", "A new task description", LocalDateTime.now().minusMinutes(3), "ToDo");
//        addTask("Learn React", "A new task description", LocalDateTime.now().minusMinutes(15), "Done");
    }

    @FXML
    public void handleAddTask(ActionEvent actionEvent) {
        showAddTaskDialog();
    }

    private void filterTaskByStatus(String status) {
        taskListVBox.getChildren().clear();
        List<TaskDTO> filteredTasks;

        if ("All".equals(status)) {
            filteredTasks = taskList.getTasks();
        } else {
            filteredTasks = taskList.getTasks().stream().filter(
                    task -> task.getStatus().equals(status)).collect(Collectors.toList()
            );
        }

        for(TaskDTO task: filteredTasks) {
            displayTask(task);
        }
    }

    private void showAddTaskDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rvakazov/todoapp/task_add_dialog.fxml"));
            VBox dialogPane = loader.load();
            TaskAddDialogController dialogController = loader.getController();
            dialogController.setMainController(this);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add new task");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(dialogPane);
            // adding custom css to specific stage
            String css = Objects.requireNonNull(this.getClass().getResource("/com/rvakazov/todoapp/addTaskStyles.css")).toExternalForm();
            scene.getStylesheets().add(css);

            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTaskFromDialog(String title, String description) {
        addTask(title, description, LocalDateTime.now(), "ToDo");
    }

    private void addTask(String title, String description, LocalDateTime dateAdded, String status) {
        TaskDTO newTask = new TaskDTO(title, description, dateAdded, status);
        taskList.addTask(newTask);
        redrawTaskList();
//        displayTask(newTask);
    }

    private void displayTask(TaskDTO task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rvakazov/todoapp/task-card.fxml"));
            HBox taskCard = loader.load();
            TaskCardController controller = loader.getController();
            controller.setTaskDetails(task, this);

            taskListVBox.getChildren().add(taskCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redrawTaskList() {
        taskListVBox.getChildren().clear();
        for(TaskDTO task: taskList.getTasks()) {
            displayTask(task);
        }
        statusComboBox.setValue("All");
    }
}