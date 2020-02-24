package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

import com.example.spaceinvaders.models.ships.Invader;
import com.example.spaceinvaders.models.ships.Player;
import com.example.spaceinvaders.models.ships.SpaceShip;

import java.util.ArrayList;

public class GameObjectHandler {

    private Player player;
    private ArrayList<SpaceShip> spaceShips = new ArrayList<>();

    private Context context;

    private int enemyTimer = 100;

    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    public GameObjectHandler(Context context){
        this.context = context;
        player = new Player(context, screenWidth / 2,screenHeight - 200,10, 0, 20);
        spaceShips.add(player);
    }

    public void update(){
        spawnEnemies();
        removeSpaceShipsOutsideOfScreen();
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.update();
        }
    }

    public void draw(Canvas canvas){
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.draw(canvas);
        }
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

    private void spawnEnemies(){
        if(enemyTimer == 0){
            enemyTimer = 100;
            spaceShips.add(new Invader(context,300,0,10,10,30));
        }else{
            enemyTimer--;
        }
    }
}
