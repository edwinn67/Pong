package util;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class FontManager {

    private static Font font = null;

    public static void loadFont() {
        String path = "src/font/ARCADE.TTF";

        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new BufferedInputStream(
                            new FileInputStream(path)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("font not loaded.");
        }
    }

    public static Font getFont(int size) {
        return font.deriveFont(Font.PLAIN, size);
    }
}
