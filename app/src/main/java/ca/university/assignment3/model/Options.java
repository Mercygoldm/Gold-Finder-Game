package ca.university.assignment3.model;

// Options class represents the game boards size
// And the number of gold coins in the board per game
public class Options {

    private int numberOfGoldCoins;
    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfGamesFromLastReset;
    private int numberOfGamesPlayedCurrently;

    private static Options instance;
    public static Options getInstance(){
        if(instance == null){
            instance = new Options();
        }
            return instance;
    }

    public Options() {
        this.numberOfGoldCoins = 6;
        this.numberOfRows = 4;
        this.numberOfColumns = 6;
        this.numberOfGamesFromLastReset =0;
        this.numberOfGamesPlayedCurrently=0;
    }

    public int getNumberOfGoldCoins() {
        return numberOfGoldCoins;
    }

    public void setNumberOfGoldCoins(int numberOfGoldCoins) {
        this.numberOfGoldCoins = numberOfGoldCoins;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfGamesFromLastReset() {
        return numberOfGamesFromLastReset;
    }

    public void setNumberOfGamesFromLastReset(int numberOfGamesFromLastReset) {
        this.numberOfGamesFromLastReset = numberOfGamesFromLastReset;
    }

    public static void setInstance(Options instance) {
        Options.instance = instance;
    }

    public int getNumberOfGamesPlayedCurrently() {
        return numberOfGamesPlayedCurrently;
    }

    public void setNumberOfGamesPlayedCurrently(int numberOfGamesPlayedCurrently) {
        this.numberOfGamesPlayedCurrently = numberOfGamesPlayedCurrently;
    }
}
