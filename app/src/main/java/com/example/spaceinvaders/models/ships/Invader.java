package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.Position;
import com.example.spaceinvaders.R;

/**
 * An Invader.
 */
public class Invader extends SpaceShip {

    public Invader(Context context, Position position, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.invader),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_laser_small),
                false, position, xSpeed, ySpeed, attackSpeed);
    }
}
