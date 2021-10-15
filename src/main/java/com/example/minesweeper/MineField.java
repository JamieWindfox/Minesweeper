package com.example.minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineField extends GridPane {

    public final int FIELD_WIDTH_TILES;
    public final int FIELD_HEIGHT_TILES;
    public final int TILE_SIZE_PIXEL;
    public final int STAGE_HEIGHT_PIXEL;
    public final int STAGE_WIDTH_PIXEL;
    public final int MINE_COUNT;

    private int flippedEmptyTilesCount;
    private boolean mineExploded;


    public MineField(int width, int height, int tileSize, int mineCount) {
        super();
        FIELD_WIDTH_TILES = width;
        FIELD_HEIGHT_TILES = height;
        TILE_SIZE_PIXEL = tileSize;
        STAGE_HEIGHT_PIXEL = FIELD_HEIGHT_TILES * TILE_SIZE_PIXEL + 110;
        STAGE_WIDTH_PIXEL = FIELD_WIDTH_TILES * TILE_SIZE_PIXEL + 34;
        MINE_COUNT = mineCount;

    }

    public MineField(MineField field) {
        super();
        FIELD_WIDTH_TILES = field.FIELD_WIDTH_TILES;
        FIELD_HEIGHT_TILES = field.FIELD_HEIGHT_TILES;
        TILE_SIZE_PIXEL = field.TILE_SIZE_PIXEL;
        STAGE_HEIGHT_PIXEL = field.STAGE_HEIGHT_PIXEL;
        STAGE_WIDTH_PIXEL = field.STAGE_WIDTH_PIXEL;
        MINE_COUNT = field.MINE_COUNT;
    }

    public void generateMineField() {
        int mines = 0;
        flippedEmptyTilesCount = 0;
        Random rnd = new Random(LocalDateTime.now().getNano());

        for(int i = 0; i < FIELD_HEIGHT_TILES; ++i) {
            for(int j = 0; j < FIELD_WIDTH_TILES; ++j) {
                MinesweeperTile tile = new MinesweeperTile(TILE_SIZE_PIXEL);

                int randomNumber = rnd.nextInt((FIELD_WIDTH_TILES * FIELD_HEIGHT_TILES) / MINE_COUNT);
                if(mines < MINE_COUNT && randomNumber == 0)
                {
                    System.out.println("Set mine on " + i + "/" + j);
                    mines++;
                    tile.setToMine();
                }

                add(tile, i, j);
            }
        }

        if(mines < MINE_COUNT) {
            System.out.println("Too less mines were generated, try again");
            GameHandler.createGame(this);
        }

        setAdjacentMineNumbers();

    }

    private void setAdjacentMineNumbers() {
        for(Node tile : getChildren()) {
            if(tile instanceof MinesweeperTile && ((MinesweeperTile)tile).isMine()) {
                List<MinesweeperTile> adjacentTiles = new ArrayList<>();
                if(getRowIndex(tile)-1 >= 0 && getColumnIndex(tile)-1 >= 0) {// left up
                    getTile(getRowIndex(tile)-1, getColumnIndex(tile)-1).setAdjacentToMine();
                }
                if(getRowIndex(tile)-1 >= 0) {// up
                    getTile(getRowIndex(tile)-1, getColumnIndex(tile)).setAdjacentToMine();
                }
                if(getRowIndex(tile)-1 >= 0 && getColumnIndex(tile)+1 < FIELD_WIDTH_TILES) {// right up
                    getTile(getRowIndex(tile)-1, getColumnIndex(tile)+1).setAdjacentToMine();
                }
                if(getColumnIndex(tile)+1 < FIELD_WIDTH_TILES) {// right
                    getTile(getRowIndex(tile), getColumnIndex(tile)+1).setAdjacentToMine();
                }
                if(getRowIndex(tile)+1 < FIELD_HEIGHT_TILES && getColumnIndex(tile)+1 < FIELD_WIDTH_TILES) {// right down
                    getTile(getRowIndex(tile)+1, getColumnIndex(tile)+1).setAdjacentToMine();
                }
                if(getRowIndex(tile)+1 < FIELD_HEIGHT_TILES) {// down
                    getTile(getRowIndex(tile)+1, getColumnIndex(tile)).setAdjacentToMine();
                }
                if(getRowIndex(tile)+1 < FIELD_HEIGHT_TILES && getColumnIndex(tile)-1 >= 0) {// left down
                    getTile(getRowIndex(tile)+1, getColumnIndex(tile)-1).setAdjacentToMine();
                }
                if(getColumnIndex(tile)-1 >= 0) {// left
                    getTile(getRowIndex(tile), getColumnIndex(tile)-1).setAdjacentToMine();
                }

            }
        }
    }

    public MinesweeperTile getTile(int row, int column) {
        for(Node tile : getChildren()) {
            if(tile instanceof MinesweeperTile && getRowIndex(tile) == row && getColumnIndex(tile) == column) {
                return (MinesweeperTile) tile;
            }
        }
        return null;
    }

    public void flipAllTiles() {
        mineExploded = true;
        for(Node tile : getChildren()) {
            if(tile instanceof MinesweeperTile) {
                ((MinesweeperTile) tile).flip();
            }
        }
    }



}
