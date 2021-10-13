package com.example.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesweeperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        createGame(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void createGame(Stage stage) {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        GridPane topPane = new GridPane();
        Label topLabel = new Label("Top Pane");
        topPane.add(topLabel, 0, 0);
        layout.add(topPane, 0, 0);

        GridPane gamePane = new GridPane();
        Label gameLabel = new Label("Game Pane");
        gamePane.add(gameLabel, 0, 0);
        layout.add(gamePane, 0, 1);

        GridPane bottomPane = new GridPane();
        Label bottomLabel = new Label("Bottom Pane");
        bottomPane.add(bottomLabel, 0, 0);
        layout.add(bottomPane, 0, 2);

        Scene scene = new Scene(layout, 400, 400);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }
}