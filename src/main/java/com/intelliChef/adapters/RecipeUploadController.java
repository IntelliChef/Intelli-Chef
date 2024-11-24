package com.intelliChef.adapters;

import com.intelliChef.Main;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInputData;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageOutputData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller that takes path of image and turns the image into bytes so it can be passed into the interactor.
 */
public class RecipeUploadController {
    private List<Ingredient> ingredientList;
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
            byte[] imgBytes = getImageBytes(imagePath);
            AnalyzeImageOutputData result = analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes));
            recipeUploadPresenter.uploadButtonResult(result.getIngredientList());
        } catch (IOException | RuntimeException e) {
            recipeUploadPresenter.uploadImageClickError("There was an error processing the image. Please try again.");
        }
    }

    private static byte[] getImageBytes(String imagePath) throws RuntimeException, IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
