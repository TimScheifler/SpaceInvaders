package com.example.spaceinvaders.models.ships;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Space;

import com.example.spaceinvaders.models.GameObject;
import com.example.spaceinvaders.models.lasers.Laser;

import java.util.ArrayList;

public abstract class SpaceShip extends GameObject {

    private int attackSpeed;
    private int charger = 0;
    private int damage;

    private boolean isPlayer;

    private ArrayList<Laser> lasers;

    SpaceShip(Bitmap image, boolean isPlayer, int xPos, int yPos, int xSpeed, int ySpeed, int attackSpeed) {
        super(image, xPos, yPos, xSpeed, ySpeed);

        this.attackSpeed = attackSpeed;
        this.damage = attackSpeed / 2;
        this.isPlayer = isPlayer;
        this.lasers = new ArrayList<>();
    }

    public void update(){
        if(gameObjectReachedSideBoarder()){
            changeXVelocity();
        }
        updatePosition();
        removeLaserOutsideOfScreen();
    }

    private void removeLaserOutsideOfScreen(){
        ArrayList<Laser> lasersToRemove = new ArrayList<>();
        for(Laser laser : lasers){
            if(laserLeftScreen(laser)){
                lasersToRemove.add(laser);
            }
        }
        for(Laser laser : lasersToRemove){
            lasers.remove(laser);
        }
    }

    private boolean laserLeftScreen(Laser laser){
        return laser.gameObjectLeavesScreen();
    }

    private void updatePosition(){
        updateXPosition();
        updateYPosition();
        updateLasers();
    }

    private void updateLasers(){
        for (Laser laser : lasers){
            laser.update();
        }
    }

    @Override
    public void draw(Canvas canvas){
        drawLasers(canvas);
        canvas.drawBitmap(getImage(), getxPosition(), getyPosition(), null);
    }

    private void drawLasers(Canvas canvas){
        for(Laser laser : lasers){
            laser.draw(canvas);
        }
    }

    boolean laserIsCharged(){
        if(charger >= attackSpeed) {
            charger = 0;
            return true;
        }else{
            charger++;
            return false;
        }
    }

    void shootLaser(Bitmap laserBitmap, int laserSpeed){
        lasers.add(new Laser(laserBitmap, getxPosition(), getyPosition(), laserSpeed, damage, isPlayer));
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public void setLasers(ArrayList<Laser> lasers) {
        this.lasers = lasers;
    }
}
