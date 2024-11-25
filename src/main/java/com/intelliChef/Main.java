package com.intelliChef;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.InteractorFactory;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewFactory viewFactory = new ViewFactory(null);
            AnalyzeImageInteractor analyzeImageInteractor = new InteractorFactory().createAnalyzeImageInteractor(
                    new GeminiAIClient(
                        "",
                        "us-central1",
                        "gemini-1.5-flash-001"));
            ViewManager viewManager = new ViewManager(viewFactory, analyzeImageInteractor);
            viewFactory.setNavigator(viewManager);
            viewManager.navigateToRecipeUploadView();
        });
    }
}
