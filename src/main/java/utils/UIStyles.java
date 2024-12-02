package utils;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;


public class UIStyles {
    public static String mediumDynaPuff = "fonts/DynaPuff-Medium.ttf";
    public static String regularDynaPuff = "fonts/DynaPuff-Regular.ttf";

    /**
     * Gives a general styling to any button passed as argument.
     * @param button the button to be styled
     */
    public static void styleButton(JButton button) {
        button.setFont(loadCustomFont(mediumDynaPuff, 16));
        button.setBackground(new Color(38, 129, 189));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFocusPainted(false);
    }

    /**
     * Loads up a font from resources folder given the correct name of file is entered.
     * @param fontPath file name of font
     * @param size size of desired font
     * @return a font style with the given style and size or a basic font if no font file is found.
     */
    public static Font loadCustomFont(String fontPath, float size) {
        try {
            InputStream fontStream = UIStyles.class.getClassLoader().getResourceAsStream(fontPath);
            if (fontStream == null) {
                throw new Exception("Font file not found: " + fontPath);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return font.deriveFont(size);
        } catch (Exception e) {
            System.out.println("Unable to load font. Using fallback font.");
            return new Font("SansSerif", Font.BOLD, (int) size);
        }
    }
}
