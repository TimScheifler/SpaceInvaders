package com.example.spaceinvaders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.spaceinvaders.models.lasers.Laser;
import com.example.spaceinvaders.models.ships.Invader;
import com.example.spaceinvaders.models.ships.Player;
import com.example.spaceinvaders.models.ships.SpaceShip;

import java.util.ArrayList;

public class GameObjectHandler {

    private ArrayList<SpaceShip> spaceShips = new ArrayList<>();
    private ArrayList<Laser> lasers = new ArrayList<>();

    private Context context;
    private int waveCounter;
    private int totalAmountOfEnemies;
    private int remainingEnemies;
    private int enemyTimer = 100;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    private Player player;

    public GameObjectHandler(Context context){
        this.context = context;
        waveCounter = 1;
        totalAmountOfEnemies = 5;
        remainingEnemies = totalAmountOfEnemies;
        Velocity playerVelocity = new Velocity(0, 0);
        Position playerPosition = new Position(screenWidth / 2, screenHeight - 200);
        player = new Player(context, playerPosition, playerVelocity,  20, 1000);
        spaceShips.add(player);
    }

    void update(){
        spawnEnemies();
        removeSpaceShipsOutsideOfScreen();
        removeLasersOutsideOfScreen();

        updateSpaceShips();
        updateLasers();

        CollisionDetectorSystem detectorSystem = new CollisionDetectorSystem();

        int score = 0;
        for(SpaceShip spaceShip : new ArrayList<>(spaceShips)){
            for (Laser laser : new ArrayList<>(lasers)){
                if (detectorSystem.isColliding(spaceShip,laser)){
                    spaceShip.dropHealth(laser.getDamage());
                    lasers.remove(laser);
                    if(spaceShip.isDying()){
                        score+=spaceShip.getKillPoints();
                        spaceShips.remove(spaceShip);
                    }
                }
            }
        }
        player.increaseScore(score);
    }

    private void updateLasers() {
        for(Laser laser : lasers)
            laser.update();
    }

    private void updateSpaceShips() {
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.update();
            if(spaceShip.laserIsCharged()){
                lasers.add(spaceShip.shootLaser(spaceShip.getLaserImage(), 8));
            }
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

    private void removeSpaceShipsOutsideOfScreen(){
        for(SpaceShip spaceShip : new ArrayList<>(spaceShips))
            if(spaceShip.gameObjectLeavesScreen())
                spaceShips.remove(spaceShip);
    }

    private void removeLasersOutsideOfScreen(){
        for (Laser laser : new ArrayList<>(lasers))
            if (laser.gameObjectLeavesScreen())
                lasers.remove(laser);
    }

    //SppressLint bedeutet, dass es die Warnung des Elements ignorieren soll.
    private void printInformationOnCanvas(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);

        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawText("Hello", xPos, yPos, paint);

        canvas.drawText("PlayerHealth: "+player.getHealth()+" Score: "+player.getScore(),10,40, paint);
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
                Position invaderPosition = new Position(getRandomSpawnPoint(),0);
                Velocity invaderVelocity = new Velocity(5,3);
                spaceShips.add(new Invader(context, invaderPosition, invaderVelocity,50, 3));
                remainingEnemies--;
            }else{
                enemyTimer--;
            }
        }
    }

    public void receiveUserInput(int x, int y){
        SpaceShip spaceShip = getPlayerAsSpaceShip();
        Position playerPosition = new Position(x - spaceShip.getImage().getWidth() / 2, spaceShip.getPosition().getY());
        spaceShip.setPosition(playerPosition);
    }

    private SpaceShip getPlayerAsSpaceShip(){
        for(SpaceShip spaceShip: spaceShips){
            if(spaceShip.isPlayer())
                return spaceShip;
        }
        return null;
    }

    private int getRandomSpawnPoint(){
        return (int) (Math.random() * (screenWidth - BitmapFactory.decodeResource(context.getResources(), R.drawable.invader).getWidth()));
    }

    private int getSpawnDirection(int xVelocity){
        if (flyRight())
            return xVelocity;
        else
            return -xVelocity;
    }

    private boolean flyRight(){
        return Math.random() < 0.5;
    }
}
