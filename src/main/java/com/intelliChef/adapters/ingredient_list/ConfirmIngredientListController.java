package com.intelliChef.adapters.ingredient_list;


import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputBoundary;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputData;
import com.intelliChef.view.NavigationCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Confirm Ingredient Use Case
 */
public class ConfirmIngredientListController {
    private ConfirmIngredientListInputBoundary interactor;
    private NavigationCall navigationCall;

    public ConfirmIngredientListController(ConfirmIngredientListInputBoundary interactor,
                                           NavigationCall navigationCall) {
        this.interactor = interactor;
        this.navigationCall = navigationCall;
    }

    /**
     * Executes the Confirm Ingredient Use Case
     * @param selected a list of booleans that expresses whether an ingredient should be kept
     */
    public void execute(boolean[] selected) {
        List<Integer> toChange = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < selected.length; i++) {
            if (!selected[i]) { toChange.set(j, i); j++;}
        }
        final ConfirmIngredientListInputData inputData = new ConfirmIngredientListInputData(toChange);
        interactor.execute(inputData);
        // Navigate to the next view
        navigationCall.navigateToDietPreferenceView();
    }
}
