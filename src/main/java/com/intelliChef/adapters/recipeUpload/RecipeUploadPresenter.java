package com.intelliChef.adapters.recipeUpload;

import com.intelliChef.use_case.analyzeImage.AnalyzeImageOutputData;
import com.intelliChef.view.RecipeUploadView;

import javax.swing.*;

/**
 * Presenter to display updates to the user through UI changes.
 */
public class RecipeUploadPresenter {
    private final RecipeUploadView recipeUploadView;

    public RecipeUploadPresenter(RecipeUploadView recipeUploadView) {
        this.recipeUploadView = recipeUploadView;
    }

    /**
     * Controls the visibility of the scanning label at the bottom of recipe upload view.
     * @param isVisible whether label is visible or not
     */
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

    /**
     * Removes current page from panel so next view can be displayed.
     */
    public void ingredientButtonClick() {
        recipeUploadView.dispose();
    }

    /**
     * What to display based on whether output is empty or not.
     * @param result output given from the API call
     */
    public void uploadButtonResult(AnalyzeImageOutputData result) {
        recipeUploadView.updateScanningLabel(false);
        if (result.isEmpty()) {
            recipeUploadView.showWarningMessage("No ingredients found. Please upload a valid image.");
        } else {
            recipeUploadView.dispose();
        }
    }

    /**
     * What to display if error occurred while image was being scanned.
     * @param error message
     */
    public void uploadImageClickError(String error) {
        recipeUploadView.updateScanningLabel(false);
        recipeUploadView.showErrorMessage(error);
    }
}
