package gamestate.Menu.Options;

import gamestate.Menu.OptionsMenu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class DifficultyOptions extends OptionsMenu {

    public DifficultyOptions() {
        super();
    }

    @Override
    protected void fillMenu() {
        menuOptions.add("Easy");
        menuOptions.add("Medium");
        menuOptions.add("Hard");
    }


    @Override
    public void draw(Graphics g) {
        drawBackground(g);
        drawTitle(g);
        drawMenu(g);
    }

    @Override
    public void update() {

    }

    @Override
    protected void handleChoice(LinkedList<Integer> pressedKeys) {
        if (pressedKeys.contains(KeyEvent.VK_ESCAPE) && !isKeyPressed) {
            goBack(new OptionsMenu());
        }

        if (pressedKeys.contains(KeyEvent.VK_ENTER) && !isKeyPressed) {
            isKeyPressed = true;

            if (choice == 0) {
                OptionsMenu.setDifficulty(0);
            }
            else if (choice == 1) {
                OptionsMenu.setDifficulty(1);
            }
            else if (choice == 2) {
                OptionsMenu.setDifficulty(2);
            }

            goBack(new OptionsMenu());
        }
    }
}

