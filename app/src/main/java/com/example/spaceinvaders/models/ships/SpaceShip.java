package com.example.spaceinvaders.models.ships;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Space;

import com.example.spaceinvaders.Position;
import com.example.spaceinvaders.models.GameObject;
import com.example.spaceinvaders.models.lasers.Laser;

/**
 * A SpaceShip.
 */
public abstract class SpaceShip extends GameObject {

    private final Bitmap laserImage;
    private int attackSpeed;
    private int charger = 0;
    private int damage;
    private final boolean isPlayer;

    /**
     *
     * @param image
     * @param laserImage
     * @param isPlayer
     * @param position
     * @param xVelocity
     * @param yVelocity
     * @param attackSpeed
     */
    SpaceShip(Bitmap image, Bitmap laserImage, boolean isPlayer, Position position, int xVelocity, int yVelocity, int attackSpeed) {
        super(image, position, xVelocity, yVelocity);

        this.laserImage = laserImage;
        this.attackSpeed = attackSpeed;
        this.damage = attackSpeed / 2;
        this.isPlayer = isPlayer;
    }

    /**
     *
     */
    public void update(){
        if(gameObjectReachedSideBoarder()){
            changeXVelocity();
        }
        updatePosition();
        //removeLaserOutsideOfScreen(); //TODO
    }

    /**
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(getImage(), position.getX(), position.getY(), null);
    }

    /**
     *
     * @return
     */
    public boolean laserIsCharged(){
        if(charger >= attackSpeed) {
            charger = 0;
            return true;
        }else{
            charger++;
            return false;
        }
    }

    /**
     *
     * @param laserBitmap
     * @param laserSpeed
     * @return
     */
    public Laser shootLaser(Bitmap laserBitmap, int laserSpeed){
        if(isPlayer){
            return getNewLaser(laserBitmap, -laserSpeed);
        }else{
            return getNewLaser(laserBitmap, laserSpeed);
        }
    }

    private Laser getNewLaser(Bitmap laserBitmap, int laserSpeed){
        return new Laser(laserBitmap, new Position(position.getX(),position.getY()), laserSpeed, damage, isPlayer);
    }

    /**
     * Getter of attackSpeed.
     *
     * @return the AttackSpeed of a SpaceShip.
     */
    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Setter of attackSpeed.
     *
     * @param attackSpeed the new AttackSpeed.
     */
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Getter of the LaserImage.
     *
     * @return The laserImage.
     */
    public Bitmap getLaserImage() {
        return laserImage;
    }
}
