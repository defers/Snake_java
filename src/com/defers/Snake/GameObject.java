package com.defers.Snake;

import java.awt.*;

public class GameObject {

    double x;
    double y;
    double width;
    double height;
    Color color;
    boolean isInteractive;
    boolean isActive = true;

    public GameObject(double x, double y, Color color, boolean isInteractive, double width, double height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.color = color;
        this.isInteractive = isInteractive;

    }

    public void init() {

        //needs realization

    }

    public void update() {


    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        render(g2d);

    }

    public void render(Graphics2D g2d) {

    }


    public void setActive(boolean active) {

        this.isActive = active;

    }

}

