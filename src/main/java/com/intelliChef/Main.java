package com.intelliChef;

import com.intelliChef.adapters.UploadImageController;
import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.RecipeUploadView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeminiAIClient vertexAIClient = new GeminiAIClient(
                    "", // TODO: Change this to a valid API key
                    "us-central1",
                    "gemini-1.5-flash-001");
            AnalyzeImageInteractor analyzeImageInteractor = new AnalyzeImageInteractor(vertexAIClient);
            UploadImageController uploadImageController = new UploadImageController(analyzeImageInteractor);

            RecipeUploadView recipeUploadView = new RecipeUploadView(uploadImageController);
            recipeUploadView.setVisible(true);
        });
    }
}
