package com.intelliChef.adapters.ingredientsDetected;

import com.intelliChef.view.IngredientsDetectedView;

public class IngredientsDetectedPresenter {
    private final IngredientsDetectedView ingredientsDetectedView;

    public IngredientsDetectedPresenter(IngredientsDetectedView ingredientsDetectedView) {
        this.ingredientsDetectedView = ingredientsDetectedView;
    }

    public void continueButtonClick() {
        ingredientsDetectedView.dispose();
    }

    public void cancelButtonClick() {
        ingredientsDetectedView.dispose();
    }
}
