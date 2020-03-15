package com.madproject.spaceinvaders.models.components;

/**
 * Used for the position of a GameObject.
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor of Position.
     *
     * @param x the xCoordiante of a Point.
     * @param y the yCoordinate of a Point.
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of x.
     *
     * @return x-Coordination
     */
    public int getX(){
        return x;
    }

    /**
     * Updates X by a velocity.
     *
     * @param distance the Velocity a Point is moved on the x-axis.
     */
    public void updateXByDistance(int distance){
        x += distance;
    }

    /**
     * Getter of y.
     *
     * @return y-Coordination
     */
    public int getY(){
        return y;
    }

    /**
     * Updates Yby a velocity.
     *
     * @param distance the Velocity a Point is moved on the y axis.
     */
    public void updateYByDistance(int distance){
        y += distance;
    }

    public void updatePosition(int xVelocity, int yVelocity) {
        this.x += xVelocity;
        this.y += yVelocity;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
