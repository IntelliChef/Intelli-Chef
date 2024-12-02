package interface_adapters.ingredient_list;


import use_case.confirm_ingredient_list.ConfirmIngredientListInputBoundary;
import use_case.confirm_ingredient_list.ConfirmIngredientListInputData;
import view.NavigationCall;

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
     * @param toDelete a list of ids of ingredients to be deleted
     */
    public void execute(List<Integer> toDelete) {
        final ConfirmIngredientListInputData inputData = new ConfirmIngredientListInputData(toDelete);
        interactor.execute(inputData);
        // Navigate to the next view
        navigationCall.navigateToDietPreferenceView();
    }
}
