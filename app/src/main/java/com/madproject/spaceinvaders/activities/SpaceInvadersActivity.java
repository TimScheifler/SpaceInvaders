package com.madproject.spaceinvaders.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.madproject.spaceinvaders.R;

/**
 * The SpaceInvadersActivity.
 */
public class SpaceInvadersActivity extends Activity {

    /**
     * Called when SpaceInvadersActivity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.start_button);
        final Button settingsButton = findViewById(R.id.settings_button);
        final Button highscoreButton = findViewById(R.id.highscore_button);
        final Button creditsButton = findViewById(R.id.credits_button);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(GameActivity.class);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(SettingsActivity.class);
            }
        });
        highscoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HighscoreActivity.class);
            }
        });
        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreditsActivity.class);
            }
        });
    }

    private void startActivity(Class nextClass){
        Intent intent =
                new Intent(SpaceInvadersActivity.this, nextClass);
        startActivityForResult(intent,1);
    }
}
