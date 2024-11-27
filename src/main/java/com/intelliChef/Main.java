package com.intelliChef;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.InteractorFactory;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Unable to load the flatLaf library for UI component.");
        }

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
