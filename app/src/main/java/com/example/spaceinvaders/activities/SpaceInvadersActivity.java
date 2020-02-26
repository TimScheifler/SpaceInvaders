package com.example.spaceinvaders.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spaceinvaders.R;

public class SpaceInvadersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.start_game_button);
        Button settingsButton = findViewById(R.id.settings_button);
        Button highscoreButton = findViewById(R.id.score_button);

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
    }

    private void startActivity(Class nextClass){
        Intent intent =
                new Intent(SpaceInvadersActivity.this, nextClass);
        startActivityForResult(intent,1);
    }
}
