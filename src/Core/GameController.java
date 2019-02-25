package Core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController
        implements KeyListener, ActionListener {

    // properties
    Timer timer = new Timer(20, this);

    public GameController() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //        System.out.println("Running");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
