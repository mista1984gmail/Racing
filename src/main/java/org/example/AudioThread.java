package org.example;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioThread implements Runnable{
    public void run(){
        try {
            javazoom.jl.player.Player p = new Player(new FileInputStream(("res/song.mp3")));
            p.play();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JavaLayerException e){
            e.printStackTrace();
        }
    }
}
