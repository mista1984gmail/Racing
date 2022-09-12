package org.example;

import javax.swing.*;
import java.awt.*;

public class Enemy {

    int x;
    int y;
    int v;

    Image imgEnemyGreen=new ImageIcon("src/main/resources/enemy.gif").getImage();
    Image imgEnemyRed=new ImageIcon("src/main/resources/enemy2.gif").getImage();
    Road road;
    public Rectangle getRect(){
        return new Rectangle(x,y,50,88);
    }


    public Enemy(int x,int y,int v,Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }



    public void move(){
        y=y+road.p.v-v;
    }
}
