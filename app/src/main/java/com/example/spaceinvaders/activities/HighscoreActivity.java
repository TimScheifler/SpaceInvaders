package com.example.spaceinvaders.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.spaceinvaders.R;
import com.example.spaceinvaders.db.DatabaseManipulator;

import java.util.List;

/**
 * The HighscoreActivity.
 */
public class HighscoreActivity extends ListActivity {

    DatabaseManipulator databaseManipulator;

    List<String[]> names2 = null;
    String[] stg1;

    /**
     * Called when HighscoreActivity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_activity);

        databaseManipulator = new DatabaseManipulator(this);
        names2 = databaseManipulator.selectAll();
        stg1 = new String[names2.size()];
        int x = 0;
        String stg;

        for(String[] name : names2){
            stg = name[1] + " - "
                    + name[2] + " - "
                    + name[3];
            stg1[x] = stg;
            x++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,stg1);
        this.setListAdapter(adapter);

        Button backButton = (Button) findViewById(R.id.return_button);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
