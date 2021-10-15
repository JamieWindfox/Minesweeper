package com.example.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesweeperApplication extends Application {

    private final static int FIELD_WIDTH_TILES = 10;
    private final static int FIELD_HEIGHT_TILES = 10;
    private final static int TILE_SIZE_PIXEL = 25;
    private final static int STAGE_HEIGHT_PIXEL = FIELD_HEIGHT_TILES * TILE_SIZE_PIXEL + 110;
    private final static int STAGE_WIDTH_PIXEL = FIELD_WIDTH_TILES * TILE_SIZE_PIXEL + 34;
    private final static int MINE_COUNT = 10;

    private static Stage primaryStage;
    private static MineField mineField;
    private static Label statusLabel;
    private static Button restartButton;
    private static GridPane buttonPane;
    private static  GridPane statusPane;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Minesweeper");
        startGame();

    }

    public static void startGame() {
        GridPane layout = new GridPane();
        layout.setDisable(true);
        layout.setHgap(TILE_SIZE_PIXEL);
        layout.setPadding(new Insets(TILE_SIZE_PIXEL, TILE_SIZE_PIXEL, TILE_SIZE_PIXEL, TILE_SIZE_PIXEL));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(createTopPane(), createMiddlePane(), createBottomPane());

        Scene scene = new Scene(layout, STAGE_WIDTH_PIXEL, STAGE_HEIGHT_PIXEL);
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            startGame();
        }
        layout.setDisable(false);
    }

    private static GridPane createTopPane() {
        buttonPane = new GridPane();
        restartButton = new Button("Restart");
        restartButton.setOnMouseClicked(event -> {
            startGame();
        });

        GridPane.setConstraints(buttonPane, 0, 0);
        GridPane.setConstraints(restartButton, 0, 0);

        buttonPane.getChildren().add(restartButton);
        return buttonPane;
    }

    private static GridPane createMiddlePane() {
        mineField = new MineField(FIELD_WIDTH_TILES, FIELD_HEIGHT_TILES, TILE_SIZE_PIXEL, MINE_COUNT);
        GridPane.setConstraints(mineField, 0, 1);
        mineField.setVgap(2);
        mineField.setHgap(2);

        GameHandler.createGame(mineField);
        return mineField;
    }

    private static GridPane createBottomPane() {
        statusPane = new GridPane();
        statusLabel = new Label("");

        GridPane.setConstraints(statusPane, 0, 2);
        GridPane.setConstraints(statusLabel, 0, 2);

        statusPane.getChildren().add(statusLabel);
        return statusPane;
    }

    public static void finishGame(boolean wonGame) {
        if(wonGame == true) {
            statusLabel.setText("Congratulations, you won the game!");
        }
        else {
            statusLabel.setText("You stepped into dogs crap. Game Over");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}