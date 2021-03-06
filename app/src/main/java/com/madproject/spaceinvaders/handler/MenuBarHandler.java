package com.madproject.spaceinvaders.handler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.models.ships.Player;

class MenuBarHandler {

    private HealthPointHandler healthPointHandler;
    private ScorePointHandler scorePointHandler;

    private Bitmap grey_bar;

    MenuBarHandler(Context context, Player player){

        int relativeHeight = Resources.getSystem().getDisplayMetrics().widthPixels / 20;
        healthPointHandler = new HealthPointHandler(context, player, relativeHeight);
        scorePointHandler = new ScorePointHandler(context, player);
        Bitmap grey_tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.grey_bar);
        grey_bar = Bitmap.createScaledBitmap(grey_tmp, Resources.getSystem().getDisplayMetrics().widthPixels, relativeHeight, true);
    }

    void update(){
        healthPointHandler.update();
        scorePointHandler.update();
    }

    void draw(Canvas canvas){
        canvas.drawBitmap(grey_bar, Resources.getSystem().getDisplayMetrics().widthPixels / 5, 0, null);
        healthPointHandler.draw(canvas);
        scorePointHandler.draw(canvas);
    }
}
