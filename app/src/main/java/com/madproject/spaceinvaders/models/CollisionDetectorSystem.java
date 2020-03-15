package com.madproject.spaceinvaders.models;

import com.madproject.spaceinvaders.models.components.CollisionBox;
import com.madproject.spaceinvaders.models.components.Position;
import com.madproject.spaceinvaders.models.lasers.Laser;
import com.madproject.spaceinvaders.models.ships.SpaceShip;

public class CollisionDetectorSystem {


    public CollisionDetectorSystem(){

    }

    public boolean isColliding(SpaceShip spaceShip, Laser laser){
        Position p1 = laser.getPosition();
        Position p2 = new Position(laser.getPosition().getX()+laser.getCollisionBox().getWidth(),laser.getPosition().getY());
        Position p3 = new Position(laser.getPosition().getX(), laser.getPosition().getY()+laser.getCollisionBox().getHeight());
        Position p4 = new Position(laser.getPosition().getX()+laser.getCollisionBox().getWidth(),laser.getPosition().getY()+laser.getCollisionBox().getHeight());
        return ( spaceShip.isPlayer() != laser.isPlayerLaser() && (between(p1,spaceShip.getPosition(), spaceShip.getCollisionBox())
                || between(p2, spaceShip.getPosition(), spaceShip.getCollisionBox())
                || between(p3, spaceShip.getPosition(), spaceShip.getCollisionBox())
                || between(p4, spaceShip.getPosition(), spaceShip.getCollisionBox())));
    }

    private boolean between(Position point, Position recPos, CollisionBox recBox){
        return point.getX() >= recPos.getX()
                && point.getX() < recPos.getX() + recBox.getWidth()
                && point.getY() >= recPos.getY()
                && point.getY() < recPos.getY() + recBox.getHeight();
    }
}
