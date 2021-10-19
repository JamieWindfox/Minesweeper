package com.example.minesweeper;

public class GameHandler {


    private static MineField mineField;
    private static boolean gameLost;
    private static boolean gameWon;
    private static int flippedEmptyTilesCount = 0;


    public static void createGame(MineField field) {
        mineField = field;
        mineField.generateMineField();
        gameLost = false;
        gameWon = false;
        flippedEmptyTilesCount = 0;
    }

    private static void flipAllTiles() {
        mineField.flipAllTiles();
    }


    public static void winGame() {
        MinesweeperApplication.finishGame(true);
    }

    public static void looseGame() {
        flipAllTiles();
        MinesweeperApplication.finishGame(false);
    }

    public static void flipEmptyTile(boolean mineExploded) {
        flippedEmptyTilesCount++;

        if(mineExploded == false && flippedEmptyTilesCount >= mineField.FIELD_HEIGHT_TILES * mineField.FIELD_WIDTH_TILES - mineField.MINE_COUNT) {
            System.out.println("You won the game!");
            GameHandler.winGame();
        }
    }

    public static MineField getMineField() {
        return mineField;
    }
}
