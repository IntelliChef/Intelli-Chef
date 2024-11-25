package com.intelliChef;

import com.intelliChef.adapters.UploadImageController;
import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.IngredientListView;
import com.intelliChef.view.IngredientsDetectedView;
import com.intelliChef.view.RecipeUploadView;

import javax.swing.*;
import java.util.List;

public class Main {
    private static UploadImageController uploadImageController;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeminiAIClient vertexAIClient = new GeminiAIClient(
                    "", // TODO: Change this to a valid API key
                    "us-central1",
                    "gemini-1.5-flash-001");
            AnalyzeImageInteractor analyzeImageInteractor = new AnalyzeImageInteractor(vertexAIClient);
            uploadImageController = new UploadImageController(analyzeImageInteractor);

            showRecipeUploadView();
        });
    }

    public static void showRecipeUploadView() {
        RecipeUploadView recipeUploadView = new RecipeUploadView(uploadImageController);
        recipeUploadView.setVisible(true);
    }

    public static void showIngredientListView(List<Ingredient> ingredientList) {
        IngredientListView ingredientListView = new IngredientListView(ingredientList);
        ingredientListView.setVisible(true);
    }

    public static void showIngredientsDetectedView(List<Ingredient> ingredientList) {
        IngredientsDetectedView ingredientsDetectedView = new IngredientsDetectedView(ingredientList);
        ingredientsDetectedView.setVisible(true);
    }
}
