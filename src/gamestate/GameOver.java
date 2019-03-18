package gamestate;

import gamestate.Menu.MainMenu;
import Util.FontManager;
import Util.GameStatesManager;
import Util.WindowManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class GameOver implements GameState {

    // properties
    private String textGameOver;
    private final Rectangle bounds;

    // constructors
    GameOver() {
        bounds = WindowManager.getBounds();
        textGameOver = "Game Over";
    }

    @Override
    public void draw(Graphics g) {
        drawBackground(g);
        drawCenteredString(g, textGameOver, FontManager.getFont(60));
    }
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    private void drawCenteredString(Graphics g, String text, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = bounds.x + (bounds.width - metrics.stringWidth(text)) / 2;
        int y = bounds.y + ((bounds.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setColor(new Color(255, 255, 255));
        g.setFont(font);
        g.drawString(text, x, y);
    }


    @Override
    public void update() {

    }

    @Override
    public void onKeyTyped(int keyCode) {

    }

    @Override
    public void onKeyPressed(int keyCode) {

    }

    @Override
    public void onKeyReleased(int keyCode) {

    }

    @Override
    public void handleKeys(LinkedList<Integer> pressedKeys) {
        if (pressedKeys.contains(KeyEvent.VK_ENTER)) {
            GameStatesManager.setGameState(new MainMenu());
        }
    }
}
