package Util;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class FontManager {

    private static Font font = null;
    private static FontMetrics metrics;

    public static void loadFont() {
        String path = "src/Util/Font/ARCADE.TTF";

        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new BufferedInputStream(
                            new FileInputStream(path)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
    }

    public static Font getFont(int size) {
        return font.deriveFont(Font.PLAIN, size);
    }

    public static Graphics g;

    public static int getFontHeight(FontMetrics metrics) {
//        System.out.println("FM: " +metrics);
        return metrics.getHeight()+metrics.getAscent()/2;
    }

    public static FontMetrics getMetrics(Graphics g, Font font) {
        return g.getFontMetrics(font);
    }

    public void setMetrics(Graphics g) {
        this.metrics = g.getFontMetrics(font);
    }
    public int getTextWidth(String text) {
        return metrics.stringWidth(text);
    }
    public int getTextHeight() {
        return metrics.getHeight();
    }
    public int getTextAscent() {
        return metrics.getAscent();
    }
}
