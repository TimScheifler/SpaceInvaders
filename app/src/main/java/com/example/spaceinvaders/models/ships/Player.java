package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.models.components.Position;
import com.example.spaceinvaders.R;
import com.example.spaceinvaders.models.components.Velocity;

/**
 * A Player.
 */
public class Player extends SpaceShip {

    public Player(Context context, Position position, Velocity velocity, int attackSpeed, int health) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.player),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.laser_small),
                true, position, velocity, attackSpeed, health);
    }
}
