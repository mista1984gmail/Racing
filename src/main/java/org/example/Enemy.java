package org.example;

import javax.swing.*;
import java.awt.*;

public class Enemy extends ObjectInRoad{

    Image imgEnemyGreen=new ImageIcon("res/enemy.gif").getImage();
    Image imgEnemyRed=new ImageIcon("res/enemy2.gif").getImage();

    public Rectangle getRect(){
        return new Rectangle(x,y,50,88);
    }

    public Enemy(int x,int y,int v,Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }
}
