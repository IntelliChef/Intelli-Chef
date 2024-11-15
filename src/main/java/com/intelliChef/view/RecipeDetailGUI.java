package com.intelliChef.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RecipeDetailGUI extends JFrame {

    public RecipeDetailGUI(RecipeListGUI.Recipe recipe, RecipeListGUI recipeListGUI) {
        setTitle("Recipe Details - " + recipe.getName());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        add(contentPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(nameLabel);

        try {
            Image image = ImageIO.read(new URL(recipe.getImageUrl()));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            contentPanel.add(imageLabel);
        } catch (Exception e) {
            contentPanel.add(new JLabel("Image not available"));
        }

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        contentPanel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            contentPanel.add(timeLabel);
        }

        JEditorPane urlPane = new JEditorPane();
        urlPane.setContentType("text/html");
        urlPane.setText("<a href='" + recipe.getUrl() + "'>View Full Recipe</a>");
        urlPane.setEditable(false);
        urlPane.setOpaque(false);
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

        JButton backButton = new JButton("Back to Recipes List");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipeListGUI.setVisible(true);
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
