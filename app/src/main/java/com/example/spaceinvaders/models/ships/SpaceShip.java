package com.example.spaceinvaders.models.ships;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Space;

import com.example.spaceinvaders.models.GameObject;
import com.example.spaceinvaders.models.lasers.Laser;

public abstract class SpaceShip extends GameObject {

    private final Bitmap laserImage;
    private int attackSpeed;
    private int charger = 0;
    private int damage;
    private boolean isPlayer;

    SpaceShip(Bitmap image, Bitmap laserImage, boolean isPlayer, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(image, xPos, yPos, xSpeed, ySpeed);
        this.laserImage = laserImage;
        this.attackSpeed = attackSpeed;
        this.damage = attackSpeed / 2;
        this.isPlayer = isPlayer;
    }

    public void update(){
        if(gameObjectReachedSideBoarder()){
            changeXVelocity();
        }
        updatePosition();
        //removeLaserOutsideOfScreen(); //TODO
    }

    private void updatePosition(){
        updateXPosition();
        updateYPosition();
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(getImage(), getxPosition(), getyPosition(), null);
    }

    public boolean laserIsCharged(){
        if(charger >= attackSpeed) {
            charger = 0;
            return true;
        }else{
            charger++;
            return false;
        }
    }

    public Laser shootLaser(Bitmap laserBitmap, int laserSpeed){
        if(isPlayer){
            return getNewLaser(laserBitmap, -laserSpeed);
        }else{
            return getNewLaser(laserBitmap, laserSpeed);
        }
    }
    private Laser getNewLaser(Bitmap laserBitmap, int laserSpeed){
        return new Laser(laserBitmap, getxPosition(), getyPosition(), laserSpeed, damage, isPlayer);
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Bitmap getLaserImage() {
        return laserImage;
    }
}
