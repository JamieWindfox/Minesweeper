package com.example.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Minesweeper");
        startGame();

    }

    public static void startGame() {
        GridPane layout = new GridPane();
        layout.setHgap(TILE_SIZE_PIXEL);
        layout.setPadding(new Insets(TILE_SIZE_PIXEL, TILE_SIZE_PIXEL, TILE_SIZE_PIXEL, TILE_SIZE_PIXEL));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(createTopPane(), createMiddlePane(), createBottomPane());

        GameHandler.setTotalEmptyTilesCount(FIELD_HEIGHT_TILES * FIELD_WIDTH_TILES - MINE_COUNT);

        Scene scene = new Scene(layout, STAGE_WIDTH_PIXEL, STAGE_HEIGHT_PIXEL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static GridPane createTopPane() {
        GridPane pane = new GridPane();
        Label label = new Label("top row");

        GridPane.setConstraints(pane, 0, 0);
        GridPane.setConstraints(label, 0, 0);

        pane.getChildren().add(label);
        return pane;
    }

    private static GridPane createMiddlePane() {
        GridPane pane = new GridPane();
        GridPane.setConstraints(pane, 0, 1);
        pane.setVgap(2);
        pane.setHgap(2);

        GameHandler.createGame(FIELD_WIDTH_TILES, FIELD_HEIGHT_TILES, TILE_SIZE_PIXEL, pane, MINE_COUNT);
        return pane;
    }

    private static GridPane createBottomPane() {
        GridPane pane = new GridPane();
        Label label = new Label("bottom row");

        GridPane.setConstraints(pane, 0, 2);
        GridPane.setConstraints(label, 0, 2);

        pane.getChildren().add(label);
        return pane;
    }

    public static void main(String[] args) {
        launch();
    }
}