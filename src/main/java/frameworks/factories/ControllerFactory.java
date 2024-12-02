package frameworks.factories;


import interface_adapters.diet_preference.DietPreferenceController;
import interface_adapters.ingredients_detected.IngredientsDetectedController;
import interface_adapters.recipe_upload.RecipeUploadController;
import use_case.analyze_image.AnalyzeImageInteractor;
import view.NavigationCall;

public class ControllerFactory {
    public RecipeUploadController getRecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor, NavigationCall navigationCall) {
        return new RecipeUploadController(analyzeImageInteractor, navigationCall);
    }

    public IngredientsDetectedController getIngredientsDetectedController(NavigationCall navigationCall) {
        return new IngredientsDetectedController(navigationCall);
    }
    public DietPreferenceController getDietPreferenceController(NavigationCall navigationCall) {
        return new DietPreferenceController(navigationCall);
    }

}
