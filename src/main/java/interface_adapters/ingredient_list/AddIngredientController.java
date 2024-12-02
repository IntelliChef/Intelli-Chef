package interface_adapters.ingredient_list;

import use_case.add_ingredient.AddIngredientInputData;
import use_case.add_ingredient.AddIngredientInteractor;

/**
 * Controller for the Add Ingredient Use Case.
 */
public class AddIngredientController {
    private final AddIngredientInteractor interactor;

    public AddIngredientController(AddIngredientInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes Add Ingredient Use Case.
     * @param name the name of the ingredient to be added
     * @param quantity the quantity of the ingredient to be added
     */
    public void execute(String name, String quantity) {
        final AddIngredientInputData inputData = new AddIngredientInputData(name, quantity);
        interactor.execute(inputData);
    }
}
