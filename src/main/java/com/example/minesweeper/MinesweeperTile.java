package com.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class MinesweeperTile extends Button {

    private boolean _isMine = false;
    private boolean _isFlagged = false;

    public MinesweeperTile(int tileSizeInPixel) {
        super();

        setPrefSize(tileSizeInPixel, tileSizeInPixel);
        setMinSize(tileSizeInPixel, tileSizeInPixel);

        this.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                if(_isFlagged == false) {
                    // flip tile and check what it is
                    System.out.println("Flip tile");
                }
            } else if(event.getButton() == MouseButton.SECONDARY) {
                // set tile to flag or back
                if(_isFlagged == true) {
                    _isFlagged = false;
                    setText("");
                    setTextFill(Color.BLACK);
                } else {
                    _isFlagged = true;
                    setText("P");
                    setTextFill(Color.RED);
                }
                System.out.println("Flag: " + _isFlagged);
            }
        });
    }




}
