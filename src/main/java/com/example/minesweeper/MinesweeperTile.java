package com.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class MinesweeperTile extends Button {

    private final static String HIDDEN_TILE_COLOR = "#999999";
    private final static String FLAGGED_TILE_COLOR = "#fffd9c";
    private final static String MINE_TILE_COLOR = "#ff0000";

    private boolean _isMine = false;
    private boolean _isFlagged = false;
    private int adjacentMineCount = 0;
    private final int ROW, COLUMN;

    public MinesweeperTile(int tileSizeInPixel, int row, int column) {
        super();

        setPrefSize(tileSizeInPixel, tileSizeInPixel);
        setMinSize(tileSizeInPixel, tileSizeInPixel);
        setStyle("-fx-background-color: " + HIDDEN_TILE_COLOR);

        this.setOnMouseClicked(event -> {
            if(isDisabled() == true) return;
            if(event.getButton() == MouseButton.PRIMARY) {
                handleLeftMouseClick();
            } else if(event.getButton() == MouseButton.SECONDARY) {
                handleRightMouseClick();
            }
        });

        ROW = row;
        COLUMN = column;
    }

    public void setToMine() {
        _isMine = true;
    }

    /**
     * Flip tile
     */
    private void handleLeftMouseClick() {
        if(_isFlagged == false) {
            // flip tile and check what it is
            flip(true);
        }
    }

    public void flip(boolean triggeredByMouseClick) {
        if(isDisabled()) return;
        setDisabled(true);
        if(_isMine == true) {
            Image bombPic = new Image("file:src/pictures/bomb.png");
            ImageView view = new ImageView(bombPic);
            this.setGraphic(view);
            setStyle("-fx-background-color: " + MINE_TILE_COLOR);
            // flip all tiles on board
            GameHandler.looseGame();

            // loose game
            System.out.println("The tile was a mine. Game lost.");
        } else {
            GameHandler.flipEmptyTile(false);
            if(adjacentMineCount > 0) {
                setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
                setText(String.valueOf(adjacentMineCount));
            }
            if(triggeredByMouseClick) {
                GameHandler.getMineField().flipAdjacentEmptyTiles(ROW, COLUMN);
            }
        }
    }

    public void setAdjacentToMine() {
        adjacentMineCount++;
    }

    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }


    /**
     * Set tile to flag or back
     */
    private void handleRightMouseClick() {
        if(_isFlagged == true) {
            _isFlagged = false;
            setText("");
            setStyle("-fx-background-color: " + HIDDEN_TILE_COLOR);
            this.setGraphic(null);
        } else {
            _isFlagged = true;
            setStyle("-fx-background-color: " + FLAGGED_TILE_COLOR);
            Image bombPic = new Image("file:src/pictures/flag.png");
            ImageView view = new ImageView(bombPic);
            this.setGraphic(view);
        }
        System.out.println("Flag: " + _isFlagged);
    }

    public boolean isMine() {
        return _isMine;
    }



}
