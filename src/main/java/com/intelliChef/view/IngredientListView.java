package com.intelliChef.view;

import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;
import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IngredientListView extends JFrame {
    private final GetIngredientListPresenter presenter;

    public IngredientListView(GetIngredientListPresenter presenter) {
        this.presenter = presenter;

        // Set up the frame
        setTitle("Ingredient List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label at the top
        JLabel titleLabel = new JLabel("Ingredients List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Display the ingredients
        displayIngredients();
    }

    private void displayIngredients() {
        // Get the View Model from the Presenter
        IngredientListViewModel viewModel = presenter.getViewModel();

        // Create a panel to hold ingredient checkboxes in a scrollable pane
        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));

        // Add a checkbox for each ingredient
        List<String> ingredients = viewModel.getIngredientsDisplayList();
        for (String ingredient : ingredients) {
            JCheckBox checkBox = new JCheckBox(ingredient, true);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            ingredientPanel.add(checkBox);
        }

        // Make the panel scrollable
        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }
}



