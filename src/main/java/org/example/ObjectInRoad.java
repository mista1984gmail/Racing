package org.example;


import java.awt.*;

public abstract class ObjectInRoad {
    int x;
    int y;
    int v;
    Road road;
    Image img;
    int damage;
    int speedReduction;
    public Rectangle getRect(){
        return new Rectangle(x,y,0,0);
    }

    public void move(){
        y=y+road.p.v-v;
    }

    public Image getImg() {
        return img;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeedReduction() {
        return speedReduction;
    }
}
