package com.defers.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {

    static boolean[] isKeyPressed = new boolean[128];
    static boolean[] isKeyTyped = new boolean[128];

    @Override
    public void keyTyped(KeyEvent e) {

        isKeyTyped = new boolean[128];

        isKeyTyped[e.getKeyCode()] = true;

    }

    @Override
    public void keyPressed(KeyEvent e) {

        isKeyPressed[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        isKeyPressed[e.getKeyCode()] = false;

    }

    public static boolean isKeyPressed(int keyCode) {

        return isKeyPressed[keyCode];

    }

    public static boolean isKeyTyped(int keyCode) {

        return isKeyTyped[keyCode];

    }

}
