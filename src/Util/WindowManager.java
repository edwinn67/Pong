package Util;

import java.awt.*;

public class WindowManager {

    // window
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int SCALE = 1;

    // getters
    public static int getWindowWidth() {
        return WIDTH *SCALE;
    }
    public static int getWindowHeight() {
        return HEIGHT *SCALE;
    }
    public static Rectangle getBounds() {
        return new Rectangle(
                0,
                0,
                WindowManager.getWindowWidth(),
                WindowManager.getWindowHeight());
    }


}
