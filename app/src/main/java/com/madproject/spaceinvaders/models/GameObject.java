package com.madproject.spaceinvaders.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

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
    protected CollisionBox collisionBox;

    private boolean collidable;

    Rescaler rescaler;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameObject(Bitmap image, Position position, Velocity velocity){
        rescaler = new Rescaler();
        Log.i("RESCALER X",rescaler.getXRescaleFactor()+"");
        Log.i("RESCALER Y",rescaler.getYRescaleFactor()+"");
        this.image = Bitmap.createScaledBitmap(image,(int)(image.getWidth()*rescaler.getXRescaleFactor()), (int)(image.getHeight()*rescaler.getYRescaleFactor()), true);

        this.collisionBox = new CollisionBox(image.getWidth(), image.getHeight());

        this.position = position;
        this.velocity = velocity;
        this.collidable = true; //TODO add functionality
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, position.getX(), position.getY(), null);
    }

    public boolean gameObjectReachedSideBoarder(){
        return position.getX() + image.getWidth() >= screenWidth || position.getX() <= 0;
    }

    public boolean gameObjectLeavesScreen(){
        return position.getY() <= -100
                || position.getY() >= screenHeight;
    }

    public void changeXVelocity(){
        velocity.setxVelocity(velocity.getxVelocity() * -1);
    }

    protected void updatePosition() {
        position.updatePosition(velocity.getxVelocity(), velocity.getyVelocity());
    }

    public Bitmap getImage() {
        return image;
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
