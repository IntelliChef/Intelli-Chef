package view;


import interface_adapters.diet_preference.DietPreferenceController;
import frameworks.factories.ControllerFactory;
import frameworks.factories.PresenterFactory;
import interface_adapters.ingredients_detected.IngredientsDetectedController;
import interface_adapters.ingredients_detected.IngredientsDetectedPresenter;
import interface_adapters.recipe_upload.RecipeUploadController;
import interface_adapters.recipe_upload.RecipeUploadPresenter;
import use_case.IngredientRepository;
import use_case.analyze_image.AnalyzeImageInteractor;

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

        DietPreferenceController dietPreferenceController = new ControllerFactory()
                .getDietPreferenceController(navigationCall);

        dietPreferenceForm.setController(dietPreferenceController);
        return dietPreferenceForm;
    }
}
