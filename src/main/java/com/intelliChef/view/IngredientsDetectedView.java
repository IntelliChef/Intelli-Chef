package com.intelliChef.view;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedPresenter;
import com.intelliChef.entities.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IngredientsDetectedView extends JFrame {
    private IngredientsDetectedController ingredientsDetectedController;
    private IngredientsDetectedPresenter ingredientsDetectedPresenter;

    public IngredientsDetectedView(int ingredientCount, List<Ingredient> ingredientList) {
     super("Ingredient(s) Detected");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(400, 200);
     setLocationRelativeTo(null);
     setLayout(new BorderLayout());

     JLabel ingredientLabel = new JLabel("Type(s) of ingredient(s) detected: " + ingredientCount, SwingConstants.CENTER);
     ingredientLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
     add(ingredientLabel, BorderLayout.CENTER);

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

    public void setIngredientsDetectedController(IngredientsDetectedController ingredientsDetectedController) {
        this.ingredientsDetectedController = ingredientsDetectedController;
    }

    public void setIngredientsDetectedPresenter(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        this.ingredientsDetectedPresenter = ingredientsDetectedPresenter;
    }
}
