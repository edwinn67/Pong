package util;

import java.awt.*;
import java.io.*;

public class FontManager {

    private static Font ttfFont = null;
    private static final String FONT_PATH_TELEGRAFICO = "/res/ARCADE.ttf";

    private void createFont() {
        try {
            InputStream is = getClass().getResourceAsStream(FONT_PATH_TELEGRAFICO);
            ttfFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
    }

    public Font getFont(int size) {
        if (ttfFont == null)
            this.createFont();

        return ttfFont.deriveFont(Font.PLAIN, size);
    }

    /*private Font font = null;
     InputStream in = getClass().getResourceAsStream("res/ARCADE.ttf");

    public void loadFont() {
        String path = "src/res/ARCADE.ttf";
        path = "res\\ARCADE.ttf";


        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    FontManager.class.getResourceAsStream("res/ARCADE.ttf"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("font not loaded.");

            path = "res/ARCADE.ttf";
            try {
                font = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new BufferedInputStream(
                                new FileInputStream(path)));
            }
            catch (Exception e) {
                e.printStackTrace();
                System.err.println("#############################################################");

                path = "ARCADE.ttf";
                try {
                    font = Font.createFont(
                            Font.TRUETYPE_FONT,
                            new BufferedInputStream(
                                    new FileInputStream(path)));
                }
                catch (Exception ei) {
                    ei.printStackTrace();
                    System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                }
            }
        }
    }

    public Font getFont(int size) {
        if (font == null)
            loadFont();

        return font.deriveFont(Font.PLAIN, size);
    }


    public Font getFont(int size) {
        Font font = new Font("Times New Roman", Font.PLAIN, size);

        try {
            InputStream is = getClass().getResourceAsStream("res\\ARCADE.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font.deriveFont(Font.PLAIN, size);
    }*/
}
