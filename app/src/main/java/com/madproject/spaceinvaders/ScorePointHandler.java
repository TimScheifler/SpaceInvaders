package com.madproject.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.madproject.spaceinvaders.models.ships.Player;

class ScorePointHandler {

    private Player player;
    private Paint paint;

    private int score = 0;

    public ScorePointHandler(Player player){

        this.player = player;

        paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);

    }

    public void draw(Canvas canvas){
        canvas.drawText("Points: "+score,Resources.getSystem().getDisplayMetrics().widthPixels / 5,42, paint);
    }
    public void update(){
        this.score = player.getScore();
    }
}
