package com.example.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.Random;

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

    public static void createGame(int FIELD_WIDTH_TILES, int FIELD_HEIGHT_TILES, int TILE_SIZE_PIXEL, GridPane pane, int MINE_COUNT) {
        int mines = 0;
        Random rnd = new Random(LocalDateTime.now().getNano());

        for(int i = 0; i < FIELD_WIDTH_TILES; ++i) {
            for(int j = 0; j < FIELD_HEIGHT_TILES; ++j) {
                MinesweeperTile tile = new MinesweeperTile(TILE_SIZE_PIXEL);

                int randomNumber = rnd.nextInt((FIELD_WIDTH_TILES * FIELD_HEIGHT_TILES) / MINE_COUNT);
                if(mines < MINE_COUNT && randomNumber == 0)
                {
                    System.out.println("Set mine on " + i + "/" + j);
                    mines++;
                    tile.setToMine();
                }

                pane.add(tile, i, j);
            }
        }

        if(mines < MINE_COUNT) {
            System.out.println("Too less mines were generated, try again");
            createGame(FIELD_WIDTH_TILES, FIELD_HEIGHT_TILES, TILE_SIZE_PIXEL, pane, MINE_COUNT);
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
