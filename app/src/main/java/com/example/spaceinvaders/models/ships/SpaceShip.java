package com.example.spaceinvaders.models.ships;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.spaceinvaders.models.components.Position;
import com.example.spaceinvaders.models.components.Velocity;
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
    private int health;
    private int score;
    private int killPoints;

    /**
     *
     * @param image
     * @param laserImage
     * @param isPlayer
     * @param position
     * @param velocity
     * @param attackSpeed
     */
    SpaceShip(Bitmap image, Bitmap laserImage, boolean isPlayer, Position position, Velocity velocity, int attackSpeed, int health) {
        super(image, position, velocity);

        this.laserImage = laserImage;
        this.attackSpeed = attackSpeed;
        this.damage = 1;
        this.isPlayer = isPlayer;
        this.health = health;
        this.score = 0;
        this.killPoints=100;
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
        Position laserPosition = calculateNewLaserPosition();
        Velocity laserVelocity = new Velocity(0, laserSpeed);
        return new Laser(laserBitmap, laserPosition, laserVelocity, damage, isPlayer);
    }

    private Position calculateNewLaserPosition(){
        if(isPlayer){
            return new Position((position.getX()+getImage().getWidth() / 2)-laserImage.getWidth()/2,position.getY());
        }else{
            return new Position((position.getX()+getImage().getWidth() / 2)-laserImage.getWidth()/2, position.getY()+getImage().getHeight());
        }
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

    public boolean isPlayer(){
        return isPlayer;
    }
    public void dropHealth(int damage){
        this.health -= damage;
    }
    public boolean isDying(){
        return health <= 0;
    }
    public int getHealth(){
        return health;
    }

    public void increaseScore(int newPoints){
        score += newPoints;
    }

    public int getScore(){
        return score;
    }

    public int getKillPoints(){
        return killPoints;
    }
}
