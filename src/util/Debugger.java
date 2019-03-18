package util;

import java.awt.*;

public class Debugger {

    private static Rectangle bounds = new Rectangle(
        0, 0,
        WindowManager.getWindowWidth(),
        WindowManager.getWindowHeight());

    public static void drawCenter(Graphics g) {
        int radius = 2;

        g.setColor(new Color(255,0,0));
        g.drawArc(bounds.width/2, bounds.height/2,
                radius, radius, 0, 360);
    }
}
