package frameworks.factories;

import interface_adapters.ingredients_detected.IngredientsDetectedPresenter;
import interface_adapters.recipe_upload.RecipeUploadPresenter;
import view.IngredientsDetectedView;
import view.RecipeUploadView;

public class PresenterFactory {
    public RecipeUploadPresenter getRecipeUploadPresenter(RecipeUploadView recipeUploadView) {
        return new RecipeUploadPresenter(recipeUploadView);
    }

    public IngredientsDetectedPresenter getIngredientsDetectedPresenter(IngredientsDetectedView ingredientsDetectedView) {
        return new IngredientsDetectedPresenter(ingredientsDetectedView);
    }
}
