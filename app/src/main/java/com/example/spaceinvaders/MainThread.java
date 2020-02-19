package com.example.spaceinvaders;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * TODO
 */
public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private static Canvas canvas; //Leinwand

    /**
     * TODO
     *
     * @param surfaceHolder
     * @param gameView
     */
    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    /**
     * TODO
     */
    @Override
    public void run(){
        while (running){
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }catch(Exception e){
                Log.e("Exception: ", e.getMessage());
            }finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Setter for "running".
     * @param running Boolean to decide whether or not to run our canvas.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
