package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.Position;
import com.example.spaceinvaders.R;

/**
 * A Player.
 */
public class Player extends SpaceShip {

    public Player(Context context, Position position, int xSpeed, int ySpeed, int attackSpeed) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.player),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.laser_small),
                true, position, xSpeed, ySpeed, attackSpeed);
    }
}
