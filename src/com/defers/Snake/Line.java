package com.defers.Snake;

import java.awt.*;

public class Line extends GameObject{

    double x2, y2;

    public Line(double x, double y, Color color, boolean isInteractive, double x2, double y2) {
        super(x, y, color, isInteractive, x2, y2);

        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(color);
        g2d.drawLine((int) x, (int) y, (int) x2, (int) y2);

    }
}
