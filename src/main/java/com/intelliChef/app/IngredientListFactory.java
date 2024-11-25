package com.intelliChef.app;

import com.intelliChef.adapters.ingredient_list.AddIngredientController;
import com.intelliChef.adapters.ingredient_list.ConfirmIngredientListController;
import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;
import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;
import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.add_ingredient.AddIngredientInteractor;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListInputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListInteractor;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInteractor;
import com.intelliChef.view.IngredientListView;

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
    public static IngredientListView create(IngredientRepository ingredientRepository) {
        IngredientListViewModel ingredientListViewModel = createIngredientListViewModel(ingredientRepository);
        AddIngredientController addIngredientController = createAddIngredientUseCase(ingredientRepository);
        ConfirmIngredientListController confirmIngredientListController = createConfirmIngredientListUseCase(
                ingredientRepository);

        return new IngredientListView(ingredientListViewModel, addIngredientController,
                confirmIngredientListController);
    }

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

    public static AddIngredientController createAddIngredientUseCase(IngredientRepository ingredientRepository) {
        // Create a Use Case Add Ingredient
        // Create a new presenter, interactor for the use case
        GetIngredientListOutputBoundary getIngredientListOutputBoundary = new GetIngredientListPresenter();
        AddIngredientInteractor addIngredientInteractor = new AddIngredientInteractor(
                ingredientRepository, getIngredientListOutputBoundary);
        // Create a new controller for the interactor
        return new AddIngredientController(addIngredientInteractor);
    }

    public static ConfirmIngredientListController createConfirmIngredientListUseCase(IngredientRepository
                                                                                             ingredientRepository) {
        ConfirmIngredientListInteractor confirmIngredientListInteractor = new ConfirmIngredientListInteractor(
                ingredientRepository);
        return new ConfirmIngredientListController(confirmIngredientListInteractor);
    }
}

