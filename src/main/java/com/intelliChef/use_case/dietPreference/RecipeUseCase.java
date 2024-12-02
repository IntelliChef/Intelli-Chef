package com.intelliChef.use_case.dietPreference;

import com.intelliChef.data_access.GeminiAIforRecipe;
import com.intelliChef.entities.DietPreference;
import com.intelliChef.interfaces.FileStorage;

import java.util.List;

public class RecipeUseCase {
    private GeminiAIforRecipe geminiService = new GeminiAIforRecipe(
            "",
            "us-central1",
            "gemini-1.5-flash-001");
    private FileStorage fileStorage;

    public RecipeUseCase(GeminiAIforRecipe geminiService, FileStorage fileStorage) {
        this.geminiService = geminiService;
        this.fileStorage = fileStorage;
    }

    public void processRecipes(DietPreference preferences, List<String> ingredients) {
        try {
            String jsonResponse = geminiService.getRecipe(preferences.getPreferences(), ingredients);
            fileStorage.saveToFile(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error processing recipes: " + e.getMessage());
        }
    }
}
