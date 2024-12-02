package com.intelliChef.view;

import com.intelliChef.adapters.ControllerFactory;
import com.intelliChef.adapters.PresenterFactory;
import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedPresenter;
import com.intelliChef.adapters.recipeUpload.RecipeUploadController;
import com.intelliChef.adapters.recipeUpload.RecipeUploadPresenter;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

/**
 * Factory to create views.
 */
public class ViewFactory {
    private NavigationCall navigationCall;

    public ViewFactory(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void setNavigator(ViewManager viewManager) {
        navigationCall = viewManager;
    }

    public RecipeUploadView createRecipeUploadView(AnalyzeImageInteractor analyzeImageInteractor) {
        RecipeUploadView recipeUploadView = new RecipeUploadView();

        RecipeUploadController recipeUploadController = new ControllerFactory()
                .getRecipeUploadController(analyzeImageInteractor, navigationCall);
        RecipeUploadPresenter recipeUploadPresenter = new PresenterFactory().
                getRecipeUploadPresenter(recipeUploadView);

        recipeUploadView.setRecipeUploadController(recipeUploadController);
        recipeUploadView.setRecipeUploadPresenter(recipeUploadPresenter);
        return recipeUploadView;
    }

    public IngredientsDetectedView createIngredientsDetectedView(IngredientRepository ingredientRepo) {
        IngredientsDetectedView ingredientsDetectedView = new IngredientsDetectedView(ingredientRepo);

        IngredientsDetectedController ingredientsDetectedController = new ControllerFactory()
                .getIngredientsDetectedController(navigationCall);
        IngredientsDetectedPresenter ingredientsDetectedPresenter = new PresenterFactory()
                .getIngredientsDetectedPresenter(ingredientsDetectedView);

        ingredientsDetectedView.setIngredientsDetectedController(ingredientsDetectedController);
        ingredientsDetectedView.setIngredientsDetectedPresenter(ingredientsDetectedPresenter);
        return ingredientsDetectedView;
    }

    public DietPreferenceForm createDietPreferenceForm(IngredientRepository ingredientRepo) {
        DietPreferenceForm dietPreferenceForm = new DietPreferenceForm(ingredientRepo);


    }
}
