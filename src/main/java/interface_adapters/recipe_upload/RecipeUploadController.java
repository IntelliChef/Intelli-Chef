package interface_adapters.recipe_upload;

import data_access.IngredientListRepository;
import use_case.analyze_image.AnalyzeImageInputData;
import use_case.analyze_image.AnalyzeImageInteractor;
import use_case.analyze_image.AnalyzeImageOutputData;
import utils.convertImageToBytes;
import view.NavigationCall;

import java.io.IOException;

/**
 * Controller that functions the button clicks in recipe upload view.
 */
public class RecipeUploadController {
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private final NavigationCall navigationCall;

    public RecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor, NavigationCall navigationCall) {
        this.analyzeImageInteractor = analyzeImageInteractor;
        this.navigationCall = navigationCall;
    }

    /**
     * What happens when ingredient button is clicked.
     * @param recipeUploadPresenter to delegate UI related tasks to presenter
     */
    public void ingredientButtonClick(RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.ingredientButtonClick();
        navigationCall.navigateToIngredientListView(new IngredientListRepository());
    }

    /**
     * What happens when upload image button is clicked.
     * @param imagePath that is selected from files
     * @param recipeUploadPresenter to delegate UI related tasks to presenter
     */
    public void uploadImageClick(String imagePath, RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.updateScanningLabel(true);
        try {
            byte[] imgBytes = convertImageToBytes.execute(imagePath);
            AnalyzeImageOutputData result = analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes));
            recipeUploadPresenter.uploadButtonResult(result);
            navigationCall.navigateToIngredientsDetectedView(new IngredientListRepository().makeRepository(result.getIngredientList()));
        } catch (IOException | RuntimeException e) {
            recipeUploadPresenter.uploadImageClickError("There was an error processing the image. Please try again.");
        }
    }
}
