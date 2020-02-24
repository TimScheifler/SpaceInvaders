package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.R;

public class Invader extends SpaceShip {

    private Bitmap laser;

    public Invader(Context context, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.invader), false,
                xPos, yPos, xSpeed, ySpeed, attackSpeed);

        laser = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_laser);
    }

    @Override
    public void update(){
        super.update();

        if(laserIsCharged()){
            shootLaser(laser, 20);
        }
    }
}
