package com.intelliChef.adapters.dietPreference;

import com.intelliChef.entities.DietPreference;
import com.intelliChef.interfaces.FileStorage;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.dietPreference.RecipeUseCase;

import javax.swing.*;

public class dietPreferenceController {
    public void fetchRecipes(DietPreference dietPreference, IngredientRepository ingredients) {
        RecipeUseCase useCase = new RecipeUseCase(new FileStorage());
        useCase.processRecipes(dietPreference, ingredients);
        JOptionPane.showMessageDialog(null, "Recipes fetched and saved to file.");
    }
}
