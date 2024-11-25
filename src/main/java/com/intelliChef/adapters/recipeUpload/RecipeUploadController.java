package com.intelliChef.adapters.recipeUpload;

import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInputData;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageOutputData;
import com.intelliChef.utils.convertImageToBytes;
import com.intelliChef.view.NavigationCall;

import java.io.IOException;

/**
 * Controller that takes path of image and turns the image into bytes so it can be passed into the interactor.
 */
public class RecipeUploadController {
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private final NavigationCall navigationCall;

    public RecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor, NavigationCall navigationCall) {
        this.analyzeImageInteractor = analyzeImageInteractor;
        this.navigationCall = navigationCall;
    }

    public void ingredientButtonClick(RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.ingredientButtonClick();
        // TODO: add call to ingredient list view with an empty list using navigationCall
    }

    public void uploadImageClick(String imagePath, RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.updateScanningLabel(true);
        try {
            byte[] imgBytes = convertImageToBytes.execute(imagePath);
            AnalyzeImageOutputData result = analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes));
            recipeUploadPresenter.uploadButtonResult(result.getIngredientList());
            navigationCall.navigateToIngredientsDetectedView(
                    new IngredientListRepository().makeRepository(result.getIngredientList()));
        } catch (IOException | RuntimeException e) {
            recipeUploadPresenter.uploadImageClickError("There was an error processing the image. Please try again.");
        }
    }
}
