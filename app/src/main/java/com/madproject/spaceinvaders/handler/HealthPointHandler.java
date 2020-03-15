package com.madproject.spaceinvaders.handler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.models.ships.Player;

public class HealthPointHandler {


    private Bitmap healthBar;
    private Context context;
    private int health;
    private Player player;
    private int relativeHeight;

    HealthPointHandler(Context context, Player player, int relativeHeight){
        this.health = player.getHealth();
        this.relativeHeight = relativeHeight;
        this.context = context;
        this.player = player;
        Bitmap tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.hp_full);
        rescaleHealthBar(tmp);

    }

    private void changeHealthBarImage(){
        switch (health){
            case 4 : setHealthBar(R.drawable.hp_four);
                break;
            case 3 : setHealthBar(R.drawable.hp_three);
                break;
            case 2 : setHealthBar(R.drawable.hp_two);
                break;
            case 1 : setHealthBar(R.drawable.hp_one);
                break;
            case 0 : setHealthBar(R.drawable.hp_death);
                break;
                default:
                    setHealthBar(R.drawable.hp_full);
                    break;
        }
        rescaleHealthBar(healthBar);
    }

    private void rescaleHealthBar(Bitmap tmp){
        healthBar = Bitmap.createScaledBitmap(tmp, Resources.getSystem().getDisplayMetrics().widthPixels / 5, relativeHeight, true);
    }

    private void setHealthBar(int drawable){
        healthBar = BitmapFactory.decodeResource(context.getResources(), drawable);
    }

    void update(){
        health = player.getHealth();
        changeHealthBarImage();
    }

    void draw(Canvas canvas){
        canvas.drawBitmap(healthBar, 0, 0, null);
    }
}
