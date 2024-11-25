package com.intelliChef.use_case.confirm_ingredient_list;

import java.util.List;

/**
 * The Input Data for the Confirm Ingredient List Use Case.
 */
public class ConfirmIngredientListInputData {
    private final List<Integer> ids;

    public ConfirmIngredientListInputData(List<Integer> ids) {
        this.ids = ids;
    }
    public List<Integer> getIds() {
        return ids;
    }
}
