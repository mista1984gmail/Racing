package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Life extends ObjectInRoad{
    Image img=new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("heart.gif"))).getImage();
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
