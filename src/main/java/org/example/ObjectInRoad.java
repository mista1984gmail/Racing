package org.example;


import java.awt.*;

public abstract class ObjectInRoad {
    int x;
    int y;
    int v;
    Road road;

    public void move(){
        y=y+road.p.v-v;
    }
}
