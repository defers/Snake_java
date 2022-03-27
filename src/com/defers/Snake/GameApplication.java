package com.defers.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class GameApplication implements Runnable {

    GameWindow gameWindow;
    Graphics canvas;
    boolean gameRunning;
    Renderer renderer;
    ArrayList<GameObject> gameObjects;
    KL kl;
    Player player;
    boolean win = false;
    double deltaTime;


    GameApplication(GameWindow gameWindow, Graphics canvas, KL kl) {

      this.gameWindow = gameWindow;
      this.gameRunning = true;
      this.canvas = canvas;
      this.kl = kl;

    }

    private Thread createAnimationThread() {

        Thread animThread = new Thread(new Runnable() {

            @Override
            public void run() {
                for (final GameObject gameObject : gameObjects) {
                    if (gameObject instanceof Line) {

                        gameObject.color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

                        renderer.render(gameObjects);

                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        });

        return animThread;
    }

    @Override
    public void run() {

        Thread animThread = createAnimationThread();

        if (animThread.isAlive()) {

            animThread.interrupt();

        }

        renderer = new Renderer(canvas, gameWindow);

        initGameObjects();

        double prevTime = System.nanoTime() / 1.0E06f;;
        double currentTime;

        while (gameRunning) {

            currentTime = System.nanoTime() / 1.0E06f;

            deltaTime = currentTime - prevTime;

            update(deltaTime);
            renderer.render(gameObjects);

            try {
                Thread.sleep(1000 / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (!gameRunning) {

                animThread.start();


        }

        while (!gameRunning) {

            restart();

            }

            try {
                Thread.sleep(1000 / 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    private void initGameObjects() {

        gameObjects = new ArrayList<GameObject>();
        Collections.synchronizedList(gameObjects);

        RectangleGameObject background = new RectangleGameObject(0, 0, Color.black, false,
                                                                    Settings.WIDTH, Settings.HEIGHT);
        gameObjects.add(background);

        createLines();

        Map<String, Double> randCoords = GameMechanics.getRandCoords(Settings.WIDTH, Settings.HEIGHT, gameObjects);

        player = new Player(randCoords.get("x"), randCoords.get("y"), Color.ORANGE, true,
               Settings.CELL_WIDTH, Settings.CELL_HEIGHT, gameObjects);
        gameObjects.add(player);

        randCoords = GameMechanics.getRandCoords(Settings.WIDTH, Settings.HEIGHT, gameObjects);

        Seed seed = new Seed(randCoords.get("x"), randCoords.get("y"), Color.GREEN, true,
                Settings.CELL_WIDTH, Settings.CELL_HEIGHT);

        gameObjects.add(seed);

        UI ui = new UI(Settings.CELL_WIDTH, Settings.CELL_HEIGHT, Color.WHITE, false,
                Settings.CELL_WIDTH, Settings.CELL_HEIGHT, this);

        gameObjects.add(ui);

    }

    private void update(double deltaTime) {

        for (GameObject gameObject : gameObjects) {

            gameObject.update();

        }

        addRemoveSeeds();
        checkWin();
        checkLoose();

    }

    private void checkLoose() {

        if (!player.isAlive) {

            gameRunning = false;
            win = false;
            Stats.currentSpeed = Settings.SPEED;
            Stats.level = 1;

        }
    }

    private void checkWin() {

        if (Stats.getSeedsQuantity() == Settings.seedsForWin) {

            gameRunning = false;
            win = true;
            Stats.currentSpeed += Settings.SPEED_INCREASE;
            Stats.level += 1;

        }

    }

    private void restart() {

        if (KL.isKeyPressed(KeyEvent.VK_SPACE)) {
            win = false;
            gameRunning = true;
            Stats.setSeedsQuantity(0);
            run();
        }
    }


    private void addRemoveSeeds() {

        int seedQuantity = 0;

        for (GameObject gameObject : gameObjects) {

            if (!gameObject.isActive) {

                if (gameObject instanceof Seed) {

                    TaleLink tl;

                    if (player.taleLink.size() == 0) {

                        tl = new TaleLink(player.x, player.y, Color.white,
                                true, player.width, player.height, player, gameObjects);

                    }
                    else {

                        TaleLink master = player.taleLink.get(player.taleLink.size() - 1);
                        tl = new TaleLink(master.x, master.y, Color.white,
                                true, master.width, master.height, master, gameObjects);


                    }

                    player.taleLink.add(tl);
                    gameObjects.add(tl);
                    gameObjects.remove(gameObject);
                }

                break;

            }

            if (gameObject instanceof Seed) {

                seedQuantity += 1;
                break;

            }

        }

        if (seedQuantity == 0) {

            Map<String, Double> randCoords = GameMechanics.getRandCoords(Settings.WIDTH, Settings.HEIGHT, gameObjects);

            Seed seed = new Seed(randCoords.get("x"), randCoords.get("y"), Color.GREEN, true,
                    Settings.CELL_WIDTH, Settings.CELL_HEIGHT);

            gameObjects.add(seed);

        }


    }

    private void createLines() {

        int cellNumbers = Settings.CELL_NUMBERS;

        double x = 0;
        double y = 0;

        for (int i = 0; i < cellNumbers; i++) {

            x += Settings.CELL_WIDTH;

            Line line = new Line(x, y, Color.GRAY, false, x, Settings.HEIGHT);
            gameObjects.add(line);
        }

        x = 0;
        y = 0;

        for (int i = 0; i < cellNumbers; i++) {

            y += Settings.CELL_HEIGHT;

            Line line = new Line(x, y, Color.GRAY, false, Settings.WIDTH, y);
            gameObjects.add(line);
        }

    }
}
