package com.intelliChef.view;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedPresenter;
import com.intelliChef.use_case.IngredientListRepositoryInterface;

import static com.intelliChef.utils.UIStyles.styleButton;

import javax.swing.*;
import java.awt.*;

public class IngredientsDetectedView extends JFrame {
    private IngredientsDetectedController ingredientsDetectedController;
    private IngredientsDetectedPresenter ingredientsDetectedPresenter;

    public IngredientsDetectedView(IngredientListRepositoryInterface ingredientRepository) {
     super("Ingredient(s) Detected");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(500, 250);
     setLocationRelativeTo(null);
     setLayout(new BorderLayout());

        JLabel ingredientLabel = new JLabel(
                "<html><div style='text-align: center;'>Type(s) of ingredient(s) detected:<br>"
                        + ingredientRepository.numberOfIngredients() + "</div></html>",
                SwingConstants.CENTER
        );
        ingredientLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        ingredientLabel.setForeground(Color.BLACK);
        ingredientLabel.setOpaque(true);
        ingredientLabel.setBackground(new Color(240, 240, 240));
        ingredientLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        add(ingredientLabel, BorderLayout.CENTER);

     JButton continueButton = new JButton("Continue");
     styleButton(continueButton);
     continueButton.addActionListener(e -> {
         ingredientsDetectedController.continueButtonClick(ingredientRepository, ingredientsDetectedPresenter);
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

    public void setIngredientsDetectedController(IngredientsDetectedController ingredientsDetectedController) {
        this.ingredientsDetectedController = ingredientsDetectedController;
    }

    public void setIngredientsDetectedPresenter(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        this.ingredientsDetectedPresenter = ingredientsDetectedPresenter;
    }
}
