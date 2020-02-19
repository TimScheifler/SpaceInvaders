package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * TODO
 */
public class Player {

    private Bitmap image;
    private int xCoord;
    private int yCoord;


    private int xVelocity = 10;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /**
     * TODO
     */
    public Player(Context context){
        this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        xCoord = screenWidth / 2;
        yCoord = screenHeight - image.getHeight()*2;
    }

    /**
     * Draws the Player.
     *
     * @param canvas
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, xCoord, yCoord,null);
    }

    /**
     * Updates the player.
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

    /**
     * @return current xCoord of the Player.
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * @return current yCoord of the Player.
     */
    public int getyCoord() {
        return yCoord;
    }
}
