package com.intelliChef.view;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IngredientsDetectedView extends JFrame {
     public IngredientsDetectedView(List<Ingredient> ingredientList) {
         super("Ingredients Detected");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(400, 200);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());

         JLabel ingredientCount = new JLabel("Number of ingredients detected: "
                 + ingredientList.size(), SwingConstants.CENTER);
         ingredientCount.setFont(new Font("SansSerif", Font.PLAIN, 16));
         add(ingredientCount, BorderLayout.CENTER);

         JButton continueButton = new JButton("Continue");
         styleButton(continueButton);
         continueButton.addActionListener(e -> {
             Main.showIngredientListView(ingredientList);
             dispose();
         });

         JButton cancelButton = new JButton("Cancel");
         styleButton(cancelButton);
         cancelButton.addActionListener(e -> {
             Main.showRecipeUploadView();
             dispose();
         });

         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
         buttonPanel.add(continueButton);
         buttonPanel.add(cancelButton);
         add(buttonPanel, BorderLayout.SOUTH);
         setVisible(true);
     }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(120, 40));
    }
}
