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
 * GUI implementation of the recipe list view.
 * This class is in the frameworks & drivers layer and implements
 * view interfaces from the interface adapters layer.
 */
public class RecipeListGUI extends JFrame implements RecipeListView, NavigationView {
    private final JPanel contentPanel;
    private final RecipeSelectionListener selectionListener;

    /**
     * Interface for handling recipe selection events.
     */
    public interface RecipeSelectionListener {
        void onRecipeSelected(RecipeViewModel recipe);
    }

    /**
     * Constructs a new RecipeListGUI.
     *
     * @param selectionListener listener for recipe selection events
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

    @Override
    public void displayRecipes(List<RecipeViewModel> recipes) {
        SwingUtilities.invokeLater(() -> {
            contentPanel.removeAll();
            recipes.forEach(this::addRecipePanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }

    @Override
    public void displayError(String error) {
        SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE));
    }

    @Override
    public void navigateBack() {
        setVisible(true);
    }

    private void addRecipePanel(RecipeViewModel recipe) {
        JPanel panel = createRecipePanel(recipe);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectionListener.onRecipeSelected(recipe);
            }
        });
        contentPanel.add(panel);
    }

    private JPanel createRecipePanel(RecipeViewModel recipe) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(750, 200));
        panel.setBackground(Color.WHITE);

        // Image Panel
        JPanel imagePanel = createImagePanel(recipe.getImageUrl());
        panel.add(imagePanel, BorderLayout.WEST);

        // Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        addDetailLabels(detailsPanel, recipe);
        panel.add(detailsPanel, BorderLayout.CENTER);

        return panel;
    }

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

    private void addDetailLabels(JPanel detailsPanel, RecipeViewModel recipe) {
        JLabel nameLabel = new JLabel(recipe.getDisplayName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        detailsPanel.add(nameLabel);

        JLabel caloriesLabel = new JLabel(recipe.getFormattedCalories());
        caloriesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsPanel.add(caloriesLabel);

        if (!recipe.getFormattedTime().equals("Time not available")) {
            JLabel timeLabel = new JLabel(recipe.getFormattedTime());
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            detailsPanel.add(timeLabel);
        }

        JLabel urlLabel = new JLabel("<html><a href='" + recipe.getUrl() + "'>Recipe Link</a></html>");
        urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL(recipe.getUrl()).toURI());
                } catch (Exception ex) {
                    displayError("Could not open recipe link: " + ex.getMessage());
                }
            }
        });
        detailsPanel.add(urlLabel);
    }
}