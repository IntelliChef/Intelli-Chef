package interface_adapters.ingredient_list;
import java.util.List;

/**
 * A view model the Ingredient List View.
 * It stores the output data to be shown on the ingredient list view in a UI-friendly format.
 */
public class IngredientListViewModel {
    private final List<String> ingredientsDisplayList; // UI-friendly format

    public IngredientListViewModel(List<String> ingredientsDisplayList) {
        this.ingredientsDisplayList = ingredientsDisplayList;
    }

    public List<String> getIngredientsDisplayList() {
        return ingredientsDisplayList;
    }
}

