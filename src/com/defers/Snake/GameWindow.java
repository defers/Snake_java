package com.defers.Snake;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    int width;
    int height;
    KL kl;
    JButton btnRestart;

    public GameWindow(int width, int height, KL kl){

        this.width = width;
        this.height = height;
        this.kl = kl;

    }

    public void showWindow() {

        initWindow();

    }

    private void initWindow() {

        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(kl);
        this.setUndecorated(true);
        this.setVisible(true);

    }

    public Graphics getCanvas() {

        Graphics g = this.getGraphics();

        return g;
    }

}
