package minesweeper;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class TileGrid {

    private final int HEIGHT = 10;
    private final int WIDTH = 10;
    private final int MINE_COUNT = 10;

    private final GridPane grid;

    public TileGrid() {
        grid = new GridPane();

        // Create empty rows
        for(int i = 0; i < HEIGHT; ++i) {
            grid.addRow(i);
        }

        // Create empty columns
        for(int i = 0; i < WIDTH; ++i) {
            grid.addColumn(i);
        }

        // Create mines on random positions
        for(int i = 0; i < MINE_COUNT;) {
            Random rnd = new Random();

            int row = rnd.nextInt(HEIGHT);
            int column = rnd.nextInt(WIDTH);

            if(isTileMine(row, column) == false) {

            }

            grid.add(new Mine(), rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
        }
    }

    private boolean isTileMine(int row, int column) {
        for(Node tile : grid.getChildren()) {
            if()
        }
    }

}
