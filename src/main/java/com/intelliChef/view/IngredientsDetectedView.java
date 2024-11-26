package com.intelliChef.view;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedPresenter;
import com.intelliChef.use_case.IngredientRepository;
import static com.intelliChef.utils.UIStyles.styleButton;

import javax.swing.*;
import java.awt.*;

public class IngredientsDetectedView extends JFrame {
    private IngredientsDetectedController ingredientsDetectedController;
    private IngredientsDetectedPresenter ingredientsDetectedPresenter;

    public IngredientsDetectedView(IngredientRepository ingredientRepository) {
     super("Ingredient(s) Detected");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(400, 200);
     setLocationRelativeTo(null);
     setLayout(new BorderLayout());

     JLabel ingredientLabel = new JLabel(
             "Type(s) of ingredient(s) detected: " + ingredientRepository.numberOfIngredients(),
             SwingConstants.CENTER);
     ingredientLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
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
