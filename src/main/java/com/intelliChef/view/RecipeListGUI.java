package com.intelliChef.view;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeListGUI extends JFrame {

    private JPanel contentPanel;

    public RecipeListGUI() {
        setTitle("Recipe List");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen

        // Main content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        add(scrollPane, BorderLayout.CENTER);

        List<Recipe> recipes = loadRecipes();
        for (Recipe recipe : recipes) {
            contentPanel.add(createRecipePanel(recipe));
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        }

        setVisible(true);
    }

    private JPanel createRecipePanel(Recipe recipe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(Color.WHITE);

        // Recipe image
        try {
            BufferedImage image = ImageIO.read(new URL(recipe.getImageUrl()));
            Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            panel.add(imageLabel, BorderLayout.WEST);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("No Image", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(120, 120));
            placeholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.add(placeholder, BorderLayout.WEST);
        }

        // Recipe details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(50, 50, 50));
        detailsPanel.add(nameLabel);

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        calorieLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        calorieLabel.setForeground(new Color(80, 80, 80));
        detailsPanel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            timeLabel.setForeground(new Color(80, 80, 80));
            detailsPanel.add(timeLabel);
        }

        JButton detailsButton = new JButton("View Details");
        detailsButton.setFocusPainted(false);
        detailsButton.setFont(new Font("Arial", Font.BOLD, 12));
        detailsButton.setBackground(new Color(0, 123, 255));
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        detailsButton.addActionListener(e -> new RecipeDetailGUI(recipe, this));
        detailsPanel.add(detailsButton);

        panel.add(detailsPanel, BorderLayout.CENTER);
        return panel;
    }

    private List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("recipes.json")) {
            if (is == null) {
                throw new RuntimeException("File not found: recipes.json");
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(is));
            JSONArray hitsArray = jsonObject.getJSONArray("hits");
            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject recipeJson = hitsArray.getJSONObject(i).getJSONObject("recipe");
                String name = recipeJson.getString("label");
                String imageUrl = recipeJson.optString("image", "");
                double calories = recipeJson.optDouble("calories", 0.0);
                int cookingTime = recipeJson.optInt("totalTime", -1);
                String url = recipeJson.optString("url", "");
                recipes.add(new Recipe(name, imageUrl, calories, cookingTime, url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecipeListGUI::new);
    }

    static class Recipe {
        private final String name;
        private final String imageUrl;
        private final double calories;
        private final int cookingTime;
        private final String url;

        public Recipe(String name, String imageUrl, double calories, int cookingTime, String url) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.calories = calories;
            this.cookingTime = cookingTime;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public double getCalories() {
            return calories;
        }

        public int getCookingTime() {
            return cookingTime;
        }

        public String getUrl() {
            return url;
        }
    }
}
