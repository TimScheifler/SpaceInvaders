package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.R;

public class Player extends SpaceShip {

    public Player(Context context, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.player),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.laser_small),
                true, xPos, yPos, xSpeed, ySpeed, attackSpeed);
    }

    @Override
    public void update(){
        super.update();
    }
}
