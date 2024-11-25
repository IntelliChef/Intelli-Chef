package com.intelliChef.use_case;

import com.intelliChef.data_access.GeminiAIforRecipe;
import com.intelliChef.entities.Recipe;
import com.intelliChef.entities.DietPreference;
import infrastructure.FileStorage;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class RecipeUseCase {
    private GeminiAIforRecipe geminiService;
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
