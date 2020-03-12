package com.madproject.spaceinvaders.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.SharedPreferencesHandler;
import com.madproject.spaceinvaders.db.DatabaseManipulator;

/**
 * TheSettingsActivity.
 */
public class SettingsActivity extends Activity {

    private DatabaseManipulator databaseManipulator;

    private String name;
    private SharedPreferencesHandler sharedPreferencesHandler;
    /**
     * Called when SettingsActivity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        sharedPreferencesHandler = new SharedPreferencesHandler(this);

        databaseManipulator = new DatabaseManipulator(this);

        Button startButton = findViewById(R.id.back_button);
        Button resetDBButton = findViewById(R.id.reset_db_button);
        Button saveNameButton = findViewById(R.id.save_button);
        final Button volumeButton = findViewById(R.id.volume_button);

        final EditText editText = findViewById(R.id.player_name);

        editText.setText(sharedPreferencesHandler.getSharedPrefsPlayerName());

        setVolumeButtonText(volumeButton);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resetDBButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //databaseManipulator();
            }
        });

        saveNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                sharedPreferencesHandler.applySharedPrefsPlayerName(name);
            }
        });

        volumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferencesHandler.getSharedPrefsSoundOn()){
                    sharedPreferencesHandler.applySharedPrefsSoundOn(false);
                    volumeButton.setText(R.string.volume_on);
                }else {
                    sharedPreferencesHandler.applySharedPrefsSoundOn(true);
                    volumeButton.setText(R.string.volume_off);
                }
            }
        });

    }

    private void setVolumeButtonText(Button volumeButton) {
        if(sharedPreferencesHandler.getSharedPrefsSoundOn())
            volumeButton.setText(R.string.volume_off);
        else
            volumeButton.setText(R.string.volume_on);

    }
}
