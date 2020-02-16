package com.example.spaceinvaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * TODO
 */
public class Player {

    private Bitmap image;
    private int xCoord;
    private int yCoord;

    private int xVelocity = 10;
    private int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * TODO
     *
     * @param image
     */
    public Player(Bitmap image){
        this.image = image;
        xCoord = screenWidth / 2;
        yCoord = screenHeight - image.getHeight()*2;
    }

    /**
     * TODO
     *
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,xCoord,yCoord,null);

    }

    /**
     * TODO
     */
    public void update(){
        updateTest();
    }

    private void updateTest(){
        if(xCoord + image.getWidth() >= screenWidth || xCoord <= 0){
            xVelocity = xVelocity * -1;
        }
        xCoord += xVelocity;
    }
}
