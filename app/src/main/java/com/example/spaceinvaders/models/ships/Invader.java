package com.example.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.spaceinvaders.models.components.Position;
import com.example.spaceinvaders.R;
import com.example.spaceinvaders.models.components.Velocity;

/**
 * An Invader.
 */
public class Invader extends SpaceShip {

    public Invader(Context context, Position position, Velocity velocity, int attackSpeed, int health) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.invader),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_laser_small),
                false, position, velocity, attackSpeed, health);
    }
}
