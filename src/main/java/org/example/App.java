package org.example;

import javax.swing.*;
import java.awt.*;

public class App 
{
    public static void main(String[] args) {

        JFrame frame=new JFrame("Fast Racing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.add(new Road());
        frame.setVisible(true);
    }
}
