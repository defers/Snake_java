package com.defers.Snake;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Seed extends RectangleGameObject{
    public Seed(double x, double y, Color color, boolean isInteractive, double width, double height) {
        super(x, y, color, isInteractive, width, height);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);

        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(x, y, width, height));

    }

}
