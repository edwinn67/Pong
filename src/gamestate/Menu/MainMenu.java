package gamestate.Menu;

import gamestate.World;
import Util.GameStatesManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import static java.lang.System.exit;

public class MainMenu extends Menu {

    // constructors
    public MainMenu() {
        super();
        title = "PONG";
    }

    // init menu
    @Override
    protected void fillMenu() {
        menuOptions.add("1 Player");
        menuOptions.add("2 Player");
        menuOptions.add("Options");
        menuOptions.add("Exit");
    }

    // draw menu
    @Override
    public void draw(Graphics g) {
        drawBackground(g);
        drawTitle(g);
        drawMenu(g);
    }

    // update
    @Override
    public void update() {

    }

    // handle keys
    @Override
    protected void handleChoice(LinkedList<Integer> pressedKeys) {
        if (pressedKeys.contains(KeyEvent.VK_ENTER) && !isKeyPressed) {
            isKeyPressed = true;

            if (choice == 0) {
                GameStatesManager.setGameState(new World(false));
            }
            else if (choice == 1) {
                GameStatesManager.setGameState(new World(true));
            }
            else if (choice == 2) {
                GameStatesManager.setGameState(new OptionsMenu());
            }
            else if (choice == 3) {
                exit(0);
            }
        }
    }
}
