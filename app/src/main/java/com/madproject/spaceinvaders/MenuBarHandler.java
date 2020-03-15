package com.madproject.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.madproject.spaceinvaders.models.ships.Player;

public class MenuBarHandler {

    private HealthPointHandler healthPointHandler;
    private ScorePointHandler scorePointHandler;

    private int relativeHeight = Resources.getSystem().getDisplayMetrics().widthPixels / 20;

    private Bitmap grey_bar;

    public MenuBarHandler(Context context, Player player){

        healthPointHandler = new HealthPointHandler(context, player, relativeHeight);
        scorePointHandler = new ScorePointHandler(context, player);
        Bitmap grey_tmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.grey_bar);
        grey_bar = Bitmap.createScaledBitmap(grey_tmp, Resources.getSystem().getDisplayMetrics().widthPixels, relativeHeight, true);
    }

    public void update(){
        healthPointHandler.update();
        scorePointHandler.update();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(grey_bar, Resources.getSystem().getDisplayMetrics().widthPixels / 5, 0, null);
        healthPointHandler.draw(canvas);
        scorePointHandler.draw(canvas);
    }
}
