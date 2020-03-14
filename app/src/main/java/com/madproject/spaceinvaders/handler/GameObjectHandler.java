package com.madproject.spaceinvaders.handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import com.madproject.spaceinvaders.CollisionDetectorSystem;
import com.madproject.spaceinvaders.MenuBarHandler;
import com.madproject.spaceinvaders.R;
import com.madproject.spaceinvaders.Rescaler;
import com.madproject.spaceinvaders.db.DatabaseManipulator;
import com.madproject.spaceinvaders.db.FirebaseHelper;
import com.madproject.spaceinvaders.models.components.Position;
import com.madproject.spaceinvaders.models.components.Velocity;
import com.madproject.spaceinvaders.models.lasers.Laser;
import com.madproject.spaceinvaders.models.ships.Invader;
import com.madproject.spaceinvaders.models.ships.Player;
import com.madproject.spaceinvaders.models.ships.SpaceShip;

import java.util.ArrayList;

public class GameObjectHandler {

    private ArrayList<SpaceShip> spaceShips = new ArrayList<>();
    private ArrayList<Laser> lasers = new ArrayList<>();
    private boolean running = true;
    private Bitmap grey_bar;

    private DatabaseManipulator databaseManipulator;
    private MenuBarHandler menuBarHandler;
    private Context context;
    private int waveCounter;
    private final int waveCounterTimeMS = 90;
    private int remainingWaveCounterTimeMS = waveCounterTimeMS;
    private boolean isTextPrinted = false;
    private int totalAmountOfEnemies;
    private int remainingEnemies;
    private int enemyTimer = 100;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private Rescaler rescaler;

    private Bitmap background;
    private FirebaseHelper firebaseHelper;

    private CollisionDetectorSystem detectorSystem;
    private SoundHandler soundHandler;

    private Player player;

    public GameObjectHandler(Context context){
        this.context = context;
        waveCounter = 1;
        rescaler = new Rescaler();

        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.space);
        totalAmountOfEnemies = 5;
        remainingEnemies = totalAmountOfEnemies;

        firebaseHelper = new FirebaseHelper(context);
        soundHandler = new SoundHandler(context);
        detectorSystem = new CollisionDetectorSystem();

        Velocity playerVelocity = new Velocity(0, 0);
        Position playerPosition = new Position(screenWidth / 2, screenHeight - 250);
        player = new Player(context, playerPosition, playerVelocity,  20, 5);
        spaceShips.add(player);
        menuBarHandler = new MenuBarHandler(context, player);
    }

    public void update(){
        menuBarHandler.update();
        if(running){
            spawnEnemies();
            removeSpaceShipsOutsideOfScreen();
            removeLasersOutsideOfScreen();

            updateSpaceShips();
            updateLasers();

            int score = 0;
            for(SpaceShip spaceShip : new ArrayList<>(spaceShips)){
                for (Laser laser : new ArrayList<>(lasers)){
                    if (detectorSystem.isColliding(spaceShip,laser)){
                        spaceShip.dropHealth(laser.getDamage());
                        lasers.remove(laser);
                        if(spaceShip.isDying()){
                            if(spaceShip.isPlayer()){
                                saveGameScore();
                                firebaseHelper.uploadScore(waveCounter,player.getScore());
                                //firebaseHelper.uploadScoreToFirebase(waveCounter, player.getScore());
                                ((Activity)context).runOnUiThread(new Runnable() {   // Use the context here
                                    @Override
                                    public void run() {
                                        showInformationSavedDialog();
                                    }
                                });
                                running = false;
                            }else{
                                //soundHandler.playExplosionSound();
                                score+=spaceShip.getKillPoints();
                                spaceShips.remove(spaceShip);
                            }
                        }
                    }
                }
            }
            player.increaseScore(score);
        }
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
                if(spaceShip.isPlayer()){
                    soundHandler.playShootSound();
                }
            }
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(background, 0, 0, null);
        if(isTextPrinted){
            printNextWave(canvas);
        }
        for(SpaceShip spaceShip : spaceShips){
            spaceShip.draw(canvas);
        }
        for(Laser laser : lasers){
            laser.draw(canvas);
        }
        menuBarHandler.draw(canvas);
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


    private int enemSpawnRate = 100;

    private void spawnEnemies(){
        if(remainingEnemies == 0){
            enemyTimer = 300;
            if(enemSpawnRate > 40)
                enemSpawnRate -= 5;
            waveCounter++;
            totalAmountOfEnemies *= 1.4;
            remainingEnemies = totalAmountOfEnemies;
            isTextPrinted = true;
        }else{
            if(enemyTimer == 0){
                enemyTimer = enemSpawnRate;
                Position invaderPosition = new Position(getRandomSpawnPoint(),0);
                Velocity invaderVelocity = new Velocity(getSpawnDirection(5),3);
                spaceShips.add(new Invader(context, invaderPosition, invaderVelocity,50, 3));
                remainingEnemies--;
            }else{
                enemyTimer--;
            }
        }
    }

    private void printNextWave(Canvas canvas) {
        if(remainingWaveCounterTimeMS > 0){
            remainingWaveCounterTimeMS--;
            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(100);
            paint.setColor(Color.WHITE);

            int xPos = (canvas.getWidth() / 2);
            int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
            String waveText = context.getResources().getString(R.string.wave_text);
            canvas.drawText(waveText+ " " +waveCounter, xPos, yPos, paint);
        }else{
            remainingWaveCounterTimeMS = waveCounterTimeMS;
            isTextPrinted = false;
        }

    }

    public void receiveUserInput(int x){
        SpaceShip spaceShip = getPlayerAsSpaceShip();
        assert spaceShip != null;
        Position playerPosition = new Position(x - spaceShip.getImage().getWidth() / 2, spaceShip.getPosition().getY());
        spaceShip.setPosition(playerPosition);
    }

    private void showInformationSavedDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        String reached_score_text = context.getResources().getString(R.string.dying_text);
        String wanna_retry = context.getResources().getString(R.string.do_you_want_to_retry);
        builder.setMessage(reached_score_text+player.getScore()+"\n"+wanna_retry);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        ((Activity)context).finish();
                    }
                });
        builder.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        restartGameActivity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void saveGameScore() {
        this.databaseManipulator = new DatabaseManipulator(context);
        this.databaseManipulator.insertResult(waveCounter, player.getScore());
    }

    private SpaceShip getPlayerAsSpaceShip(){
        for(SpaceShip spaceShip: spaceShips){
            if(spaceShip.isPlayer())
                return spaceShip;
        }
        return null;
    }

    private int getRandomSpawnPoint(){
        return (int) (Math.random() * (screenWidth - BitmapFactory.decodeResource(context.getResources(), R.drawable.invader_1).getWidth()));
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

    private void restartGameActivity(){
        Intent intent = ((Activity) context).getIntent();
        ((Activity) context).finish();
        context.startActivity(intent);
    }
}
