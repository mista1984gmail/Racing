package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Road  extends JPanel implements ActionListener, Runnable{
    String name="";
    Timer mainTimer=new Timer(20,this);//обновление
    Image imgRoad = new ImageIcon("res/road.gif").getImage();//изображение дороги
    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new AudioThread());
    List<Enemy>enemyListRed=new ArrayList<Enemy>();
    List<Enemy>enemyListGreen=new ArrayList<Enemy>();



    Player p=new Player();

    public Road(){
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }
    @Override
    public void run(){
        while (true){
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemyListRed.add(new Enemy(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                enemyListGreen.add(new Enemy(rand.nextInt(500),-700,15+rand.nextInt(15),this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g){
        g=(Graphics2D) g;

        g.drawImage(imgRoad,0,p.layer1,null);
        g.drawImage(imgRoad,0,p.layer2,null);
        g.drawImage(p.imgPlayer,p.x,p.y,null);

        Iterator<Enemy>iterator=enemyListRed.iterator();
        while (iterator.hasNext()){
            Enemy e =iterator.next();
            if (e.y>=1400 || e.y<=-1400 ){
                iterator.remove();
            }else {
                e.move();
                g.drawImage(e.imgEnemyRed, e.x, e.y, null);}
        }
        Iterator<Enemy>iterator1=enemyListGreen.iterator();
        while (iterator1.hasNext()){
            Enemy e =iterator1.next();
            if (e.y>=1400 || e.y<=-1400 ){
                iterator1.remove();
            }else {
                e.move();
                g.drawImage(e.imgEnemyGreen, e.x, e.y, null);}
        }
        double v=(200/ Player.MAX_V)*p.v;
        g.setColor(Color.GREEN);
        Font font = new Font ("Arial",Font.ITALIC,20);
        g.setFont(font);
        g.drawString("Скорость "+ v + " км/ч",100,30);

        double l=p.s/1000;
        g.setColor(Color.GREEN);
        Font font1 = new Font ("Arial",Font.ITALIC,20);
        g.setFont(font1);
        g.drawString(name + " проехал "+ l + "км из 100 км",100,60);

    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy>i=enemyListGreen.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){
                p.v-=5;
            }
        }
        Iterator<Enemy>i1=enemyListRed.iterator();
        while (i1.hasNext()){
            Enemy e = i1.next();
            if (p.getRect().intersects(e.getRect())){
                p.v-=5;
            }
        }
    }

    private void testWin() {
        if (p.s>100000){
            JOptionPane.showMessageDialog(null,"Вы выиграли!!! =))");
            System.exit(0);
        }
    }

    private void setPlayerName() {
        if(name.isEmpty()){
        name=JOptionPane.showInputDialog("Введите свое имя!!!");
        JOptionPane.showMessageDialog(null, "Привет " + name);}

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        setPlayerName();
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }
    private class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            p.keyPressed(e);
        }
        @Override
        public void keyReleased (KeyEvent e){
            p.keyReleased(e);
        }

    }
}
