package com.madproject.spaceinvaders;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesHandler {

    private static final String MY_PREFS_LANGUAGE = "SPACE_INVADERS_LANGUAGE";
    private static final String GAME_LANGUAGE = "language";
    private static final String DEFAULT_LANGUAGE = "de";

    private static final String MY_PREFS_NAME = "SPACE_INVADERS_GAME_PLAYER_NAME";
    private static final String PLAYER_NAME = "name";
    private static final String DEFAULT_NAME = "Player";

    private static final String MY_PREFS_SOUND_ON = "SPACE_INVADERS_SOUND";
    private static final String SOUND_ON = "sound";
    private static final boolean DEFAULT_SOUND_ON = true;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private Context context;

    public SharedPreferencesHandler(Context context){
        this.context = context;
    }

    public boolean getSharedPrefsSoundOn(){
        prefs = context.getSharedPreferences(MY_PREFS_SOUND_ON, MODE_PRIVATE);
        return prefs.getBoolean(SOUND_ON, DEFAULT_SOUND_ON);
    }

    public void applySharedPrefsSoundOn(boolean soundOn){
        editor = context.getSharedPreferences(MY_PREFS_SOUND_ON, MODE_PRIVATE).edit();
        editor.putBoolean(SOUND_ON, soundOn);
        editor.apply();
    }

    public String getSharedPrefsLanguage(){
        prefs = context.getSharedPreferences(MY_PREFS_LANGUAGE, MODE_PRIVATE);
        return prefs.getString(GAME_LANGUAGE, DEFAULT_LANGUAGE);
    }

    public void applySharedPrefsLanguage(String language){
        editor = context.getSharedPreferences(MY_PREFS_LANGUAGE, MODE_PRIVATE).edit();
        editor.putString(GAME_LANGUAGE, language);
        editor.apply();
    }

    public String getSharedPrefsPlayerName(){
        prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(PLAYER_NAME,DEFAULT_NAME);
    }

    public void applySharedPrefsPlayerName(String newSharedPref){
        editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(PLAYER_NAME, newSharedPref);
        editor.apply();
    }
}
