package com.madproject.spaceinvaders.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.madproject.spaceinvaders.models.components.PlayerScore;
import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.db.DatabaseManipulator;
import com.madproject.spaceinvaders.db.FirebaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * The HighscoreActivity.
 */
public class HighscoreActivity extends ListActivity {

    private DatabaseManipulator databaseManipulator;

    private FirebaseHelper firebaseHelper;

    private List<String[]> names2 = null;
    private String[] stg1;
    private ArrayList<PlayerScore> playerScores;

    private boolean isGlobalScore = true;

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
        firebaseHelper = new FirebaseHelper(this);

        playerScores = firebaseHelper.getScoretableFromFirebase();

        printLocalScore();

        executeListAdapter();

        Button backButton = findViewById(R.id.return_button);
        final Button updateButton = findViewById(R.id.update_button);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGlobalScore){
                    isGlobalScore = false;
                    updateButton.setText(getResources().getString(R.string.show_local_score));
                    printGlobalScore();
                }else{
                    isGlobalScore = true;
                    updateButton.setText(getResources().getString(R.string.show_global_score));
                    //firebaseHelper.getScoretableFromFirebase();
                    printLocalScore();
                }
                executeListAdapter();
            }
        });
    }

    private void printLocalScore(){
        List<PlayerScore> playerScores = databaseManipulator.getResults();
        stg1 = new String[playerScores.size()];
        String stg;
        int x = 0;

        for(PlayerScore playerScore : playerScores){
            stg = playerScore.getName()+" "+playerScore.getWave()+" "+playerScore.getScore();
            stg1[x] = stg;
            x++;
        }
    }

    private void printGlobalScore(){
        stg1 = new String[playerScores.size()];
        int x = 0;
        String stg;

        for(PlayerScore playerScore : playerScores){
            stg = playerScore.getName() + " - "+ playerScore.getWave()+" - "+playerScore.getScore();
            Log.i("Playscores",stg);
            stg1[x] = stg;
            x++;
        }
    }

    private void executeListAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,stg1);
        this.setListAdapter(adapter);
    }
}
