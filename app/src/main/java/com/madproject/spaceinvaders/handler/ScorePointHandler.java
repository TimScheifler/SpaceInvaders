package com.madproject.spaceinvaders.handler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.models.ships.Player;

class ScorePointHandler {

    private Player player;
    private Paint paint;
    private Context context;

    private int score = 0;

    ScorePointHandler(Context context, Player player){
        this.context = context;
        this.player = player;

        paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);

    }

    void draw(Canvas canvas){
        String point_text = context.getResources().getString(R.string.points_text);
        canvas.drawText(point_text + ": " + score,Resources.getSystem().getDisplayMetrics().widthPixels / 5,42, paint);
    }
    void update(){
        this.score = player.getScore();
    }
}
