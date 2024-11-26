package com.intelliChef.view;

import com.intelliChef.entities.Recipe;
import com.intelliChef.presenters.BackButtonPresenter;
import com.intelliChef.presenters.RecipePresenter;
import com.intelliChef.use_case.BackButtonInteractor;
import com.intelliChef.use_case.RecipeSelectionInteractor;
import com.intelliChef.presenters.RecipeListView;

public class RecipeViewFactory {
    private NavigationCall navigationCall;

    public RecipeViewFactory(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void setNavigator(ViewManager viewManager) {
        this.navigationCall = viewManager;
    }

    public RecipeListGUI createRecipeListView() {
        RecipeListGUI recipeListView = new RecipeListGUI();

        RecipePresenter recipePresenter = new RecipePresenter(recipeListView);
        RecipeSelectionInteractor recipeSelectionInteractor = new RecipeSelectionInteractor(recipePresenter);

        recipeListView.setInteractor(recipeSelectionInteractor);
        return recipeListView;
    }

    public RecipeDetailGUI createRecipeDetailView(Recipe recipe, RecipeListView recipeListView) {
        RecipeDetailGUI recipeDetailView = new RecipeDetailGUI(recipe, recipeListView);

        BackButtonPresenter backButtonPresenter = new BackButtonPresenter(recipeListView);
        BackButtonInteractor backButtonInteractor = new BackButtonInteractor(backButtonPresenter);

        recipeDetailView.setBackButtonInteractor(backButtonInteractor);
        return recipeDetailView;
    }
}
