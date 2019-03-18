package util;


import javax.sound.sampled.*;
import java.io.*;


public class AudioFilePlayer extends Thread {


    public static synchronized void playSound(String path) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {

                    //read audio data from whatever source (file/classloader/etc.)
//                    InputStream audioSrc = getClass().getResourceAsStream(path);
//add buffer for mark/reset support
//                    InputStream bufferedIn = new BufferedInputStream(audioSrc);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));

                    Clip clip = AudioSystem.getClip();
                    /*AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream(path));*/
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
