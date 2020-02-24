package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The GameView.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Context context;
    private GameObjectHandler gameObjectHandler;

    /**
     * Each time this class is called to create a new object (our surface), it will
     * run the constructor and it will create a new surface.
     * @param context The context.
     */
    public GameView(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this); //adding callback to intercept events (intercept = abfangen).
        gameObjectHandler = new GameObjectHandler(context);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    /**
     * Creats the surface.
     *
     * @param holder Holds the surface of the display.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    /**
     * TODO
     *
     * @param holder Holds the surface of the display.
     * @param format The format of the surface.
     * @param width The width of the surface.
     * @param height The height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * Destroys the surface.
     *
     * @param holder Holds the surface of the display.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Updates the display.
     */
    public void update() {
        gameObjectHandler.update();
    }

    /**
     * Draws the Sprites.
     *
     * @param canvas The canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //OLD
        if(canvas != null){
            canvas.drawColor(Color.DKGRAY);
            gameObjectHandler.draw(canvas);
        }
    }
}