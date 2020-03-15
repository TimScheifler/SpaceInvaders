package com.madproject.spaceinvaders.activities;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

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

    private TextView first, second, third;

    private List<String[]> names2 = null;
    private String[] stg1;
    private String[] topThree;
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
        setContentView(R.layout.test);

        first = findViewById(R.id.first_place);
        second = findViewById(R.id.second_place);
        third = findViewById(R.id.third_place);

        topThree = new String[3];

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
                    printLocalScore();
                }
                setTopThreeTextViews();
                executeListAdapter();
            }
        });
    }

    private void printLocalScore(){
        List<PlayerScore> playerScores = databaseManipulator.getResults();
        stg1 = new String[playerScores.size()-3];
        String stg;
        int x = 0;
        int topThreeCounter = 0;

        if(playerScores.size()<3){
            topThree[0] = "Player1 - 0 - 0";
            topThree[1] = "Player2 - 0 - 0";
            topThree[2] = "Player3 - 0 - 0";
        }else{
            for(PlayerScore playerScore : playerScores){
                stg = playerScore.getName()+" - "+playerScore.getWave()+" - "+playerScore.getScore();
                if(topThreeCounter<3){
                    topThree[topThreeCounter] = stg;
                    topThreeCounter++;
                }else{
                    stg1[x] = stg;
                    x++;
                }
            }
        }
    }

    private void printGlobalScore(){
        stg1 = new String[playerScores.size()-3];
        topThree = new String[3];
        int x = 0;
        String stg;

        int topThreeCounter = 0;
        for(PlayerScore playerScore : playerScores){
            stg = playerScore.getName() + " - "+ playerScore.getWave()+" - "+playerScore.getScore();
            Log.i("Place "+topThreeCounter+1,stg);
            if(topThreeCounter<3){
                topThree[topThreeCounter] = stg;
                topThreeCounter++;
            }else{
                stg1[x] = stg;
                x++;
            }
        }
    }

    private void setTopThreeTextViews(){
        first.setText(topThree[0]);
        second.setText(topThree[1]);
        third.setText(topThree[2]);
    }

    private void executeListAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,stg1){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };
        this.setListAdapter(adapter);
    }
}
