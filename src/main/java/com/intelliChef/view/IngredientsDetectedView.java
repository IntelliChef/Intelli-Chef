package com.intelliChef.view;

import com.intelliChef.adapters.IngredientsDetectedController;
import com.intelliChef.adapters.IngredientsDetectedPresenter;
import com.intelliChef.entities.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IngredientsDetectedView extends JFrame {
     public IngredientsDetectedView(List<Ingredient> ingredientList) {
         super("Ingredient(s) Detected");
         IngredientsDetectedController ingredientsDetectedController = new IngredientsDetectedController();
         IngredientsDetectedPresenter ingredientsDetectedPresenter = new IngredientsDetectedPresenter(this);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(400, 200);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());

         JLabel ingredientCount = new JLabel("Type(s) of ingredient(s) detected: "
                 + ingredientList.size(), SwingConstants.CENTER);
         ingredientCount.setFont(new Font("SansSerif", Font.PLAIN, 16));
         add(ingredientCount, BorderLayout.CENTER);

         JButton continueButton = new JButton("Continue");
         styleButton(continueButton);
         continueButton.addActionListener(e -> {
             ingredientsDetectedController.continueButtonClick(ingredientList, ingredientsDetectedPresenter);
         });

         JButton cancelButton = new JButton("Cancel");
         styleButton(cancelButton);
         cancelButton.addActionListener(e -> {
             ingredientsDetectedController.cancelButtonClick(ingredientsDetectedPresenter);
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
