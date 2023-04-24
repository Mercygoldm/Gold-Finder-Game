package ca.university.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import ca.university.assignment3.model.MessageFragment;
import ca.university.assignment3.model.Options;
import ca.university.assignment3.model.Tile;

public class GameScreenActivity extends AppCompatActivity {

    private Options option;
    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_GOLD;

    Button buttons[][];
    Tile tiles[];
    private int clicks =0;
    private int goldFound = 0;
    TextView txtViewNumberOfGold;
    TextView txtViewNumberOfClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        txtViewNumberOfGold = findViewById(R.id.txtViewFoundGold);
        txtViewNumberOfClicks = findViewById(R.id.textViewNumOfScans);
        txtViewNumberOfClicks.setText("# Scans used: " + clicks);
        txtViewNumberOfGold.setText("Found " + goldFound + " of " + NUM_GOLD + " gold.");

        option = Options.getInstance();

        int numOfGamesPlayedCurrently = option.getNumberOfGamesPlayedCurrently();
        numOfGamesPlayedCurrently++;
        option.setNumberOfGamesPlayedCurrently(numOfGamesPlayedCurrently);

        NUM_ROWS = option.getNumberOfRows();
        NUM_COLS = option.getNumberOfColumns();
        NUM_GOLD = option.getNumberOfGoldCoins();
        buttons = new Button[NUM_ROWS][NUM_COLS];
        tiles = new Tile[NUM_ROWS*NUM_COLS];


