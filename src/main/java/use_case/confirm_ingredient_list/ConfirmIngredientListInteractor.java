package use_case.confirm_ingredient_list;

import use_case.IngredientRepository;

import java.util.List;

/**
 * The Confirm Ingredient Interactor
 */
public class ConfirmIngredientListInteractor implements ConfirmIngredientListInputBoundary {
    private final IngredientRepository repository;

    public ConfirmIngredientListInteractor(IngredientRepository repository) {
        this.repository = repository;
    }
    /**
     * Executes the Confirm Ingredient List Use Case.
     * Updates the ingredient list so that now it only contains checked ingredients.
     * @param inputData the input data which are the list of ids for the ingredients that should be deleted.
     */
    @Override
    public void execute(ConfirmIngredientListInputData inputData) {
        List<Integer> ids = inputData.getIds();
        for (Integer id : ids) {
            repository.deleteIngredient(id);
        }
    }
}
