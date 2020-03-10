package com.example.spaceinvaders.handler;

import android.content.Context;
import android.media.SoundPool;

import com.example.spaceinvaders.R;

public class SoundHandler {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int shootSound;
    private static int gameOverSound;

    public SoundHandler(Context context){
        soundPool = new SoundPool.Builder().build();

        shootSound = soundPool.load(context, R.raw.laser,1);
    }

    public void playShootSound(){
        soundPool.play(shootSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
