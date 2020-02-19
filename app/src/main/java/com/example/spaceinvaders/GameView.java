package com.example.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * The GameView.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Player player;
    private int count = 30;

    private Context context;

    private ArrayList<Laser> lasers = new ArrayList<>();

    /**
     * Each time this class is called to create a new object (our surface), it will
     * run the constructor and it will create a new surface.
     * @param context The context.
     */
    public GameView(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this); //adding callback to intercept events (intercept = abfangen).

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
        player = new Player(context);
        thread.start();
    }

    /**
     * TODO
     *
     * @param holder Holds the surface of the display.
     * @param format
     * @param width
     * @param height
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
        player.update();

        for(Laser laser : lasers){
            laser.update();
        }
    }

    /**
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas != null){
            canvas.drawColor(Color.DKGRAY);
            player.draw(canvas);
            if(count == 0){
                count = 30;
                lasers.add(new Laser(context, player.getxCoord(), player.getyCoord(), false));
            }else{
                count--;
            }
            for(Laser laser : lasers){
                laser.draw(canvas);
            }
        }


    }
}
