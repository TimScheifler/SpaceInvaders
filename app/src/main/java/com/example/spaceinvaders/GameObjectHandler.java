package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.spaceinvaders.models.lasers.Laser;
import com.example.spaceinvaders.models.ships.Invader;
import com.example.spaceinvaders.models.ships.Player;
import com.example.spaceinvaders.models.ships.SpaceShip;

import java.util.ArrayList;

public class GameObjectHandler {

    private Player player;
    private ArrayList<SpaceShip> spaceShips = new ArrayList<>();

    private Context context;
    private int waveCounter;
    private int totalAmountOfEnemies;
    private int remainingEnemies;

    private int enemyTimer = 100;

    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    GameObjectHandler(Context context){
        this.context = context;
        waveCounter = 1;
        totalAmountOfEnemies = 5;
        remainingEnemies = totalAmountOfEnemies;
        player = new Player(context, screenWidth / 2,screenHeight - 200,10, 0, 20);
        spaceShips.add(player);
    }

    void update(){
        spawnEnemies();
        removeSpaceShipsOutsideOfScreen();
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.update();
        }
    }

    void draw(Canvas canvas){
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.draw(canvas);
        }
        writeStuffOnCanvas(canvas);
    }

    private void removeSpaceShipsOutsideOfScreen(){
        ArrayList<SpaceShip> spaceShipsToRemove = new ArrayList<>();
        for(SpaceShip spaceShip : spaceShips){
            if(spaceShip.gameObjectLeavesScreen()){
                spaceShipsToRemove.add(spaceShip);
            }
        }
        for(SpaceShip spaceShip : spaceShipsToRemove){
            spaceShips.remove(spaceShip);
            Log.i("InvaderRemover", "Successfully removed Invader!");
        }
    }

    void writeStuffOnCanvas(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(30);

        canvas.drawText("Wave: "+waveCounter+"\nRemaining Enemies: "+remainingEnemies,10,40, paint);
    }

    private void spawnEnemies(){
        if(remainingEnemies == 0){
            enemyTimer = 300;
            waveCounter++;
            totalAmountOfEnemies *= 1.4;
            remainingEnemies = totalAmountOfEnemies;
            Log.i("NewWave:",waveCounter+"");
        }else{
            if(enemyTimer == 0){
                enemyTimer = 100;
                spaceShips.add(new Invader(context,300,0,10,3,33));
                remainingEnemies--;
            }else{
                enemyTimer--;
            }
        }
    }
}
