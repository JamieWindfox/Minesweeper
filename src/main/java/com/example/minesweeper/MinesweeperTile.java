package com.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class MinesweeperTile extends Button {

    private final String HIDDEN_TILE_COLOR = "#999999";
    private final String FLAGGED_TILE_COLOR = "#f4cccc";
    private final String MINE_TILE_COLOR = "#ff0000";

    private boolean _isMine = false;
    private boolean _isFlagged = false;

    public MinesweeperTile(int tileSizeInPixel) {
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
            flip();
        }
    }

    public void flip() {
        if(isDisabled()) return;
        System.out.println("Flip tile");
        if(_isMine == true) {
            setStyle("-fx-background-color: " + MINE_TILE_COLOR);
            setDisabled(true);
            // flip all tiles on board
            GameHandler.flipAllTiles();

            // lose game
            System.out.println("The tile was a mine. Game lost.");
        } else {
            // flip tile and disable it
            this.setDisabled(true);
            GameHandler.flipEmptyTile();
            System.out.println("The tile was not a mine");
        }
    }

    /**
     * Set tile to flag or back
     */
    private void handleRightMouseClick() {
        if(_isFlagged == true) {
            _isFlagged = false;
            setText("");
            setTextFill(Color.BLACK);
            setStyle("-fx-background-color: " + HIDDEN_TILE_COLOR);
        } else {
            _isFlagged = true;
            setText("P");
            setTextFill(Color.RED);
            setStyle("-fx-background-color: " + FLAGGED_TILE_COLOR);
        }
        System.out.println("Flag: " + _isFlagged);
    }




}
