package com.intelliChef.adapters.recipeUpload;

import com.intelliChef.use_case.analyzeImage.AnalyzeImageOutputData;
import com.intelliChef.view.RecipeUploadView;

import javax.swing.*;

public class RecipeUploadPresenter {
    private final RecipeUploadView recipeUploadView;

    public RecipeUploadPresenter(RecipeUploadView recipeUploadView) {
        this.recipeUploadView = recipeUploadView;
    }

    public void updateScanningLabel(boolean isVisible) {
        recipeUploadView.updateScanningLabel(isVisible);
        if (isVisible) {
            JOptionPane.showMessageDialog(
                    recipeUploadView,
                    "Please wait while image is being scanned. Click 'OK' to continue.",
                    "Scan in Progress",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public void ingredientButtonClick() {
        recipeUploadView.dispose();
    }

    public void uploadButtonResult(AnalyzeImageOutputData result) {
        recipeUploadView.updateScanningLabel(false);
        if (result.isEmpty()) {
            recipeUploadView.showWarningMessage("No ingredients found. Please upload a valid image.");
        } else {
            recipeUploadView.dispose();
        }
    }

    public void uploadImageClickError(String error) {
        recipeUploadView.updateScanningLabel(false);
        recipeUploadView.showErrorMessage(error);
    }
}
