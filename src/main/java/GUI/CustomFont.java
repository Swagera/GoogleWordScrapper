package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    public static File fontFile = new File("src/main/resources/Kanit-Regular.ttf");

    public static Font getPlainCustomFont() {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont.deriveFont(Font.PLAIN, 14);
    }
    public static Font getBoldCustomFont() {

        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont.deriveFont(Font.BOLD, 14);
    }
    public static Font getBoldCustomFontSize(int size) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont.deriveFont(Font.BOLD, size);
    }
}
