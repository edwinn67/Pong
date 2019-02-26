package Core;

import GameState.World;
import Physics.PlayerDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class GamePanel extends JPanel
    implements KeyListener, ActionListener {

    // dimensions
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int SCALE = 1;

    // game loop variables
    private Timer timer = new Timer(120, this);
    private LinkedList<Integer> pressedKeys = new LinkedList<>();

    // world instance
    private final World world  = new World();

    // constructors
    GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        if (world.getBounds() == null)
            world.setBounds(getBounds());
        world.update();
        world.draw(g);

        world.debug(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ( !pressedKeys.contains(key) )
            pressedKeys.add(key);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys.remove(Integer.valueOf(key));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleKeys();
        repaint();
    }

    private void handleKeys() {
        if (!pressedKeys.isEmpty()) {
            if (pressedKeys.getLast() == KeyEvent.VK_DOWN) {
                world.setPlayerDirection(PlayerDirection.DOWN);
            } else
            if (pressedKeys.getLast() == KeyEvent.VK_UP) {
                world.setPlayerDirection(PlayerDirection.UP);
            }

            // DEBUG
            if (pressedKeys.getLast() == KeyEvent.VK_RIGHT) {
                world.setPlayerDirection(PlayerDirection.RIGHT);
            } else
            if (pressedKeys.getLast() == KeyEvent.VK_LEFT) {
                world.setPlayerDirection(PlayerDirection.LEFT);
            }
        }
        else {
            world.setPlayerDirection(PlayerDirection.STAND);
        }
    }
}
