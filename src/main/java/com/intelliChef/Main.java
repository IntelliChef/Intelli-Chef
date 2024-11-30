package com.intelliChef;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.InteractorFactory;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.*;
import com.intelliChef.frameworks.factories.RecipeViewFactory;
import com.intelliChef.use_case.LoadRecipesInteractor;
import com.intelliChef.use_case.SelectRecipeInteractor;
import com.intelliChef.use_case.NavigationInteractor;
import com.intelliChef.adapters.presentation.*;
import com.intelliChef.adapters.persistence.JSONRecipeRepository;
import com.intelliChef.use_case.ports.output.RecipeRepository;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Original components
            ViewFactory viewFactory = new ViewFactory(null);
            AnalyzeImageInteractor analyzeImageInteractor = new InteractorFactory().createAnalyzeImageInteractor(
                    new GeminiAIClient(
                            "",
                            "us-central1",
                            "gemini-1.5-flash-001"));

            // Recipe-related components
            RecipeRepository repository = new JSONRecipeRepository();
            RecipeViewFactory recipeViewFactory = new RecipeViewFactory();

            // Create temporary views to pass to presenters
            RecipeListGUI tempListView = new RecipeListGUI(null);
            RecipeDetailGUI tempDetailView = new RecipeDetailGUI(null);

            // Create presenters
            RecipeListPresenter listPresenter = new RecipeListPresenter(tempListView);
            RecipeDetailPresenter detailPresenter = new RecipeDetailPresenter(tempDetailView);
            NavigationPresenter navigationPresenter = new NavigationPresenter(tempListView);

            // Create use cases
            LoadRecipesInteractor loadRecipesInteractor = new LoadRecipesInteractor(repository, listPresenter);
            SelectRecipeInteractor selectRecipeInteractor = new SelectRecipeInteractor(detailPresenter);
            NavigationInteractor navigationInteractor = new NavigationInteractor(navigationPresenter);

            // Create view manager with all components
            ViewManager viewManager = new ViewManager(
                    viewFactory,
                    analyzeImageInteractor,
                    recipeViewFactory,
                    loadRecipesInteractor,
                    selectRecipeInteractor,
                    navigationInteractor);

            viewFactory.setNavigator(viewManager);

            // Start the application
            viewManager.navigateToRecipeUploadView();
        });
    }
}