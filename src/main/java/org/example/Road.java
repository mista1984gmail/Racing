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
    Font font = new Font ("Arial",Font.ITALIC,20);
    Timer mainTimer=new Timer(20,this);//обновление
    Image imgRoad = new ImageIcon("res/road.gif").getImage();//изображение дороги
    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new AudioThread());
    List<Enemy>enemyListRed=new ArrayList<Enemy>();
    List<Enemy>enemyListGreen=new ArrayList<Enemy>();
    List<EnemyBoss>enemyListBoss=new ArrayList<EnemyBoss>();
    List<Life>lifeList=new ArrayList<Life>();

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
                Thread.sleep(rand.nextInt(5000));
                enemyListRed.add(new Enemy(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                enemyListGreen.add(new Enemy(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                lifeList.add(new Life(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                if (p.s > 150000){
                    enemyListBoss.add(new EnemyBoss(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                }
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


        Iterator<EnemyBoss>iterator3=enemyListBoss.iterator();
        while (iterator3.hasNext()){
            EnemyBoss e =iterator3.next();
            if (e.y>=1400 || e.y<=-1400 ){
                iterator3.remove();
            }else {
                e.move();
                g.drawImage(e.imgEnemyGreen, e.x, e.y, null);}
        }
        Iterator<Life>iterator2=lifeList.iterator();
        while (iterator2.hasNext()){
            Life e =iterator2.next();
            if (e.y>=1400 || e.y<=-1400 ){
                iterator2.remove();
            }else {
                e.move();
                g.drawImage(e.imgHeart, e.x, e.y, null);}
        }
        double v=(200/ Player.MAX_V)*p.v;
        g.setColor(Color.GREEN);
        g.setFont(font);
        g.drawString("Скорость "+ v + " км/ч",100,30);

        double l=p.s/1000;
        g.setColor(Color.GREEN);
        g.setFont(font);
        g.drawString(name + " проехал "+ l + "км из 200 км",100,60);

        g.setColor(Color.ORANGE);
        g.setFont(font);
        g.drawString("Время: "+ p.stopWatch,300,30);

        int life=p.l;
        if(life<50){
        g.setColor(Color.GREEN);
        g.setFont(font);
        g.drawString("ПОВРЕЖДЕНИЯ: "+ life + " % ",100,90);}
        else {
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("ПОВРЕЖДЕНИЯ: "+ life + " % ",100,90);
        }

    }

    private void paintObject(Graphics g, List<ObjectInRoad> enemyListRed, Image p) {

        Iterator<ObjectInRoad> iterator = enemyListRed.iterator();
        while (iterator.hasNext()) {
            ObjectInRoad e = iterator.next();
            if (e.y >= 1400 || e.y <= -1400) {
                iterator.remove();
            } else {
                e.move();
                g.drawImage(p, e.x, e.y, null);
            }
        }
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy>i=enemyListGreen.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){
                p.v-=5;
                p.l+=1;
            }
        }
        Iterator<Enemy>i1=enemyListRed.iterator();
        while (i1.hasNext()){
            Enemy e = i1.next();
            if (p.getRect().intersects(e.getRect())){
                p.v-=5;
                p.l+=1;
            }
        }
        Iterator<EnemyBoss>i3=enemyListBoss.iterator();
        while (i3.hasNext()){
            EnemyBoss e = i3.next();
            if (p.getRect().intersects(e.getRect())){
                p.v-=15;
                p.l+=2;
            }
        }
    }

    private void testCollisionWithLife() {
        Iterator<Life>i=lifeList.iterator();
        while (i.hasNext()){
            Life e = i.next();
            if (p.getRect().intersects(e.getRect())){
                p.l-=10;
                if(p.l<0){
                    p.l=0;
                }
                i.remove();
            }
        }
    }

    private void testWin() {
        if (p.s>200000){
            JOptionPane.showMessageDialog(null,"Вы выиграли!!! =))" + " Ваше время: " + p.stopWatch);
            System.exit(0);
        }
    }

    private void testLose() {
        if (p.l>100){
            JOptionPane.showMessageDialog(null,"Вы проиграли!!! =))");
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
        testCollisionWithLife();
        testWin();
        testLose();
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
