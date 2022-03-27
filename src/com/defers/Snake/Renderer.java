package com.defers.Snake;

import java.awt.*;
import java.util.ArrayList;


public class Renderer {

    Graphics2D canvas;
    GameWindow gameWindow;

    public Renderer(Graphics canvas, GameWindow gameWindow) {

        this.canvas = (Graphics2D) canvas;
        this.gameWindow = gameWindow;

    }

    private void init() {

    }

    public void render(ArrayList<GameObject> gameObjects) {

        Image img = gameWindow.createImage(gameWindow.getWidth(), gameWindow.getHeight());

        Graphics imageCanvas = img.getGraphics();

        for (GameObject gameObject : gameObjects) {

            if (gameObject.isActive) {

                gameObject.render(imageCanvas);

            }

        }

        canvas.drawImage(img, 0,0, gameWindow);
    }



}
