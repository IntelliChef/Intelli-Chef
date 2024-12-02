package interface_adapters.ingredients_detected;

import view.IngredientsDetectedView;

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
