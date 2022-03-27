package com.defers.Snake;

import java.awt.*;
import java.util.ArrayList;

public class TaleLink extends RectangleGameObject implements Moveable {

    Moveable master;
    double speed;
    private int directionX;
    private int directionY;
    double prevTime = System.nanoTime() / 1.0E06f;;

    ArrayList<GameObject> gameObjects;

    ArrayList<Point> points = new ArrayList<Point>();

    public TaleLink(double x, double y, Color color, boolean isInteractive,
                    double width, double height, Moveable master, ArrayList<GameObject> gameObjects) {
        super(x, y, color, isInteractive, width, height);

        this.master = master;
        this.gameObjects = gameObjects;
        init();

    }

    @Override
    public void init() {
        super.init();

        directionX = master.getDirectionX();
        directionY = master.getDirectionY();
        speed = master.getSpeed();
        x = master.getX() -  (directionX * (width) );
        y = master.getY() - (directionY * (height) );

        master.getPoints().clear();
    }

    @Override
    public void update() {
        super.update();

        move();
    }

    @Override
    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public void move() {

        double currentTime;
        double deltaTime;

        ArrayList<Point> masterPoints = master.getPoints();

        if (masterPoints.size() > 0) {

            currentTime = System.nanoTime() / 1.0E06f;

            deltaTime = currentTime - prevTime;


            if (deltaTime >= 16) {

                Point masterPoint = masterPoints.get(0);

                boolean isCollision = GameMechanics.checkCollisionsObjects(this, (GameObject) master, gameObjects);

                if (!isCollision) {

                    x = masterPoint.x;
                    y = masterPoint.y;

                    directionX = masterPoint.directionX;
                    directionY = masterPoint.directionY;

                    points.add(new Point(x, y, directionX, directionY));

                    masterPoints.remove(masterPoint);
                }

                prevTime = currentTime;
            }
        }

    }

    @Override
    public int getDirectionX() {
        return directionX;
    }

    @Override
    public int getDirectionY() {
        return directionY;
    }

    @Override
    public void setDirectionX(int direction) {

        this.directionX = direction;

    }

    @Override
    public void setDirectionY(int direction) {

        this.directionY = direction;

    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
