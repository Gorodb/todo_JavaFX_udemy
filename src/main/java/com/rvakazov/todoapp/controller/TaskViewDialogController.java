package com.rvakazov.todoapp.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskViewDialogController {
    public MFXTextField taskTitleField;
    public TextArea taskDescriptionField;
    public MFXComboBox statusComboBox;
    public MFXTextField commentField;
    public VBox commentList;

    public void handleAddComment(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
        closeDialog();
    }

    public void handleUpdate(ActionEvent actionEvent) {
    }

    private void closeDialog() {
        // casting gotten window into a stage and closing it
        Stage stage = (Stage) taskTitleField.getScene().getWindow();
        stage.close();
    }
}
