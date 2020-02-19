package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * TODO
 */
public class Laser {

    private boolean enemyShot;
    private int speed;
    private int xCoord;
    private int yCoord;
    private Bitmap image;

    /**
     * TODO
     *
     * @param context
     * @param xCoord
     * @param yCoord
     * @param enemyShot
     */
    public Laser(Context context, int xCoord, int yCoord, boolean enemyShot){
        this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.laser);
        this.speed = 30;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.enemyShot = enemyShot;
    }

    /**
     * Draws the Laser.
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,xCoord,yCoord,null);
    }

    /**
     * Updates the Laser.
     */
    public void update(){
        yCoord-=speed;
    }
}
