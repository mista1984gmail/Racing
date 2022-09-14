package org.example;

import javax.swing.*;
import java.awt.*;

public class EnemyGreen extends ObjectInRoad{
    int damage =1;
    int speedReduction=5;
    Image img=new ImageIcon("res/enemy.gif").getImage();

    public Rectangle getRect(){
        return new Rectangle(x,y,50,88);
    }

    public EnemyGreen(int x, int y, int v, Road road){
        this.x=x;
        this.y=y;
        this.v=v;
        this.road=road;
    }
    public void setImg(Image img) {
        this.img = img;
    }
    public Image getImg() {
        return img;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeedReduction() {
        return speedReduction;
    }

    public void setSpeedReduction(int speedReduction) {
        this.speedReduction = speedReduction;
    }
}
