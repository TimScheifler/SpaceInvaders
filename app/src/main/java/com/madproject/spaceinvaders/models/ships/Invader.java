package com.madproject.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.models.components.Position;
import com.madproject.spaceinvaders.models.components.Velocity;

/**
 * An Invader.
 */
public class Invader extends SpaceShip {

    public Invader(Context context, Position position, Velocity velocity, int attackSpeed, int health) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.invader_1),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.invader_2),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy_laser),
                false, position, velocity, attackSpeed, health);
    }
}
