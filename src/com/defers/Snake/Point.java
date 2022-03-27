package com.defers.Snake;

public class Point {

    public double x;
    public double y;
    public int directionX;
    public int directionY;

    public Point(double x, double y, int directionX, int directionY) {

        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;

    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", directionX=" + directionX +
                ", directionY=" + directionY +
                '}';
    }
}
