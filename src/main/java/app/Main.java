package app;

import com.formdev.flatlaf.FlatIntelliJLaf;
import data_access.GeminiAIClient;
import frameworks.factories.RecipeViewFactory;
import frameworks.factories.InteractorFactory;
import use_case.analyze_image.AnalyzeImageInteractor;
import view.ViewFactory;
import view.ViewManager;

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
            RecipeViewFactory recipeViewFactory = new RecipeViewFactory();
            AnalyzeImageInteractor analyzeImageInteractor = new InteractorFactory().createAnalyzeImageInteractor(
                    new GeminiAIClient(
                        "",
                        "us-central1",
                        "gemini-1.5-flash-001"));
            ViewManager viewManager = new ViewManager(viewFactory, analyzeImageInteractor, recipeViewFactory);
            viewFactory.setNavigator(viewManager);
            viewManager.navigateToRecipeUploadView();
        });
    }
}
