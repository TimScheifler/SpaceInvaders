package com.example.spaceinvaders.models.lasers;

import android.graphics.Bitmap;

import com.example.spaceinvaders.models.GameObject;

public class Laser extends GameObject {

    private final int damage;
    private final boolean isPlayerLaser;

    public Laser(Bitmap image, int xPos, int yPos, int ySpeed, int damage, boolean isPlayerLaser) {
        super(image, xPos, yPos, 0, ySpeed);

        this.damage = damage;
        this.isPlayerLaser = isPlayerLaser;
    }

    private void collision(){

        if(isPlayerLaser){

        }else{

        }
    }

    public void update(){
        updateYPosition();
    }

    public int getDamage() {
        return damage;
    }
}
