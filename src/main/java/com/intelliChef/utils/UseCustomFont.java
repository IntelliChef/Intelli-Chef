package com.intelliChef.utils;

import java.awt.*;
import java.io.InputStream;

/**
 * Loads up a custom font from the resources folder given the correct name of font.
 */
public class UseCustomFont {
    public static String mediumDynaPuff = "fonts/DynaPuff-Medium.ttf";
    public static String regularDynaPuff = "fonts/DynaPuff-Regular.ttf";

    public static Font loadCustomFont(String fontPath, float size) {
        try {
            InputStream fontStream = UseCustomFont.class.getClassLoader().getResourceAsStream(fontPath);
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
