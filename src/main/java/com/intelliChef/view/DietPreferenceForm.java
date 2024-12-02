package com.intelliChef.view;

import com.intelliChef.adapters.dietPreference.DietPreferenceController;
import com.intelliChef.entities.DietPreference;
import com.intelliChef.use_case.IngredientRepository;

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
    private IngredientRepository ingredientRepository;
    private DietPreferenceController controller;

    public void setController(DietPreferenceController controller) {
        this.controller = controller;
    }

    public DietPreferenceForm(IngredientRepository repository) {
        this.ingredientRepository = repository;
        setTitle("Diet Preferences");
        setSize(300, 400);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

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

                ketoBox.setFont(new Font("Arial", Font.PLAIN, 18));
                glutenFreeBox.setFont(new Font("Arial", Font.PLAIN, 18));
                proteinRichBox.setFont(new Font("Arial", Font.PLAIN, 18));
                fiberRichBox.setFont(new Font("Arial", Font.PLAIN, 18));
                alcoholFreeBox.setFont(new Font("Arial", Font.PLAIN, 18));
                veganBox.setFont(new Font("Arial", Font.PLAIN, 18));

                DietPreference dietPreference = new DietPreference(preferences);


                controller.fetchRecipes(dietPreference, ingredientRepository);

                controller.confirmClick();

                dispose();
            }
        });
    }


}
