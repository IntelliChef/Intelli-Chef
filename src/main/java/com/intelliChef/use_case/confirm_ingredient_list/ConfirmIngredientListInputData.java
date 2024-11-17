package com.intelliChef.use_case.confirm_ingredient_list;

/**
 * The Input Data for the Confirm Ingredient List Use Case.
 */
public class ConfirmIngredientListInputData {
    private final boolean confirm;

    public ConfirmIngredientListInputData(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isConfirm() {
        return confirm;
    }
}
