package com.intelliChef.view;

import com.intelliChef.entities.DietPreference;
import com.intelliChef.use_case.dietPreference.RecipeUseCase;
import com.intelliChef.data_access.GeminiAIforRecipe;
import com.intelliChef.use_case.dietPreference.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DietPreferenceForm extends JFrame {
    private JCheckBox ketoBox = new JCheckBox("Keto");
    private JCheckBox glutenFreeBox = new JCheckBox("Gluten-free");
    private JCheckBox proteinRichBox = new JCheckBox("Protein-Rich");
    private JCheckBox fiberRichBox = new JCheckBox("Fiber-Rich");
    private JCheckBox alcoholFreeBox = new JCheckBox("Alcohol-free");
    private JCheckBox veganBox = new JCheckBox("Vegan");
    private JButton confirmButton = new JButton("Confirm");


    public DietPreferenceForm() {
        setTitle("Diet Preferences");
        setSize(300, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        add(ketoBox);
        add(glutenFreeBox);
        add(proteinRichBox);
        add(fiberRichBox);
        add(alcoholFreeBox);
        add(veganBox);
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> preferences = new ArrayList<>();
                if (ketoBox.isSelected()) preferences.add("Keto");
                if (glutenFreeBox.isSelected()) preferences.add("Gluten-free");
                if (proteinRichBox.isSelected()) preferences.add("Protein-Rich");
                if (fiberRichBox.isSelected()) preferences.add("Fiber-Rich");
                if (alcoholFreeBox.isSelected()) preferences.add("Alcohol-free");
                if (veganBox.isSelected()) preferences.add("Vegan");

                DietPreference dietPreference = new DietPreference(preferences);

                // Dummy ingredient list
                List<String> ingredients = List.of("chicken", "garlic", "pepper");

                RecipeUseCase useCase = new RecipeUseCase(new GeminiAIforRecipe(
                        "",
                        "us-central1",
                        "gemini-1.5-flash-001"), new FileStorage());
                useCase.processRecipes(dietPreference, ingredients);
                JOptionPane.showMessageDialog(null, "Recipes fetched and saved to file.");
            }
        });
    }
}
