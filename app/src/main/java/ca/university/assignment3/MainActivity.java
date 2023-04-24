package ca.university.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import ca.university.assignment3.model.Options;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchWelcomeScreen();
        findViewById(R.id.btnPlayGame).setOnClickListener(v-> launchGameScreen());
        findViewById(R.id.btnOptions).setOnClickListener(v-> launchOptionsScreen());
        findViewById(R.id.btnHelp).setOnClickListener(v-> launchHelpScreen());
        displayAnimations();
    }

    private void displayAnimations() {
        ImageView goldChest1 = findViewById(R.id.imgVGoldChest1);
        ImageView goldChest4 = findViewById(R.id.imgVGoldChest4);

        goldChest1.animate().rotation(360).translationY(-400).setDuration(30000);
        goldChest4.animate().rotation(360).translationY(500).setDuration(30000);
    }

    private void launchGameScreen() {
        Intent intent = GameScreenActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }

    private void launchOptionsScreen() {
        Intent intent = OptionsScreenActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }

    private void launchHelpScreen() {
        Intent intent = HelpScreenActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }

    private void launchWelcomeScreen() {
        Intent intent = WelcomeScreenActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }
}