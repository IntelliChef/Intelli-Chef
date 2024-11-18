package com.intelliChef.view;

import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;
import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;

import java.util.Arrays;

public class MainforIngredientListView {
    public static void main(String[] args) {
        // Mock data for output data (simulating what the interactor would return)
        GetIngredientListOutputData outputData = new GetIngredientListOutputData(
                Arrays.asList(
                        new Ingredient("Tomato", 2),
                        new Ingredient("Onion", 1),
                        new Ingredient("Cheese", 200)
                )
        );

        // Initialize the Presenter
        GetIngredientListPresenter presenter = new GetIngredientListPresenter();

        // Simulate interactor calling the presenter to present the output data
        presenter.present(outputData);

        // Initialize the View using the Presenter
        IngredientListView view = new IngredientListView(presenter);

        // Set up the Confirm button listener
//        view.setConfirmButtonListener(e -> {
//            System.out.println("Confirm button clicked. Transitioning to the next page...");
//            // Logic to transition to the next view
//            NextView nextView = new NextView();
//            nextView.setVisible(true);
//        });

        // Show the view
        view.setVisible(true);
    }
}

