package org.example;

public class TimeResult implements Runnable{
    int time=0;
    public int timeRes(int time){
        time=0;
        while (true){
            try {
                Thread.sleep(1000);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return time;
        }

    }

    @Override
    public void run() {
        timeRes(0);
    }
}
