package com.madproject.spaceinvaders.models.components;

public class Velocity {

    private int xVelocity;
    private int yVelocity;

    public Velocity(int xVelocity, int yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                '}';
    }
}
