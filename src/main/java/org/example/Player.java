package org.example;

import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int LIFE = 0;
    public static final int MAX_V=40;
    public static final int MAX_RIGHT=500;
    public static final int MAX_LEFT=50;


    Image imgPlayer=new ImageIcon("res/player.gif").getImage();
    int v;//скорость
    int dv;//ускорение
    int s;//расстояние
    int l=LIFE;//жизни
    StopWatch stopWatch=new StopWatch();

    public Rectangle getRect(){
        return new Rectangle(x,y,50,87);
    }
    int x=300; //расположение
    int y=450;//расположение
    int dy=0;//начальное ускорение

    int layer1=0;
    int layer2 =-750;

    public void move() {
        if (!stopWatch.isStarted()) {
            stopWatch.start();
        }
        s += v;
        v += dv;
        x -= dy;
        if (v <= 0) v = 0;//не едет в зад
        if (v >= MAX_V) v = MAX_V;
        if (x <= MAX_LEFT) x = MAX_LEFT;
        if (x >= MAX_RIGHT) x = MAX_RIGHT;
        if (layer2 + v >= 0) {
            layer1 = 0;
            layer2 = -750;
        }
        layer1 += v;
        layer2 += v;

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dy = 5;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dy = -5;
        }
        if (key == KeyEvent.VK_UP) {
            dv = 1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dv = -1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dv = 0;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dy = 0;
        }
    }
}
