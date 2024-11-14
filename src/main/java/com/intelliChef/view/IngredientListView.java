package com.intelliChef.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import com.intelliChef.entities.Ingredient;

public class IngredientListView extends JFrame {

    public IngredientListView(List<Ingredient> ingredients) {
        // Set up the frame
        setTitle("Ingredients List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Title label at the top
        JLabel titleLabel = new JLabel("Ingredients List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel to hold ingredient checkboxes in a scrollable pane
        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));

        // Add a checkbox for each ingredient
        for (Ingredient ingredient : ingredients) {
            JCheckBox checkBox = new JCheckBox(ingredient.getName() + " - " + ingredient.getQuantity(), true);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            ingredientPanel.add(checkBox);
        }

        // Make the panel scrollable
        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Example list of ingredients
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomato", 5.0));
        ingredients.add(new Ingredient("Onion", 2.0));
        ingredients.add(new Ingredient("Garlic", 3.0));
        ingredients.add(new Ingredient("Olive Oil", 0.5));

        // Create and show the Ingredient List view
        new IngredientListView(ingredients);
    }
}


