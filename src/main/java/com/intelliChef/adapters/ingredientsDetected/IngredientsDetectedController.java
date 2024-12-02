package com.intelliChef.adapters.ingredientsDetected;

import com.intelliChef.use_case.IngredientListRepositoryInterface;
import com.intelliChef.view.NavigationCall;

public class IngredientsDetectedController {
    private final NavigationCall navigationCall;

    public IngredientsDetectedController(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void continueButtonClick(IngredientListRepositoryInterface ingredientRepo,
                                    IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.continueButtonClick();
        navigationCall.navigateToIngredientListView(ingredientRepo);
    }

    public void cancelButtonClick(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.cancelButtonClick();
        navigationCall.navigateToRecipeUploadView();
    }
}
