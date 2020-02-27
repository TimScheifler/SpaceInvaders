package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.spaceinvaders.models.lasers.Laser;
import com.example.spaceinvaders.models.ships.Invader;
import com.example.spaceinvaders.models.ships.Player;
import com.example.spaceinvaders.models.ships.SpaceShip;

import java.util.ArrayList;

class GameObjectHandler {

    private ArrayList<SpaceShip> spaceShips = new ArrayList<>();
    private ArrayList<Laser> lasers = new ArrayList<>();

    private Context context;
    private int waveCounter;
    private int totalAmountOfEnemies;
    private int remainingEnemies;
    private int enemyTimer = 100;

    GameObjectHandler(Context context){
        this.context = context;
        waveCounter = 1;
        totalAmountOfEnemies = 5;
        remainingEnemies = totalAmountOfEnemies;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        Player player = new Player(context, new Position(screenWidth / 2, screenHeight - 200), 10, 0, 20);
        spaceShips.add(player);
    }

    void update(){
        spawnEnemies();
        removeSpaceShipsOutsideOfScreen();
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.update();
            if(spaceShip.laserIsCharged()){
                lasers.add(spaceShip.shootLaser(spaceShip.getLaserImage(), 8));
            }
        }

        //own method...
        ArrayList<Laser> lasersToRemove = new ArrayList<>();
        for(Laser laser : lasers){
            laser.update();
            if(laser.gameObjectLeavesScreen()){
                lasersToRemove.add(laser);
            }
        }

        //TODO check if this really removes the Object (think...)
        for(Laser laser : lasersToRemove){
            lasers.remove(laser);
            Log.i("REMOVED", "Successfully removed Laser");
        }
    }

    void draw(Canvas canvas){
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.draw(canvas);
        }
        for(Laser laser : lasers){
            laser.draw(canvas);
        }
        printInformationOnCanvas(canvas);
    }

    private void removeLasersOutsideOfScreen(){
        ArrayList<Laser> lasersToRemove = new ArrayList<>();
        for(Laser laser : lasers){
            if(isLaserOutsideOfScreen(laser)){
                lasersToRemove.add(laser);
            }
        }
        for(Laser laser : lasersToRemove){
            lasers.remove(laser);
        }
    }
    private boolean isLaserOutsideOfScreen(Laser laser){
        return laser.gameObjectLeavesScreen();
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

    private void printInformationOnCanvas(Canvas canvas){
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
                spaceShips.add(new Invader(context,new Position(300,0),10,3,33));
                remainingEnemies--;
            }else{
                enemyTimer--;
            }
        }
    }
}
