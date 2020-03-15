package com.madproject.spaceinvaders.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.madproject.spaceinvaders.Rescaler;
import com.madproject.spaceinvaders.models.components.CollisionBox;
import com.madproject.spaceinvaders.models.components.Position;
import com.madproject.spaceinvaders.models.components.Velocity;

/**
 * A GameObject.
 */
public abstract class GameObject {

    private Bitmap image;
    protected Position position;
    private Velocity velocity;
    private CollisionBox collisionBox;

    private Rescaler rescaler;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameObject(Bitmap image, Position position, Velocity velocity){
        rescaler = new Rescaler();
        this.image = Bitmap.createScaledBitmap(image,(int)(image.getWidth()*rescaler.getXRescaleFactor()), (int)(image.getHeight()*rescaler.getYRescaleFactor()), true);

        this.collisionBox = new CollisionBox(image.getWidth(), image.getHeight());

        this.position = position;
        this.velocity = velocity;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, position.getX(), position.getY(), null);
    }

    protected boolean gameObjectReachedSideBoarder(){
        return position.getX() + image.getWidth() >= screenWidth || position.getX() <= 0;
    }

    protected boolean gameObjectReachedSpecificHeight(){
        return position.getY() > screenHeight / 2 || position.getY() < -50;
    }

    public boolean gameObjectLeavesScreen(){
        return position.getY() <= -100
                || position.getY() >= screenHeight;
    }

    protected void changeXVelocity(){
        velocity.setxVelocity(velocity.getxVelocity() * -1);
    }
    protected void changeYVelocity(){
        velocity.setyVelocity(velocity.getyVelocity() * -1);
    }

    protected void updatePosition() {
        position.updatePosition(velocity.getxVelocity(), velocity.getyVelocity());
    }

    public Bitmap getImage() {
        return image;
    }

    protected void setImage(Bitmap bitmap){
        this.image = bitmap;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public CollisionBox getCollisionBox(){
        return collisionBox;
    }

    public Velocity getVelocity(){ return velocity;}
}
