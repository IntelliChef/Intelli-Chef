package com.intelliChef.frameworks.gui;

import com.intelliChef.adapters.presentation.RecipeDetailView;
import com.intelliChef.adapters.presentation.RecipeViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * GUI implementation of the recipe detail view.
 * This class is in the frameworks & drivers layer and implements
 * the view interface from the interface adapters layer.
 */
public class RecipeDetailGUI extends JFrame implements RecipeDetailView {
    private final NavigationListener navigationListener;
    private final JPanel mainPanel;

    /**
     * Interface for handling navigation events.
     */
    public interface NavigationListener {
        void onNavigateBack();
    }

    /**
     * Constructs a new RecipeDetailGUI.
     *
     * @param navigationListener listener for navigation events
     */
    public RecipeDetailGUI(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;

        setTitle("Recipe Details");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);
    }

    @Override
    public void displayRecipeDetail(RecipeViewModel recipe) {
        SwingUtilities.invokeLater(() -> {
            mainPanel.removeAll();

            // Header with Recipe Name
            JLabel nameLabel = new JLabel(recipe.getDisplayName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
            nameLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
            mainPanel.add(nameLabel, BorderLayout.NORTH);

            // Center panel for image and details
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setBackground(Color.WHITE);

            // Recipe Image
            addRecipeImage(centerPanel, recipe.getImageUrl());

            // Recipe Details
            addRecipeDetails(centerPanel, recipe);

            mainPanel.add(centerPanel, BorderLayout.CENTER);

            // Back Button
            addBackButton();

            setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
    }

    private void addRecipeImage(JPanel centerPanel, String imageUrl) {
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            JLabel imageLabel = new JLabel(new ImageIcon(
                    image.getScaledInstance(400, 300, Image.SCALE_SMOOTH)));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            centerPanel.add(imageLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("Image not available", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(400, 300));
            placeholder.setOpaque(true);
            placeholder.setBackground(Color.LIGHT_GRAY);
            centerPanel.add(placeholder, BorderLayout.NORTH);
        }
    }

    private void addRecipeDetails(JPanel centerPanel, RecipeViewModel recipe) {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JLabel caloriesLabel = new JLabel(recipe.getFormattedCalories());
        caloriesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        caloriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(caloriesLabel);

        if (!recipe.getFormattedTime().equals("Time not available")) {
            JLabel timeLabel = new JLabel(recipe.getFormattedTime());
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(timeLabel);
        }

        JLabel urlLabel = new JLabel("<html><a href='" + recipe.getUrl() + "'>Recipe Link</a></html>");
        urlLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL(recipe.getUrl()).toURI());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RecipeDetailGUI.this,
                            "Could not open recipe link: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        detailsPanel.add(urlLabel);

        centerPanel.add(detailsPanel, BorderLayout.CENTER);
    }

    private void addBackButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            navigationListener.onNavigateBack();
            dispose();
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}