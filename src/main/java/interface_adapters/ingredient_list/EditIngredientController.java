package interface_adapters.ingredient_list;

import use_case.edit_ingredient.EditIngredientInputBoundary;
import use_case.edit_ingredient.EditIngredientInputData;

/**
 * Controller for the Edit Ingredient Use Case
 */
public class EditIngredientController {
    private final EditIngredientInputBoundary interactor;

    public EditIngredientController(EditIngredientInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the Edit Ingredient Use case.
     * @param id id of the ingredient to be edited
     * @param quantity updated quantity
     */
    public void execute(int id, double quantity) {
        final EditIngredientInputData inputData = new EditIngredientInputData(id, quantity);
        interactor.execute(inputData);
    }
}
