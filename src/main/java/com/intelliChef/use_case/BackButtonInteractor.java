package com.intelliChef.use_case;

public class BackButtonInteractor {
    public interface OutputBoundary {
        void navigateBackToRecipeList();
    }

    private final OutputBoundary outputBoundary;

    public BackButtonInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void goBack() {
        outputBoundary.navigateBackToRecipeList();
    }
}
