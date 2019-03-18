package gamestate.Menu;

import gamestate.GameState;
import Util.FontManager;
import Util.GameStatesManager;
import Util.WindowManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


public abstract class Menu implements GameState {

    // properties
    private final Rectangle bounds;

    // package-private properties
    protected LinkedList<String> menuOptions;
    protected static boolean isKeyPressed;
    protected String title;
    protected int choice;


    // constructors
    public Menu() {
        this.bounds = WindowManager.getBounds();
        initProps();
        fillMenu();
    }

    // init methods
    private void initProps() {
        menuOptions = new LinkedList<>();
        choice = 0;
    }

    // draw methods
    protected void drawBackground(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    protected void drawTitle(Graphics g) {
        Font font = FontManager.getFont(90);
        FontMetrics metrics = g.getFontMetrics(font);
        initFont(g, font);

        int x = bounds.x + (bounds.width - metrics.stringWidth(title)) / 2;
        int y = bounds.y + (metrics.getHeight()/2 + metrics.getAscent());

        g.drawString(title, x, y);
    }
    protected void drawMenu(Graphics g) {
        Font font = FontManager.getFont(28);
        FontMetrics metrics = g.getFontMetrics(font);
        initFont(g, font);

        int x = getHorizontalPositionOf(menuOptions.getFirst(), metrics);

        for (String option : menuOptions) {
            int y = getVerticalPositionOf(option, metrics);
            g.drawString(option, x, y);
        }

        int start = getVerticalPositionOf(menuOptions.get(choice), metrics);
        int end = start-metrics.getHeight()+metrics.getAscent()/2;
        int cy = start+(end-start)/2;
        int cx = x - 20;

        drawCursor(g, cx, cy);
    }
    protected void drawCursor(Graphics g, int x, int y) {
        int side = 12;
        y -= side/2;

        g.fillRect(x, y, side, side);
    }

    // private methods
    private void initFont(Graphics g, Font font) {
        g.setFont(font);
        g.setColor(new Color(255, 255, 255));
    }
    private int getHorizontalPositionOf(String option, FontMetrics metrics) {
        return bounds.x + (bounds.width - metrics.stringWidth(option)) / 2;
    }
    private int getVerticalPositionOf(String option, FontMetrics metrics) {
        int space = 80;
        return bounds.y + (bounds.height / 2 - (metrics.getHeight() * menuOptions.size()
                + (menuOptions.indexOf(option) * space)) / 2 + metrics.getAscent()
                + (menuOptions.indexOf(option) * space));
    }

    // handle keys
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
        if (pressedKeys.isEmpty())
            isKeyPressed = false;

        if (pressedKeys.contains(KeyEvent.VK_DOWN)
                && choice < menuOptions.size() - 1 && !isKeyPressed) {
            choice++;
            isKeyPressed = true;
        } else if (pressedKeys.contains(KeyEvent.VK_UP)
                && choice > 0 && !isKeyPressed) {
            choice--;
            isKeyPressed = true;
        }

        handleChoice(pressedKeys);
    }

    // protected methods
    protected abstract void fillMenu();
    protected abstract void handleChoice(LinkedList<Integer> pressedKeys);
    protected void goBack(GameState GM) {
        isKeyPressed = true;
        GameStatesManager.setGameState(GM);
    }

}