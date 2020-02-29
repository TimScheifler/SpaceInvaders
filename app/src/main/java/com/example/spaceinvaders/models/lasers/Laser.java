package com.example.spaceinvaders.models.lasers;

import android.graphics.Bitmap;

import com.example.spaceinvaders.Position;
import com.example.spaceinvaders.Velocity;
import com.example.spaceinvaders.models.GameObject;

/**
 * The Laser.
 */
public class Laser extends GameObject {

    private final int damage;
    private final boolean isPlayerLaser;

    public Laser(Bitmap image, Position position, Velocity velocity, int damage, boolean isPlayerLaser) {
        super(image, position, velocity);

        this.damage = damage;
        this.isPlayerLaser = isPlayerLaser;
    }

    public void update(){
        updatePosition();
    }

    public int getDamage() {
        return damage;
    }

    public boolean isPlayerLaser(){
        return isPlayerLaser;
    }
}
