package com.intelliChef.adapters.ingredient_list;


import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputBoundary;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputData;
import com.intelliChef.view.NavigationCall;

import java.util.ArrayList;
import java.util.List;

public class ConfirmIngredientListController {
    private ConfirmIngredientListInputBoundary interactor;
    private NavigationCall navigationCall;

    public ConfirmIngredientListController(ConfirmIngredientListInputBoundary interactor,
                                           NavigationCall navigationCall) {
        this.interactor = interactor;
        this.navigationCall = navigationCall;
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
        navigationCall.navigateToDietPreferenceView();
    }
}
