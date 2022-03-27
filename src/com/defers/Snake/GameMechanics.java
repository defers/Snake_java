package com.defers.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMechanics {

    public static Map getRandCoords(double limitX, double limitY, ArrayList<GameObject> gameObjects) {

        int cellWidth = Settings.CELL_WIDTH;
        int cellHeight = Settings.CELL_HEIGHT;

        Map<String, Double> randCoords = new HashMap();

        randCoords.put("x", rnd(limitX - cellWidth));
        randCoords.put("y", rnd(limitY - cellHeight));

        return randCoords;

    }

    public static double rnd(final double max)
    {
        return Math.random() * max;
    }


    public static boolean checkCollisionsObjects(GameObject gameObject, GameObject otherObject, ArrayList<GameObject> gameObjects) {

        boolean isCollision = false;

        ArrayList<GameObject> collisionList = checkCollisions(gameObject, gameObjects);

        for (GameObject collisionObject : collisionList) {

            if (collisionObject == otherObject) {

                isCollision = true;
                break;
            }
        }

        return isCollision;
    }

    public static <T extends GameObject> ArrayList<T> checkCollisions(T gameObject, ArrayList<T> gameObjects) {

        ArrayList<T> collisionList = new ArrayList<T>();

        for (GameObject otherObject : gameObjects) {

            if (gameObject == otherObject) {
                continue;
            }

            if (gameObject.isInteractive && otherObject.isInteractive
                    && gameObject.isActive && otherObject.isActive) {

                boolean collisionX = false;
                boolean collisionY = false;

                collisionX = (gameObject.x <= otherObject.x && (gameObject.x + gameObject.width) >= otherObject.x)
                        || (gameObject.x >= otherObject.x && gameObject.x <= otherObject.x + otherObject.width);
                collisionY = (gameObject.y <= otherObject.y && (gameObject.y + gameObject.height) >= otherObject.y)
                        || (gameObject.y >= otherObject.y && gameObject.y <= otherObject.y + otherObject.height);

                if (collisionX && collisionY) {

                    collisionList.add((T) otherObject);
                }
            }

        }

        return collisionList;

    }

}
