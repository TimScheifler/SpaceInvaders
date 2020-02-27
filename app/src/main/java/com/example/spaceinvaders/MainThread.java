package com.example.spaceinvaders;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * The MainThread.
 */
public class MainThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private static Canvas canvas; //Leinwand

    /**
     * The Constructor of the MainThread.
     *
     * @param surfaceHolder The SurfaceHolder.
     * @param gameView The GameView.
     */
    MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    /**
     * The running-Loop.
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
     *
     * @param running Boolean to decide whether or not to run our canvas.
     */
    void setRunning(boolean running) {
        this.running = running;
    }
}
