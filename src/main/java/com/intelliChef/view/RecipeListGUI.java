package com.intelliChef.view;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeListGUI extends JFrame {

    private JPanel contentPanel;

    public RecipeListGUI() {
        setTitle("Recipe List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        List<Recipe> recipes = loadRecipes(); // Load recipes from JSON file
        for (Recipe recipe : recipes) {
            contentPanel.add(createRecipePanel(recipe));
        }

        setVisible(true);
    }

    private JPanel createRecipePanel(Recipe recipe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(nameLabel);

        try {
            BufferedImage image = ImageIO.read(new URL(recipe.getImageUrl()));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            panel.add(imageLabel);
        } catch (Exception e) {
            panel.add(new JLabel("Image not available"));
        }

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        panel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            panel.add(timeLabel);
        }

        JEditorPane urlPane = new JEditorPane();
        urlPane.setContentType("text/html");
        urlPane.setText("<a href='" + recipe.getUrl() + "'>Recipe Link</a>");
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
        panel.add(urlPane);

        return panel;
    }

    private List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("recipes.json")) {
            if (is == null) {
                throw new FileNotFoundException("File not found: recipes.json");
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

    // Recipe class to hold data
    class Recipe {
        private String name;
        private String imageUrl;
        private double calories;
        private int cookingTime;
        private String url;

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
