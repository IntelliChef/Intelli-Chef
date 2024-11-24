package com.intelliChef.adapters.recipeUpload;

import com.intelliChef.Main;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInputData;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageOutputData;
import com.intelliChef.utils.convertImageToBytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller that takes path of image and turns the image into bytes so it can be passed into the interactor.
 */
public class RecipeUploadController {
    private final AnalyzeImageInteractor analyzeImageInteractor;

    public RecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor) {
        this.analyzeImageInteractor = analyzeImageInteractor;
    }

    public void ingredientButtonClick(RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.ingredientButtonClick();
        Main.showIngredientListView(new ArrayList<>());
    }

    public void uploadImageClick(String imagePath, RecipeUploadPresenter recipeUploadPresenter) {
        recipeUploadPresenter.updateScanningLabel(true);
        try {
            byte[] imgBytes = convertImageToBytes.execute(imagePath);
            AnalyzeImageOutputData result = analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes));
            recipeUploadPresenter.uploadButtonResult(result.getIngredientList());
        } catch (IOException | RuntimeException e) {
            recipeUploadPresenter.uploadImageClickError("There was an error processing the image. Please try again.");
        }
    }
}
