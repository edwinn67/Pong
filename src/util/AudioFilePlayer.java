package util;


import javax.sound.sampled.*;
import java.io.*;


public class AudioFilePlayer extends Thread {


    public static synchronized void playSound(String path) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    AudioInputStream inputStream =
                            AudioSystem.getAudioInputStream(
                                    getClass().getResourceAsStream(path));
                    Clip clip = AudioSystem.getClip();

                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public void PlaySound(String filename) {
        try (InputStream in = getClass().getResourceAsStream(filename)) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
