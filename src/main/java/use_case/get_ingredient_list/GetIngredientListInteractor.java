package use_case.get_ingredient_list;

import entities.Ingredient;
import use_case.IngredientRepository;

import java.util.List;

/**
 * The Get Ingredient List Use Case.
 */
public class GetIngredientListInteractor implements GetIngredientListInputBoundary {
    private final IngredientRepository ingredientRepository; // Data Access Interface
    private final GetIngredientListOutputBoundary outputBoundary; // Presenter Interface

    public GetIngredientListInteractor(IngredientRepository ingredientRepository,
                                       GetIngredientListOutputBoundary outputBoundary) {
        this.ingredientRepository = ingredientRepository;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes the Get Ingredient List Use Case
     * Fetches ingredient list data from the DAO and passes them to the presenter.
     */
    public void getIngredients() {
        // Fetch data from the repository
        List<Ingredient> ingredients = ingredientRepository.getAllIngredients();

        // Create output data and pass it to the presenter
        GetIngredientListOutputData outputData = new GetIngredientListOutputData(ingredients);
        outputBoundary.present(outputData);
    }
}

