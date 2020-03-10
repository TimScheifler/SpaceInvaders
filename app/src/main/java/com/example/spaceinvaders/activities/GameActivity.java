package com.example.spaceinvaders.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.spaceinvaders.handler.GameObjectHandler;
import com.example.spaceinvaders.views.GameView;

/**
 * The GameActivity.
 */
public class GameActivity extends Activity implements View.OnTouchListener {

    GameView gameView;
    GameObjectHandler gameObjectHandler;

    /**
     * Called when GameActivity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameObjectHandler = new GameObjectHandler(this);
        gameView = new GameView(this, gameObjectHandler);
        gameView.setOnTouchListener(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(gameView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE)
            gameObjectHandler.receiveUserInput((int)event.getX());
        return true;
    }
}
