package com.intelliChef.adapters;

import com.intelliChef.view.RecipeUploadView;

import javax.swing.*;

public class UploadImagePresenter {
    private final RecipeUploadView recipeUploadView;

    public UploadImagePresenter(RecipeUploadView recipeUploadView) {
        this.recipeUploadView = recipeUploadView;
    }

    public void updateScanningLabel(boolean isVisible) {
        recipeUploadView.updateScanningLabel(isVisible);
        if (isVisible) {
            JOptionPane.showMessageDialog(
                    recipeUploadView,
                    "Scanning Image. Please wait...",
                    "Scan in Progress",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public void ingredientButtonClick() {
        recipeUploadView.dispose();
    }

    public void uploadImageClickSuccess() {
        recipeUploadView.dispose();
    }

    public void uploadImageClickFail() {
        JOptionPane.showMessageDialog(
                recipeUploadView,
                "No ingredients found. Please upload a valid image.",
                "Ingredient Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public void uploadImageClickError() {
        JOptionPane.showMessageDialog(
                recipeUploadView,
                "There was an error processing the image. Please try again.",
                "Undefined Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
