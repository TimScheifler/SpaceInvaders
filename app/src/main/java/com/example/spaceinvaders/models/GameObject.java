package com.example.spaceinvaders.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.spaceinvaders.Position;

/**
 * A GameObject.
 */
public abstract class GameObject {

    private final Bitmap image;
    protected Position position;
    private int xVelocity, yVelocity;

    private boolean collidable;

    private final int width;
    private final int height;

    //Collisiondetectorsystem

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameObject(Bitmap image, Position position, int xVelocity, int yVelocity){
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.position = position;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
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
        xVelocity = xVelocity * -1;
    }

    public void updatePosition() {
        position.updateXByDistance(xVelocity);
        position.updateYByDistance(yVelocity);
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

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
