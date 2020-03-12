package com.madproject.spaceinvaders.models.ships;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.Rescaler;
import com.madproject.spaceinvaders.models.components.Position;
import com.madproject.spaceinvaders.models.components.Velocity;

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
