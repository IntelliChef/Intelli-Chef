package com.intelliChef.frameworks.gui;

import com.intelliChef.adapters.presentation.RecipeListView;
import com.intelliChef.adapters.presentation.NavigationView;
import com.intelliChef.adapters.presentation.RecipeViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

/**
 * A graphical user interface for displaying a list of recipes.
 * This class implements the RecipeListView and NavigationView interfaces from the presentation layer.
 * It provides a scrollable list of recipe panels, each showing an image and basic recipe details.
 */
public class RecipeListGUI extends JFrame implements RecipeListView, NavigationView {
    private final JPanel contentPanel;
    private RecipeSelectionListener selectionListener;

    /**
     * Interface for handling recipe selection events.
     * Implemented by classes that need to respond to recipe selections.
     */
    public interface RecipeSelectionListener {
        /**
         * Called when a recipe is selected from the list.
         *
         * @param recipe the selected recipe view model
         */
        void onRecipeSelected(RecipeViewModel recipe);
    }

    /**
     * Constructs a new RecipeListGUI with the specified selection listener.
     *
     * @param selectionListener listener for recipe selection events (can be null initially)
     */
    public RecipeListGUI(RecipeSelectionListener selectionListener) {
        this.selectionListener = selectionListener;

        setTitle("Recipe List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets or updates the recipe selection listener.
     *
     * @param selectionListener the new listener for recipe selection events
     */
    public void setSelectionListener(RecipeSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    /**
     * Displays a list of recipes in the GUI.
     *
     * @param recipes list of recipe view models to display
     */
    @Override
    public void displayRecipes(List<RecipeViewModel> recipes) {
        SwingUtilities.invokeLater(() -> {
            contentPanel.removeAll();
            recipes.forEach(this::addRecipePanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }

    /**
     * Displays an error message in a dialog box.
     *
     * @param error the error message to display
     */
    @Override
    public void displayError(String error) {
        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE));
    }

    /**
     * Makes the recipe list view visible.
     * Used when navigating back from other views.
     */
    @Override
    public void navigateBack() {
        setVisible(true);
    }

    /**
     * Adds a recipe panel to the content panel with appropriate listeners.
     *
     * @param recipe the recipe view model to create a panel for
     */
    private void addRecipePanel(RecipeViewModel recipe) {
        JPanel panel = createRecipePanel(recipe);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectionListener != null) {
                    selectionListener.onRecipeSelected(recipe);
                }
            }
        });
        contentPanel.add(panel);
    }

    /**
     * Creates a panel for displaying a single recipe.
     *
     * @param recipe the recipe view model to display
     * @return a JPanel containing the recipe's image and details
     */
    private JPanel createRecipePanel(RecipeViewModel recipe) {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(750, 200));
        panel.setBackground(Color.WHITE);

        JPanel imagePanel = createImagePanel(recipe.getImageUrl());
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2));
        panel.add(imagePanel, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        detailsPanel.setBackground(Color.WHITE);

        addDetailLabels(detailsPanel, recipe);
        panel.add(detailsPanel, BorderLayout.CENTER);

        addHoverEffect(panel);
        return panel;
    }

    /**
     * Creates a panel containing the recipe's image.
     *
     * @param imageUrl URL of the recipe image
     * @return a JPanel containing the image or a placeholder if the image cannot be loaded
     */
    private JPanel createImagePanel(String imageUrl) {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("Image not available", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(150, 150));
            placeholder.setBackground(Color.LIGHT_GRAY);
            placeholder.setOpaque(true);
            imagePanel.add(placeholder, BorderLayout.CENTER);
        }
        return imagePanel;
    }

    /**
     * Adds detail labels (name, calories, cooking time) to the details panel.
     *
     * @param detailsPanel the panel to add labels to
     * @param recipe the recipe view model containing the details
     */
    private void addDetailLabels(JPanel detailsPanel, RecipeViewModel recipe) {
        // Recipe Name
        JLabel nameLabel = new JLabel(recipe.getDisplayName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Calories
        JLabel caloriesLabel = new JLabel(recipe.getFormattedCalories());
        caloriesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        caloriesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(caloriesLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Cooking Time
        if (!recipe.getFormattedTime().equals("Time not available")) {
            JLabel timeLabel = new JLabel(recipe.getFormattedTime());
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            detailsPanel.add(timeLabel);
            detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        // Recipe URL (non-clickable)
        JLabel urlLabel = new JLabel("View recipe details for full instructions");
        urlLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        urlLabel.setForeground(Color.GRAY);
        urlLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(urlLabel);
    }

    /**
     * Adds hover effect to a panel.
     *
     * @param panel the panel to add the hover effect to
     */
    private void addHoverEffect(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
                        new EmptyBorder(15, 15, 15, 15)
                ));
            }

            public void mouseExited(MouseEvent evt) {
                panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                        new EmptyBorder(15, 15, 15, 15)
                ));
            }
        });
    }
}