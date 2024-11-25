package com.intelliChef;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.InteractorFactory;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.IngredientListView;
import com.intelliChef.view.IngredientsDetectedView;
import com.intelliChef.view.RecipeUploadView;
import com.intelliChef.view.ViewFactory;

import javax.swing.*;
import java.util.List;

public class Main {
    static ViewFactory viewFactory = new ViewFactory();
    static AnalyzeImageInteractor analyzeImageInteractor;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            analyzeImageInteractor = new InteractorFactory()
                    .createAnalyzeImageInteractor(new GeminiAIClient(
                            "",
                            "us-central1",
                            "gemini-1.5-flash-001"));
            showRecipeUploadView();
//            ViewManager viewManager = new ViewManager(new InteractorFactory, new ViewFactory, geminiAIClient);
        });
    }

    public static void showRecipeUploadView() {
        RecipeUploadView recipeUploadView = viewFactory.createRecipeUploadView(analyzeImageInteractor);
        recipeUploadView.setVisible(true);
    }

    public static void showIngredientsDetectedView(List<Ingredient> ingredientList) {
        IngredientsDetectedView ingredientsDetectedView = viewFactory.createIngredientsDetectedView(ingredientList);
        ingredientsDetectedView.setVisible(true);
    }

    public static void showIngredientListView(List<Ingredient> ingredientList) {
        IngredientListView ingredientListView = new IngredientListView(ingredientList);
        ingredientListView.setVisible(true);
    }
}
