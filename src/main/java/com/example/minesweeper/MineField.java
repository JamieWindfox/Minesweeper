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
                MinesweeperTile tile = new MinesweeperTile(TILE_SIZE_PIXEL, i, j);

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
            MinesweeperApplication.startGame();
            return;
            //GameHandler.createGame(this);
        }

        setAdjacentMineNumbers();

    }

    public List<MinesweeperTile> getAdjacentTiles(int row, int column) {
        List<MinesweeperTile> neighbours = new ArrayList<>();
        if(row-1 >= 0 && column-1 >= 0 && getTile(row-1, column-1).isDisabled() == false) {// left up
            neighbours.add(getTile(row-1, column-1));
        }
        if(row-1 >= 0 && getTile(row-1, column).isDisabled() == false) {// up
            neighbours.add(getTile(row-1, column));
        }
        if(row-1 >= 0 && column+1 < FIELD_WIDTH_TILES && getTile(row-1, column+1).isDisabled() == false) {// right up
            neighbours.add(getTile(row-1, column+1));
        }
        if(column+1 < FIELD_WIDTH_TILES && getTile(row, column+1).isDisabled() == false) {// right
            neighbours.add(getTile(row, column+1));
        }
        if(row+1 < FIELD_HEIGHT_TILES && column+1 < FIELD_WIDTH_TILES && getTile(row+1, column+1).isDisabled() == false) {// right down
            neighbours.add(getTile(row+1, column+1));
        }
        if(row+1 < FIELD_HEIGHT_TILES && getTile(row+1, column).isDisabled() == false) {// down
            neighbours.add(getTile(row+1, column));
        }
        if(row+1 < FIELD_HEIGHT_TILES && column-1 >= 0 && getTile(row+1, column-1).isDisabled() == false) {// left down
            neighbours.add(getTile(row+1, column-1));
        }
        if(column-1 >= 0 && getTile(row, column-1).isDisabled() == false) {//
            neighbours.add(getTile(row, column-1));
        }
        return neighbours;
    }

    public List<MinesweeperTile> getAdjacentSaveTiles(int row, int column) {
        List<MinesweeperTile> neighbours = getAdjacentTiles(row, column);
        List<MinesweeperTile> saveNeighbours = new ArrayList<>();
        for(MinesweeperTile tile : neighbours) {
            if(tile.isMine() == false && tile.isDisabled() == false) {
                saveNeighbours.add(tile);
            }
        }
        return saveNeighbours;
    }

    private void setAdjacentMineNumbers() {
        for(Node tile : getChildren()) {
            if(tile instanceof MinesweeperTile && ((MinesweeperTile)tile).isMine()) {
                List<MinesweeperTile> adjacentTiles = getAdjacentTiles(getRowIndex(tile), getColumnIndex(tile));
                for(MinesweeperTile adjacentTile : adjacentTiles) {
                    adjacentTile.setAdjacentToMine();
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
                ((MinesweeperTile) tile).flip(false);
            }
        }
    }

    public void flipAdjacentEmptyTiles(int row, int column) {
        MinesweeperTile currentTile = getTile(row, column);
        if(currentTile.getAdjacentMineCount() > 0) {
            return;
        }
        List<MinesweeperTile> neighbours = getAdjacentSaveTiles(row, column);
        for(MinesweeperTile tile : neighbours) {
            tile.flip(false);
            flipAdjacentEmptyTiles(getRowIndex(tile), getColumnIndex(tile));
        }
    }
}
