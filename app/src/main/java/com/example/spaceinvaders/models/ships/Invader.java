package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.R;

public class Invader extends SpaceShip {

    public Invader(Context context, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.invader),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_laser_small),
                false, xPos, yPos, xSpeed, ySpeed, attackSpeed);
    }

    @Override
    public void update(){
        super.update();
    }
}
