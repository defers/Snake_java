package com.defers.Snake;

import java.util.ArrayList;

public interface Moveable {

    void move();

    int getDirectionX();

    int getDirectionY();

    void setDirectionX(int direction);

    void setDirectionY(int direction);

    double getSpeed();

    double getX();

    double getY();

    ArrayList<Point> getPoints();

}
