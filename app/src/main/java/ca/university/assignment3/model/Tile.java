package ca.university.assignment3.model;

// Tile class is a model class that represents one tile in the board
public class Tile {
    int row;
    int column;
    boolean isGold;
    int numGoldInRow;
    int numGoldInColumn;
    boolean isTileCliked;

    public Tile(int row, int column, boolean isGold,int numGoldInRow,int numGoldInColumn,boolean isTileCliked) {
        this.row = row;
        this.column = column;
        this.isGold = isGold;
        this.numGoldInRow = numGoldInRow;
        this.numGoldInColumn = numGoldInColumn;
        this.isTileCliked = isTileCliked;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isGold() {
        return isGold;
    }

    public void setGold(boolean gold) {
        isGold = gold;
    }

    public int getNumGoldInRow() {
        return numGoldInRow;
    }

    public void setNumGoldInRow(int numGoldInRow) {
        this.numGoldInRow = numGoldInRow;
    }

    public int getNumGoldInColumn() {
        return numGoldInColumn;
    }

    public void setNumGoldInColumn(int numGoldInColumn) {
        this.numGoldInColumn = numGoldInColumn;
    }

    public boolean isTileCliked() {
        return isTileCliked;
    }

    public void setTileCliked(boolean tileCliked) {
        isTileCliked = tileCliked;
    }
}
