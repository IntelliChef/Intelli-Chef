package com.intelliChef.view;

import com.intelliChef.entities.Recipe;
import com.intelliChef.presenters.BackButtonPresenter;
import com.intelliChef.use_case.BackButtonInteractor;
import com.intelliChef.presenters.RecipeListView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RecipeDetailGUI extends JFrame {
    private BackButtonInteractor backButtonInteractor;

    public RecipeDetailGUI(Recipe recipe, RecipeListView recipeListView) {
        setTitle(recipe.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the BackButtonInteractor and Presenter
        BackButtonPresenter presenter = new BackButtonPresenter(recipeListView);
        this.backButtonInteractor = new BackButtonInteractor(presenter);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Header with Recipe Name
        JLabel nameLabel = new JLabel(recipe.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(nameLabel, BorderLayout.NORTH);

        // Center panel for image and details
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // Recipe Image
        try {
            BufferedImage image = ImageIO.read(new URL(recipe.getImageUrl()));
            JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            centerPanel.add(imageLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("Image not available", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(400, 300));
            placeholder.setOpaque(true);
            placeholder.setBackground(Color.LIGHT_GRAY);
            centerPanel.add(placeholder, BorderLayout.NORTH);
        }

        // Recipe Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        calorieLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        calorieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(timeLabel);
        }

        JLabel urlLabel = new JLabel("<html><a href='" + recipe.getUrl() + "'>Recipe Link</a></html>");
        urlLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        urlLabel.setForeground(Color.BLUE);
        urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL(recipe.getUrl()).toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        detailsPanel.add(urlLabel);

        centerPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            backButtonInteractor.goBack();
            this.dispose(); // Close RecipeDetailGUI when going back
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void setBackButtonInteractor(BackButtonInteractor backButtonInteractor) {
        this.backButtonInteractor = backButtonInteractor;
    }
}
