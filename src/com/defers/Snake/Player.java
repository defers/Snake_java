package com.defers.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends RectangleGameObject implements Moveable {

    double speed = Stats.currentSpeed;
    private int directionX;
    private int directionY;
    public boolean isAlive = true;
    double prevTime = System.nanoTime() / 1.0E06f;;

    ArrayList<Point> points = new ArrayList<Point>();

    ArrayList<Seed> seeds = new ArrayList<Seed>();
    ArrayList<TaleLink> taleLink = new ArrayList<TaleLink>();
    ArrayList<GameObject> gameObjects;


    public Player(double x, double y, Color color, boolean isInteractive,
                  double width, double height, ArrayList<GameObject> gameObjects) {
        super(x, y, color, isInteractive, width, height);

        this.gameObjects = gameObjects;

    }

    @Override
    public void update() {
        super.update();

        move();
        eatSeed();

    }

    private void eatSeed() {

        ArrayList<GameObject> collisionList = GameMechanics.checkCollisions(this, gameObjects);

        for (GameObject otherObject : collisionList) {

            if (otherObject instanceof Seed) {

                otherObject.setActive(false);
                seeds.add((Seed) otherObject);
                Stats.setSeedsQuantity(seeds.size());

                if (taleLink.size() == 0) {

                    points.clear();

                }

            }

            if (otherObject instanceof TaleLink
                    && ((TaleLink) otherObject).master != this) {

                isAlive = false;

            }

        }

    }

    @Override
    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public void move() {

        double currentTime;
        double deltaTime;

        if (x >= 0 && x + width  <= Settings.WIDTH) {

            x += speed * directionX;

            if (directionX < 0) {

                x = Math.max(x, 0 + 1);

            } else if (directionX > 0) {

                x = Math.min(x, Settings.WIDTH - width -1);

            }



        }

        if (y >= 0 && y + height <= Settings.HEIGHT) {
            y += speed * directionY;

            if (directionY < 0) {

                y = Math.max(y, 0 + 1);

            } else if (directionY > 0) {

                y = Math.min(y, Settings.HEIGHT - height -1);

            }

        }

        if (KL.isKeyPressed(KeyEvent.VK_UP) && y >= 0) {

            setDirectionY(-1);
            setDirectionX(0);

        }

        else if (KL.isKeyPressed(KeyEvent.VK_DOWN) && y + height <= Settings.HEIGHT) {

            setDirectionY(1);
            setDirectionX(0);

        }

        else if (KL.isKeyPressed(KeyEvent.VK_LEFT) && x >= 0) {

            setDirectionX(-1);
            setDirectionY(0);

        }

        else if (KL.isKeyPressed(KeyEvent.VK_RIGHT) && x + width <= Settings.WIDTH) {

            setDirectionX(1);
            setDirectionY(0);

        }

        currentTime = System.nanoTime() / 1.0E06f;

        deltaTime = currentTime - prevTime;

        if (deltaTime >= 32) {

            points.add(new Point(x, y, directionX, directionY));

            prevTime = currentTime;

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
