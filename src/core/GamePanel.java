package core;

import gamestate.Menu.MainMenu;
import gamestate.GameState;
import util.FontManager;
import util.GameStatesManager;
import util.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;


public class GamePanel extends JPanel
    implements KeyListener, ActionListener {

    // game loop variables
    private Timer timer = new Timer(5, this);
    public static LinkedList<Integer> pressedKeys = new LinkedList<>();

    // actual game state
    private GameState actualGameState;

    // constructors
    GamePanel() {
        super();
        setPreferredSize(new Dimension(
                WindowManager.getWindowWidth(),
                WindowManager.getWindowHeight()));


        timer.start();

        // should start from menu
        GameStatesManager.setGameState(new MainMenu());
        FontManager.loadFont();
    }


    @Override
    public void paint(Graphics g) {
        actualGameState.draw(g);
        actualGameState.update();
    }



    @Override
    public void keyTyped(KeyEvent e) {
        actualGameState.onKeyTyped(e.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ( !pressedKeys.contains(key) )
            pressedKeys.add(key);

        actualGameState.onKeyPressed(e.getKeyCode());

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys.remove(Integer.valueOf(key));

        actualGameState.onKeyReleased(e.getKeyCode());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGameState();
        repaint();
    }

    private void updateGameState() {
        actualGameState = GameStatesManager.getGameState();
        actualGameState.handleKeys(pressedKeys);
    }

}
