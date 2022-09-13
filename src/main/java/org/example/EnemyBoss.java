package org.example;

import javax.swing.*;
import java.awt.*;

public class EnemyBoss {
    int x;
    int y;
    int v;

    Image imgEnemyGreen=new ImageIcon("res/enemyboss.gif").getImage();

    Road road;
    public Rectangle getRect(){
        return new Rectangle(x,y,65,243);
    }


    public EnemyBoss(int x,int y,int v,Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }

    public void move(){
        y=y+road.p.v-v;
    }
}