      makeBoard();
      initializeBoard();
      populateButtons();
    }

    private void makeBoard() {
        int totalNumberOfTiles = NUM_ROWS*NUM_COLS;
        int index = 0;
        int[] tilesWithGold= RandomNumber(NUM_GOLD,totalNumberOfTiles);
        int tilesWithGoldCount = 0;
        for(int row=0;row<NUM_ROWS;row++){
            for(int col =0;col<NUM_COLS;col++){
                tiles[index] = new Tile(row,col,false,0,0,false);

                if(tilesWithGoldCount < NUM_GOLD) {
                    if (index == tilesWithGold[tilesWithGoldCount]) {
                        tiles[index].setGold(true);
                        tilesWithGoldCount++;
                    } else {
                        tiles[index].setGold(false);
                    }
                }else{
                    tiles[index].setGold(false);
                }
                index++;
            }
        }
    }

    private void initializeBoard() {
        int count = 0;int firstColIndex =0;int firstRowIndex =0;
        for(int row=0;row<NUM_ROWS;row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                boolean goldPresent = tiles[count].isGold();
                if(goldPresent == true){
                    // update rows
                    IncrementRows(row,col,firstColIndex,count);

                    // update columns
                    IncrementColumns(row,col,firstRowIndex,count);
                }
                count++;
            }
            firstColIndex++;
        }
    }

    private void IncrementRows(int row,int col,int firstColIndex,int goldIndex) {
        firstColIndex = firstColIndex * (NUM_COLS);
        int index = firstColIndex;
        for(int j=0;j<NUM_COLS;j++){

            if (index == goldIndex){
                index++;
                continue;
            }else{
                int r = tiles[index].getRow();
                int c = tiles[index].getColumn();
                int goldInRow = tiles[index].getNumGoldInRow();
                goldInRow++;
                tiles[index].setNumGoldInRow(goldInRow);
                index++;
            }
        }
    }

    private void IncrementColumns(int row,int col,int firstRowIndex,int goldIndex) {
        int test = goldIndex;
        while(test>(NUM_COLS-1)){
            test = test -NUM_COLS;
        }
        firstRowIndex = test;

        int index = firstRowIndex;
        for(int i=0;i<NUM_ROWS;i++){

            if (index == goldIndex){
                index = index + NUM_COLS;
                continue;
            }else{
                int r = tiles[index].getRow();
                int c = tiles[index].getColumn();
                int goldInCol = tiles[index].getNumGoldInColumn();
                goldInCol++;
                tiles[index].setNumGoldInColumn(goldInCol);
                index = index + NUM_COLS;
            }
        }
    }

    private int[] RandomNumber(int numOfGold,int totalNumOfTiles){
        int index = 0;
        int[] tilesWithGold = new int [numOfGold];
        Random random = new Random();
        int randomTileNumber = random.nextInt(totalNumOfTiles);

        tilesWithGold[index] = randomTileNumber;
        numOfGold--;index++;

        while(numOfGold > 0) {
            boolean decrement = true;
            randomTileNumber = random.nextInt(totalNumOfTiles);

            for(int i=0;i<index;i++){
                if(randomTileNumber == tilesWithGold[i]) {
                    decrement = false;
                    break;
                }
            }
            if(decrement == true) {
                tilesWithGold[index] = randomTileNumber;
                numOfGold--;
                index++;
            }
        }
        Arrays.sort(tilesWithGold);
        return tilesWithGold;
    }
    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        for(int row=0;row < NUM_ROWS;row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
            1.0f));
            table.addView(tableRow);

            for(int col =0;col<NUM_COLS;col++){
                final int FINAL_ROW = row;
                final int FINAL_COL = col;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //What to do when a button is clicked
                        gridButtonClicked(FINAL_ROW,FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }
    private void setUpSetMessage() {
        FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();
        dialog.show(manager,"MessageDialog");
    }

    private void gridButtonClicked(int row, int col) {
        Button button = buttons[row][col];

        // lock button sizes
        lockButtonSizes();

        int index = 0;
        boolean isGoldThere = false;
        int goldLeftInRow=0, goldLeftInCol=0, goldLeft =0;
        int row2 =0;int col2=0;
        for(int i=0;i<tiles.length;i++){
            row2 = tiles[i].getRow();
            col2 = tiles[i].getColumn();
            if((row2 ==row) && (col2==col)){
                index = i;
                isGoldThere = tiles[i].isGold();
                tiles[i].setGold(false);
                goldLeftInRow = tiles[i].getNumGoldInRow();
                goldLeftInCol = tiles[i].getNumGoldInColumn();
                goldLeft = goldLeftInRow + goldLeftInCol;

                final MediaPlayer wrongClick = MediaPlayer.create(this,R.raw.mixkit_click_error);
                final MediaPlayer goldClick = MediaPlayer.create(this,R.raw.mixkit_select_click);
                if(isGoldThere != true) {
                    tiles[i].setTileCliked(true);
                    clicks++;
                    txtViewNumberOfClicks.setText("# Scans used: " + clicks);
                    wrongClick.start();
                }else{
                    goldClick.start();
                    goldFound++;
                    txtViewNumberOfGold.setText("Found " + goldFound + " of " + NUM_GOLD + " gold.");
                    if(goldFound == NUM_GOLD){
                        // You have won dialog
                         setUpSetMessage();
                    }
                }
            }
        }

        int firstColIndex = index;
        while((firstColIndex % NUM_COLS) != 0){
            firstColIndex--;
        }

        int firstRowIndex =0;

        if(isGoldThere == true) {
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.goldcoin);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            // decrement row
            decrementRows(row2,col2,firstColIndex,index);

            // decrement column
            decrementColumns(row2,col2,firstRowIndex,index);

        }else{
            button.setText("" + goldLeft);
        }
    }

    private void decrementRows(int row,int col,int firstColIndex,int goldIndex) {
        int index = firstColIndex;
        for(int j=0;j<NUM_COLS;j++){

            if (index == goldIndex){
                index++;
                continue;
            }else{
                int r = tiles[index].getRow();
                int c = tiles[index].getColumn();
                int goldInRow = tiles[index].getNumGoldInRow();

                goldInRow--;
                tiles[index].setNumGoldInRow(goldInRow);

                Button btn = buttons[r][c];
                int updatedC = tiles[index].getNumGoldInColumn();
                int updatedR = tiles[index].getNumGoldInRow();
                int updated = updatedR + updatedC;

                if (tiles[index].isTileCliked() == true) {
                    btn.setText("" + updated);
                }
                index++;
            }
        }
    }

    private void decrementColumns(int row,int col,int firstRowIndex,int goldIndex) {
        int test = goldIndex;
        while (test > (NUM_COLS - 1)) {
            test = test - NUM_COLS;
        }
        firstRowIndex = test;

        int index = firstRowIndex;
        for (int i = 0; i < NUM_ROWS; i++) {
            if (index == goldIndex) {
                index = index + NUM_COLS;
                continue;
            } else {
                int r = tiles[index].getRow();
                int c = tiles[index].getColumn();
                int goldInCol = tiles[index].getNumGoldInColumn();
                goldInCol--;
                tiles[index].setNumGoldInColumn(goldInCol);

                Button btn = buttons[r][c];
                int updatedC = tiles[index].getNumGoldInColumn();
                int updatedR = tiles[index].getNumGoldInRow();
                int updated = updatedR + updatedC;
                if (tiles[index].isTileCliked() == true) {
                    btn.setText("" + updated);
                }
                index = index + NUM_COLS;
            }
        }
    }

    private void lockButtonSizes() {
        for(int row =0;row<NUM_ROWS;row++){
            for(int col = 0;col<NUM_COLS;col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,GameScreenActivity.class);
    }
}