package com.example.spaceinvaders.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class GameObject {

    private final Bitmap image;
    private int xPosition, yPosition;
    private int xVelocity, yVelocity;

    private final int width;
    private final int height;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameObject(Bitmap image, int xPosition, int yPosition, int xVelocity, int yVelocity){
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, xPosition, yPosition, null);
    }

    public boolean gameObjectReachedSideBoarder(){
        return xPosition + image.getWidth() >= screenWidth || xPosition <= 0;
    }

    public boolean gameObjectLeavesScreen(){
        return yPosition <= -100
                || yPosition >= screenHeight;
    }

    public void changeXVelocity(){
        xVelocity = xVelocity * -1;
    }

    public void updateXPosition(){
        xPosition += xVelocity;
    }

    public void updateYPosition(){
        yPosition += yVelocity;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
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
