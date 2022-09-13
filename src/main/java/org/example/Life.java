package org.example;

import javax.swing.*;
import java.awt.*;

public class Life {
    int x;
    int y;
    int v;

    Image imgHeart=new ImageIcon("res/heart.gif").getImage();
    Road road;
    public Rectangle getRect(){
        return new Rectangle(x,y,25,23);
    }


    public Life(int x,int y,int v,Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }

    public void move(){
        y=y+road.p.v-v;
    }
}
