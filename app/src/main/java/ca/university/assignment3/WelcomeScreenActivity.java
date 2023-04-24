package ca.university.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.time.LocalDateTime;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        findViewById(R.id.btnMainMenu).setOnClickListener(v->finish());
        displayAnimations();
    }

    private void displayAnimations() {
        ImageView goldChest = findViewById(R.id.imgVGoldChest);
        ImageView goldBag = findViewById(R.id.imgVGoldBag);
        ImageView goldChest2 = findViewById(R.id.imgVGoldChest2);

        goldChest.animate().rotation(360).translationY(400).setDuration(30000).withEndAction(() ->
                goldChest2.animate().rotation(360).translationY(-400).setDuration(5000).withEndAction(()->
                        finish()).start()
        ).start();
        goldBag.animate().rotation(360).translationY(-400).setDuration(30000);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,WelcomeScreenActivity.class);
    }
}