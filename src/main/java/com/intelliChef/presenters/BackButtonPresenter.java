package com.intelliChef.presenters;

import com.intelliChef.use_case.BackButtonInteractor.OutputBoundary;
import javax.swing.*;

/**
 * The presenter responsible for handling the back button functionality
 * to navigate from a detailed recipe view back to the recipe list view.
 */
public class BackButtonPresenter implements OutputBoundary {
    private final RecipeListView view;
    private JFrame detailFrame;

    /**
     * Constructs a new BackButtonPresenter with the specified recipe list view.
     *
     * @param view the {@link RecipeListView} instance that displays the list of recipes
     */
    public BackButtonPresenter(RecipeListView view) {
        this.view = view;
    }

    /**
     * Sets the frame that displays the recipe details.
     *
     * @param detailFrame the {@link JFrame} instance showing the recipe details
     */
    public void setDetailFrame(JFrame detailFrame) {
        this.detailFrame = detailFrame;
    }

    /**
     * Navigates back to the recipe list view.
     * Closes the detail frame if it is currently displayed.
     */
    @Override
    public void navigateBackToRecipeList() {
        view.showRecipeList();
        if (detailFrame != null) {
            detailFrame.dispose();
        }
    }
}
