package core;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel gp = new GamePanel();

        new JFrame("PONG");
        add(gp);
        addKeyListener(gp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }

}
