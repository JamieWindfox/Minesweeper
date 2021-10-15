package com.example.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameHandler {
    public static int totalEmptyTilesCount;
    public static int flippedEmptyTilesCount;

    private static GridPane mineField;
    private static boolean mineExploded;

    public static void flipEmptyTile() {
        flippedEmptyTilesCount++;

        if(mineExploded == false && flippedEmptyTilesCount >= totalEmptyTilesCount) {
            System.out.println("You won the game!");
            flippedEmptyTilesCount = 0;
            MinesweeperApplication.startGame();
        }
    }

    public static void setTotalEmptyTilesCount(int count) {
        totalEmptyTilesCount = count;
    }

    public static void createGame(int FIELD_WIDTH_TILES, int FIELD_HEIGHT_TILES, int TILE_SIZE_PIXEL, GridPane pane) {
        for(int i = 0; i < FIELD_WIDTH_TILES; ++i) {
            for(int j = 0; j < FIELD_HEIGHT_TILES; ++j) {
                MinesweeperTile tile = new MinesweeperTile(TILE_SIZE_PIXEL);
                if(i == 3 && j == 6) {
                    tile.setToMine();
                }
                pane.add(tile, i, j);
            }
        }
        mineExploded = false;
        mineField = pane;
    }

    public static void flipAllTiles() {
        mineExploded = true;
        for(Node tile : mineField.getChildren()) {
            System.out.println("FlipAllTiles flips: " + tile.toString());
            if(tile instanceof MinesweeperTile) {
                ((MinesweeperTile) tile).flip();
            }
        }
    }
}
