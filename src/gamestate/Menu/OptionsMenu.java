package gamestate.Menu;

import gamestate.Menu.Options.DifficultyOptions;
import Util.GameStatesManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class OptionsMenu extends Menu {

    // properties
    private static int difficulty = 0;

    // getters and setters
    public static void setDifficulty(int difficulty) {
        OptionsMenu.difficulty = difficulty;
    }
    public static int getDifficulty() {
        return difficulty;
    }

    // constructors
    public OptionsMenu() {
        super();
        title = "Options";
    }

    // init menu
    @Override
    protected void fillMenu() {
        menuOptions.add("Difficulty");
    }

    // draw
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
        if (pressedKeys.contains(KeyEvent.VK_ESCAPE) && !isKeyPressed) {
            goBack(new MainMenu());
        }

        if (pressedKeys.contains(KeyEvent.VK_ENTER) && !isKeyPressed) {
            isKeyPressed = true;

            if (choice == 0) {
                GameStatesManager.setGameState(new DifficultyOptions());
            }

        }
    }

}
