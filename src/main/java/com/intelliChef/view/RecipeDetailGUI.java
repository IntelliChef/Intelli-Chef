package com.intelliChef.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RecipeDetailGUI extends JFrame {

    public RecipeDetailGUI(RecipeListGUI.Recipe recipe, JFrame parent) {
        setTitle("Recipe Details");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent); // Center relative to the parent window

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(245, 245, 245));

        // Recipe name
        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(new Color(50, 50, 50));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(nameLabel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Recipe image
        try {
            BufferedImage image = ImageIO.read(new URL(recipe.getImageUrl()));
            Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(imageLabel);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("No Image Available", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(400, 300));
            placeholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(placeholder);
        }

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        calorieLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        calorieLabel.setForeground(new Color(80, 80, 80));
        calorieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            timeLabel.setForeground(new Color(80, 80, 80));
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(timeLabel);
        }

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing

        // Recipe URL
        JEditorPane urlPane = new JEditorPane();
        urlPane.setContentType("text/html");
        urlPane.setText("<a href='" + recipe.getUrl() + "'>View Full Recipe</a>");
        urlPane.setEditable(false);
        urlPane.setOpaque(false);
        urlPane.setFont(new Font("Arial", Font.PLAIN, 16));
        urlPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(new URL(recipe.getUrl()).toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        contentPanel.add(urlPane);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacing

        // Back button
        JButton backButton = new JButton("Back to Recipe List");
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 53, 69));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            parent.setVisible(true); // Show parent window
            dispose(); // Close this window
        });
        contentPanel.add(backButton);

        add(contentPanel);
        setVisible(true);
    }
}
