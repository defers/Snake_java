package com.defers.Snake;

import java.awt.*;

public class UI extends GameObject {

    GameApplication gameApplication;

    public UI(double x, double y, Color color, boolean isInteractive, double width, double height, GameApplication gameApplication) {
        super(x, y, color, isInteractive, width, height);

        this.gameApplication = gameApplication;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(Color.WHITE);
        Font font = new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, Settings.CELL_HEIGHT);
        g.setFont(font);
        g.drawString("Score: " + Stats.getSeedsQuantity() + " / " + Settings.seedsForWin, (int) (Settings.CELL_WIDTH / 1.5), (int) 2 * Settings.CELL_HEIGHT);
        g.drawString("Level: " + Stats.level, (int) (Settings.CELL_WIDTH / 1.5), (int) 3 * Settings.CELL_HEIGHT);
        g.drawString("Speed: " + Stats.currentSpeed, (int) (Settings.CELL_WIDTH / 1.5), (int) 4 * Settings.CELL_HEIGHT);

        if (!gameApplication.gameRunning) {

            String message;
            if (gameApplication.win) {

                message = "YOU WIN! Press SPACE to continue";
            }

            else {

                message = "YOU LOOSE! Press SPACE to restart";

            }

            g.drawString(message, 70, Settings.HEIGHT / 2);

        }
    }

}
