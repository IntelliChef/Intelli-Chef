package frameworks.factories;

import interface_adapters.ingredient_list.*;
import use_case.IngredientRepository;
import use_case.add_ingredient.AddIngredientInteractor;
import use_case.edit_ingredient.EditIngredientInteractor;
import use_case.get_ingredient_list.GetIngredientListInputBoundary;
import use_case.get_ingredient_list.GetIngredientListInteractor;
import use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import use_case.confirm_ingredient_list.ConfirmIngredientListInteractor;
import view.IngredientListView;
import view.NavigationCall;

/**
 * This class contains the static factory function for creating the IngredientListView.
 */
public final class IngredientListFactory {
    /**
     * Prevent instantiation.
     */
    private IngredientListFactory() {}

    /**
     * Factory function for creating the IngredientListView.
     */
    public static IngredientListView create(IngredientRepository ingredientRepository, NavigationCall navigationCall) {
        IngredientListViewModel ingredientListViewModel = createIngredientListViewModel(ingredientRepository);
        AddIngredientController addIngredientController = createAddIngredientUseCase(ingredientRepository);
        ConfirmIngredientListController confirmIngredientListController = createConfirmIngredientListUseCase(
                ingredientRepository, navigationCall);
        EditIngredientController editIngredientController = createEditIngredientUseCase(ingredientRepository);

        return new IngredientListView(ingredientListViewModel, addIngredientController,
                confirmIngredientListController, editIngredientController);
    }

    /**
     * Factory function for the Ingredient List View Model.
     */
    public static IngredientListViewModel createIngredientListViewModel(IngredientRepository ingredientRepository) {
        // Create a Use Case Get Ingredient List
        // Create a new presenter, interactor for the use case
        GetIngredientListOutputBoundary getIngredientListOutputBoundary = new GetIngredientListPresenter();
        GetIngredientListInputBoundary getIngredientListInputBoundary = new GetIngredientListInteractor(
                ingredientRepository,getIngredientListOutputBoundary);
        // Interactor fetches the data and pass it to presenter, presenter creates the view model
        getIngredientListInputBoundary.getIngredients();
        return getIngredientListOutputBoundary.getViewModel();
    }

    /**
     * Factory function for creating the Add Ingredient Use Case.
     */
    public static AddIngredientController createAddIngredientUseCase(IngredientRepository ingredientRepository) {
        // Create a Use Case Add Ingredient
        // Create a new interactor for the use case
        AddIngredientInteractor addIngredientInteractor = new AddIngredientInteractor(
                ingredientRepository);
        // Create a new controller for the interactor
        return new AddIngredientController(addIngredientInteractor);
    }

    /**
     * Factory function for creating the Edit Ingredient Use Case.
     */
    public static EditIngredientController createEditIngredientUseCase(IngredientRepository ingredientRepository) {
        // Create a Use Case Add Ingredient
        // Create a new interactor for the use case
        EditIngredientInteractor editIngredientInteractor = new EditIngredientInteractor(ingredientRepository);
        // Create a new controller for the interactor
        return new EditIngredientController(editIngredientInteractor);
    }

    /**
     * Factory function for creating the Confirm Ingredient List Use Case.
     */
    public static ConfirmIngredientListController createConfirmIngredientListUseCase(IngredientRepository
                                                                                             ingredientRepository,
                                                                                     NavigationCall navigationCall) {
        ConfirmIngredientListInteractor confirmIngredientListInteractor = new ConfirmIngredientListInteractor(
                ingredientRepository);
        return new ConfirmIngredientListController(confirmIngredientListInteractor, navigationCall);
    }
}

