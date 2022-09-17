package org.example;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class Road  extends JPanel implements ActionListener, Runnable{
    String name="";
    Font font = new Font ("Arial",Font.ITALIC,20);
    Timer mainTimer=new Timer(20,this);//обновление
    //Image imgRoad = new ImageIcon("res/road.gif").getImage();//изображение дороги
    Image imgRoad=new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("road.gif"))).getImage();
    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new AudioThread());
    List<ObjectInRoad>enemyListRed=new ArrayList<>();
    List<ObjectInRoad>enemyListGreen=new ArrayList<>();
    List<ObjectInRoad>enemyListBoss=new ArrayList<>();
    List<ObjectInRoad>lifeList=new ArrayList<>();

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
                enemyListRed.add(new EnemyRed(rand.nextInt(500),-700,15+rand.nextInt(15),this));
                enemyListGreen.add(new EnemyGreen(rand.nextInt(500),-700,15+rand.nextInt(15),this));
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

        paintObject(g, enemyListRed);
        paintObject(g, enemyListGreen);
        paintObject(g, enemyListBoss);
        paintObject(g, lifeList);

        //скорость
        g.setColor(Color.GREEN);
        g.setFont(font);
        g.drawString("Скорость "+ (200/ Player.MAX_V)*p.v + " км/ч",100,30);

        //расстояние
        g.drawString(name + " проехал "+ p.s/1000 + "км из 200 км",100,60);

        //время
        g.setColor(Color.ORANGE);
        g.drawString("Время: "+ p.stopWatch,300,30);

        //повреждения
        if (p.l < 50) {
            g.setColor(Color.GREEN);
            g.drawString("ПОВРЕЖДЕНИЯ: " + p.l + " % ", 100, 90);
        } else {
            g.setColor(Color.RED);
            g.drawString("ПОВРЕЖДЕНИЯ: " + p.l + " % ", 100, 90);
        }

    }

    private void paintObject(Graphics g, List<ObjectInRoad> objectsInRoad) {
        Iterator<ObjectInRoad> iterator = objectsInRoad.iterator();
        while (iterator.hasNext()) {
            ObjectInRoad e = iterator.next();
            if (e.y >= 1400 || e.y <= -1400) {
                iterator.remove();
            } else {
                e.move();
                g.drawImage(e.getImg(), e.x, e.y, null);
            }
        }
    }

    private void testCollisionWithEnemies() {
        collision(enemyListGreen);
        collision(enemyListRed);
        collision(enemyListBoss);
    }

    private void collision(List<ObjectInRoad> enemyListBoss) {
        Iterator<ObjectInRoad> iterator = enemyListBoss.iterator();
        while (iterator.hasNext()) {
            ObjectInRoad e = iterator.next();
            if (p.getRect().intersects(e.getRect())) {
                p.v -= e.getSpeedReduction();
                p.l += e.getDamage();
            }
        }
    }

    private void testCollisionWithLife() {
        Iterator<ObjectInRoad> i = lifeList.iterator();
        while (i.hasNext()) {
            ObjectInRoad e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                p.l -= 10;
                if (p.l < 0) {
                    p.l = 0;
                }
                i.remove();
            }
        }
    }

    private void testWin() {
        if (p.s > 200000) {
            JOptionPane.showMessageDialog(null, "Вы выиграли!!! =))" + " Ваше время: " + p.stopWatch);
            System.exit(0);
        }
    }

    private void testLose() {
        if (p.l > 100) {
            JOptionPane.showMessageDialog(null, "Вы проиграли!!! =))");
            System.exit(0);
        }
    }

    private void setPlayerName() {
        try {
        if (name.isEmpty()) {
            name = JOptionPane.showInputDialog("Введите свое имя!!!");
            JOptionPane.showMessageDialog(null, "Привет " + name);
        }}
        catch (Exception e){
            e.printStackTrace();
        }

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
