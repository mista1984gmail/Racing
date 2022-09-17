package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EnemyBoss extends ObjectInRoad{
    int damage =2;
    int speedReduction=15;
    Image img=new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("enemyboss.gif"))).getImage();
    //Image img=new ImageIcon("enemyboss.gif").getImage();
    public Rectangle getRect(){
        return new Rectangle(x,y,65,243);
    }

    public EnemyBoss(int x,int y,int v,Road road){
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeedReduction(int speedReduction) {
        this.speedReduction = speedReduction;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeedReduction() {
        return speedReduction;
    }
}
