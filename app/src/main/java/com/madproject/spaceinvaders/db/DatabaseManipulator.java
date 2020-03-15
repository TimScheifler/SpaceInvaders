package com.madproject.spaceinvaders.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.madproject.spaceinvaders.handler.SharedPreferencesHandler;
import com.madproject.spaceinvaders.models.components.PlayerScore;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulator extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "player_score.db";
    private static final int DATABASE_VERSION = 1;

    private SharedPreferencesHandler sharedPreferencesHandler;
    private Context context;

    private static final String RESULT_TABLE_NAME = "scores";
    private static final String RESULT_COLUMN_ID = "id";
    private static final String RESULT_COLUMN_NAME = "name";
    private static final String RESULT_COLUMN_WAVE = "wave";
    private static final String RESULT_COLUMN_SCORE = "score";


    public DatabaseManipulator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RESULT_TABLE_NAME + " ( " +
                RESULT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RESULT_COLUMN_NAME + " TEXT, " +
                RESULT_COLUMN_WAVE + " INT, " +
                RESULT_COLUMN_SCORE + " INT " + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESULT_TABLE_NAME);
        onCreate(db);
    }

    public void insertResult(int wave, int score) {
        ContentValues values = new ContentValues();

        values.put(RESULT_COLUMN_NAME, sharedPreferencesHandler.getSharedPrefsPlayerName());
        values.put(RESULT_COLUMN_WAVE, wave);
        values.put(RESULT_COLUMN_SCORE, score);
        getWritableDatabase().insert(RESULT_TABLE_NAME, null, values);
    }

    public List<PlayerScore> getResults() {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT " + RESULT_COLUMN_NAME + ", " + RESULT_COLUMN_WAVE + ", " + RESULT_COLUMN_SCORE +
                        " FROM " + RESULT_TABLE_NAME +
                        " ORDER BY " + RESULT_COLUMN_SCORE + " DESC" +
                        " LIMIT 10", null);

        List<PlayerScore> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            String playerName = cursor.getString(0);
            int wave = cursor.getInt(1);
            int score = cursor.getInt(2);
            PlayerScore gameResult = new PlayerScore(playerName, wave,score);
            results.add(gameResult);
        }
        cursor.close();
        return results;
    }
}
