package com.example.spaceinvaders.models.lasers;

import android.graphics.Bitmap;

import com.example.spaceinvaders.Position;
import com.example.spaceinvaders.models.GameObject;

/**
 * The Laser.
 */
public class Laser extends GameObject {

    private final int damage;
    private final boolean isPlayerLaser;

    public Laser(Bitmap image, Position position, int ySpeed, int damage, boolean isPlayerLaser) {
        super(image, position, 0, ySpeed);

        this.damage = damage;
        this.isPlayerLaser = isPlayerLaser;
    }

    private void collision(){

        if(isPlayerLaser){

        }else{

        }
    }

    public void update(){
        updatePosition();
    }

    public int getDamage() {
        return damage;
    }
}
