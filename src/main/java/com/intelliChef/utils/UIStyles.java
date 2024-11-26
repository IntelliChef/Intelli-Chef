package com.intelliChef.utils;

import javax.swing.*;
import java.awt.*;

import static com.intelliChef.utils.UseCustomFont.mediumDynaPuff;

public class UIStyles {
    public static void styleButton(JButton button) {
        button.setFont(UseCustomFont.loadCustomFont(mediumDynaPuff, 16));
        button.setBackground(new Color(38, 129, 189));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFocusPainted(false);
    }
}
