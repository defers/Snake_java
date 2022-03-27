package com.defers.Snake;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        KL kl = new KL();

        GameWindow gameWindow = new GameWindow(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, kl);
        gameWindow.showWindow();

        Graphics canvas = gameWindow.getCanvas();

        GameApplication ga = new GameApplication(gameWindow, canvas, kl);
        Thread thread = new Thread(ga);
        thread.start();

    }
}
