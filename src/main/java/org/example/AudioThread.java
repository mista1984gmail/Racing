package org.example;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioThread implements Runnable{
    public void run(){
        try {
            Player p = new Player(new FileInputStream(getClass()
                    .getClassLoader().getResource("song.mp3").getPath()));
            p.play();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JavaLayerException e){
            e.printStackTrace();
        }
    }
}
