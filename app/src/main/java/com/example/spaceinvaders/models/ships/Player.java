package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.R;

public class Player extends SpaceShip {

    private Bitmap laser;

    public Player(Context context, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.player), true,
                xPos, yPos, xSpeed, ySpeed, attackSpeed);
        laser = BitmapFactory.decodeResource(context.getResources(), R.drawable.laser);
    }

    @Override
    public void update(){
        super.update();

        if(laserIsCharged()){
            shootLaser(laser, 20 * -1);
        }
    }
}
