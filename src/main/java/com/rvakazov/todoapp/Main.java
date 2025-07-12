package com.rvakazov.todoapp;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal(); // any component will have material design
        Parent root = FXMLLoader.load(getClass().getResource("/com/rvakazov/todoapp/todo.fxml"));
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("/com/rvakazov/todoapp/styles.css")).toExternalForm();
        scene.getStylesheets().add(css);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Todo App");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setMaxHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}