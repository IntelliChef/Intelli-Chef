package com.intelliChef.adapters.ingredientsDetected;

import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.view.NavigationCall;

public class IngredientsDetectedController {
    private final NavigationCall navigationCall;

    public IngredientsDetectedController(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void continueButtonClick(IngredientListRepository ingredientRepo,
                                    IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.continueButtonClick();
        // TODO: add call to ingredient list view with ingredient list using navigationCall
    }

    public void cancelButtonClick(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.cancelButtonClick();
        navigationCall.navigateToRecipeUploadView();
    }
}
