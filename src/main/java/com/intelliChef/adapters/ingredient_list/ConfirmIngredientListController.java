package com.intelliChef.adapters.ingredient_list;


import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputBoundary;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputData;

import java.util.ArrayList;
import java.util.List;

public class ConfirmIngredientListController {
    private ConfirmIngredientListInputBoundary interactor;

    public ConfirmIngredientListController(ConfirmIngredientListInputBoundary interactor) {
        this.interactor = interactor;
    }
    /**
     * A method which makes input data for the ConfirmIngredientListInteractor.
     */
    public void execute(boolean[] selected) {
        List<Integer> toChange = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) { toChange.set(j, i); j++;}
        }
        final ConfirmIngredientListInputData inputData = new ConfirmIngredientListInputData(toChange);
        interactor.execute(inputData);
    }
}
