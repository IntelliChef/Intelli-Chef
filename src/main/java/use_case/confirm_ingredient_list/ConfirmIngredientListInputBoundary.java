package use_case.confirm_ingredient_list;

/**
 * Input Boundary for actions which are related to Confirming Ingredient List.
 */
public interface ConfirmIngredientListInputBoundary {
    /**
     * Executes the Confirm Ingredient List Use Case.
     * @param confirmIngredientListInputData the input data
     */
    void execute(ConfirmIngredientListInputData confirmIngredientListInputData);
}
