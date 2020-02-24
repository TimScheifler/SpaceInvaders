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

        Button startButton = (Button)findViewById(R.id.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent skipVerificationBlaBla =
                        new Intent(SpaceInvadersActivity.this, GameActivity.class);
                startActivityForResult(skipVerificationBlaBla,1);
            }
        });
    }

}
