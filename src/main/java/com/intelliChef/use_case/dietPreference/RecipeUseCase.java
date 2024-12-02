package com.intelliChef.use_case.dietPreference;

import com.intelliChef.data_access.GeminiAIforRecipe;
import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.entities.DietPreference;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.interfaces.FileStorage;
import com.intelliChef.use_case.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeUseCase {
    private GeminiAIforRecipe geminiService = new GeminiAIforRecipe(
            "",
            "us-central1",
            "gemini-1.5-flash-001");
    private FileStorage fileStorage;

    public RecipeUseCase(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public void processRecipes(DietPreference preferences, IngredientRepository ingredients) {
        List<Ingredient> listOfIngredients = ingredients.getAllIngredients();
        List<String> stringifiedIngredients= new ArrayList<>();
        for (Ingredient ingredient : listOfIngredients) {
            stringifiedIngredients.add(ingredient.getName() + "  " + ingredient.getQuantity());
        }

        try {
            String jsonResponse = geminiService.getRecipe(preferences.getPreferences(), stringifiedIngredients);
            fileStorage.saveToFile(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error processing recipes: " + e.getMessage());
        }
    }
}
