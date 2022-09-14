package org.example;

import javax.swing.*;
import java.awt.*;

public class Life extends ObjectInRoad{

    Image img=new ImageIcon("res/heart.gif").getImage();

    public Rectangle getRect(){
        return new Rectangle(x,y,25,23);
    }

    public Life(int x,int y,int v,Road road){
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
}
