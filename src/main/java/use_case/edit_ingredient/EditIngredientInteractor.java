package use_case.edit_ingredient;

import use_case.IngredientRepository;

/**
 * The Edit Ingredient Interactor
 */
public class EditIngredientInteractor implements EditIngredientInputBoundary {
    IngredientRepository myRepository;

    public EditIngredientInteractor(IngredientRepository repository) {
        myRepository = repository;
    }

    /**
     * Executes the Edit Ingredient Use Case based on the input data
     * @param editIngredientInputData input data for the Edit Ingredient Use Case
     */
    @Override
    public void execute(EditIngredientInputData editIngredientInputData) {
        int id = editIngredientInputData.getId();
        double quantity = editIngredientInputData.getQuantity();
        myRepository.updateQuantity(id, quantity);
    }
}