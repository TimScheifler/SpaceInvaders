package com.madproject.spaceinvaders.handler;

import android.content.Context;
import android.media.SoundPool;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.db.SharedPreferencesHandler;

public class SoundHandler {

    private static SoundPool soundPool;
    private static int shootSound;

    private SharedPreferencesHandler sharedPreferencesHandler;

    private final float MASTER_VOLUME = 0.2f;

    SoundHandler(Context context){
        soundPool = new SoundPool.Builder().build();
        sharedPreferencesHandler = new SharedPreferencesHandler(context);

        shootSound = soundPool.load(context, R.raw.laser,1);
    }

    void playShootSound(){
        if(isSoundActivated())
            playSound(shootSound);
    }

    private void playSound(int sound){
        soundPool.play(sound, MASTER_VOLUME, MASTER_VOLUME, 1, 0, 1.0f);
    }

    private boolean isSoundActivated(){
        return sharedPreferencesHandler.getSharedPrefsSoundOn();
    }
}
