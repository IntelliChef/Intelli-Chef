package com.intelliChef.adapters;

import com.intelliChef.Main;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInputData;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

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

    public void uploadImageClick(String imagePath,
                                 RecipeUploadPresenter recipeUploadPresenter) {
        try {
            recipeUploadPresenter.updateScanningLabel(true);
            byte[] imgBytes = getImageBytes(imagePath);
            ingredientList = analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes)).getIngredientList();
        } catch (IOException | RuntimeException e) {
            recipeUploadPresenter.updateScanningLabel(false);
            recipeUploadPresenter.uploadImageClickError();
        }

        recipeUploadPresenter.updateScanningLabel(false);

        if (ingredientList.isEmpty()) {
            recipeUploadPresenter.uploadImageClickFail();
        } else {
            Main.showIngredientsDetectedView(ingredientList);
            recipeUploadPresenter.uploadImageClickSuccess();
        }
    }

    private static byte[] getImageBytes(String imagePath) throws RuntimeException, IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}