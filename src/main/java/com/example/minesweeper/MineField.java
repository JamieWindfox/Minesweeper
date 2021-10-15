package com.example.minesweeper;

import javafx.scene.layout.GridPane;

public class MineField extends GridPane {

    private final int FIELD_WIDTH_TILES;
    private final int FIELD_HEIGHT_TILES;
    private final int TILE_SIZE_PIXEL;
    private final int STAGE_HEIGHT_PIXEL;
    private final int STAGE_WIDTH_PIXEL;
    private final int MINE_COUNT;

    public MineField(int width, int height, int tileSize, int mineCount) {
        FIELD_WIDTH_TILES = width;
        FIELD_HEIGHT_TILES = height;
        TILE_SIZE_PIXEL = tileSize;
        STAGE_HEIGHT_PIXEL = FIELD_HEIGHT_TILES * TILE_SIZE_PIXEL + 110;
        STAGE_WIDTH_PIXEL = FIELD_WIDTH_TILES * TILE_SIZE_PIXEL + 34;
        MINE_COUNT = mineCount;


    }

}
