package org.example;

import javax.swing.*;
import java.awt.*;

public class EnemyBoss extends ObjectInRoad{

    Image imgEnemyGreen=new ImageIcon("res/enemyboss.gif").getImage();

    public Rectangle getRect(){
        return new Rectangle(x,y,65,243);
    }

    public EnemyBoss(int x,int y,int v,Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }
}
