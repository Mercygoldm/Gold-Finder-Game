package ca.university.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.university.assignment3.model.Options;

public class OptionsScreenActivity extends AppCompatActivity {

    private Options option;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        option = Options.getInstance();

        createRadioButtons();
        getSavedData();
        updateGameCount();

        TextView textView = findViewById(R.id.txtViewGamesPlayed);
        textView.setText("" + option.getNumberOfGamesFromLastReset());
        findViewById(R.id.btnResetGamesPlayed).setOnClickListener(v->resetGameButton());
    }

    private void updateGameCount() {
        if(option.getNumberOfGamesFromLastReset() == 0) {
            option.setNumberOfGamesFromLastReset(option.getNumberOfGamesPlayedCurrently());
        }else{
            int sum = option.getNumberOfGamesPlayedCurrently() + option.getNumberOfGamesFromLastReset();
            option.setNumberOfGamesFromLastReset(sum);
            option.setNumberOfGamesPlayedCurrently(0);
        }
    }

    private void resetGameButton() {
        option.setNumberOfGamesFromLastReset(0);
        option.setNumberOfGamesPlayedCurrently(0);
        TextView textView = findViewById(R.id.txtViewGamesPlayed);
        int v = option.getNumberOfGamesFromLastReset();
        textView.setText("" + v);
    }

    @Override
    protected void onPause(){
        saveData();
        super.onPause();
    }

    public void saveData(){
        sharedPreferences = getSharedPreferences("Options",Context.MODE_PRIVATE);

        // Saving data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Number of gold coins",option.getNumberOfGoldCoins());
        editor.putInt("row size",option.getNumberOfRows());
        editor.putInt("column size",option.getNumberOfColumns());
        editor.putInt("num of games played",option.getNumberOfGamesFromLastReset());
        editor.commit();
    }

    public void getSavedData(){
        sharedPreferences = getSharedPreferences("Options",MODE_PRIVATE);

        int gold = sharedPreferences.getInt("Number of gold coins",6);
        int row = sharedPreferences.getInt("row size",4);
        int column = sharedPreferences.getInt("column size",6);
        int numOfGamesPlayed = sharedPreferences.getInt("num of games played",0);

        option.setNumberOfGoldCoins(gold);
        option.setNumberOfRows(row);
        option.setNumberOfColumns(column);
        option.setNumberOfGamesFromLastReset(numOfGamesPlayed);
    }

    private void createRadioButtons() {
        RadioGroup radioGrpBoardSize = findViewById(R.id.radioGrpBoardSize);
        RadioGroup radioGrpNumberOfGold = findViewById(R.id.radioGrpNumberOfGold);

        int[] numberOfGolds = getResources().getIntArray(R.array.number_of_gold);
        int[] numberOfRows = getResources().getIntArray(R.array.number_of_rows);
        int[] numberOfColumns = getResources().getIntArray(R.array.number_of_columns);

        makeRadioButton(radioGrpNumberOfGold, numberOfGolds);
        makeRadioButton(radioGrpBoardSize, numberOfRows,numberOfColumns);
    }
    private void makeRadioButton(RadioGroup radioGrp, int[] numberOfItems){
        // create the buttons
        for(int i =0;i<numberOfItems.length;i++){
            final int numberOfItem = numberOfItems[i];

            // creating 1 radio button
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(numberOfItem + getString(R.string.gold_coins));

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    option.setNumberOfGoldCoins(numberOfItem);
                    Toast.makeText(OptionsScreenActivity.this,"You clicked me " + numberOfItem, Toast.LENGTH_SHORT).show();
                }
            });

            //Adding the 1 created radio button to the radio group
            radioGrp.addView(radioButton);
        }
    }

    private void makeRadioButton(RadioGroup radioGrp, int[] numberOfRows, int[] numberOfColumns){
        // create the buttons
        for(int i =0;i<numberOfRows.length;i++){
            final int row = numberOfRows[i];
            final int column = numberOfColumns[i];

            // creating 1 radio button
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(row + " rows x " + column + " columns");

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    option.setNumberOfRows(row);
                    option.setNumberOfColumns(column);
                    Toast.makeText(OptionsScreenActivity.this,"You clicked me " + row, Toast.LENGTH_SHORT).show();
                }
            });

            //Adding the 1 created radio button to the radio group
            radioGrp.addView(radioButton);
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,OptionsScreenActivity.class);
    }
}