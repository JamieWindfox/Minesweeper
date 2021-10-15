package com.example.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.Random;

public class GameHandler {


    private static MineField mineField;
    private static boolean gameLost;
    private static boolean gameWon;


    public static void createGame(MineField field) {
        mineField = field;
        field.generateMineField();
        gameLost = false;
        gameWon = false;
    }

    private static void flipAllTiles() {
        mineField.flipAllTiles();
    }


    public static void winGame() {
        flipAllTiles();
    }

    public static void loseGame() {
        flipAllTiles();
    }
}
