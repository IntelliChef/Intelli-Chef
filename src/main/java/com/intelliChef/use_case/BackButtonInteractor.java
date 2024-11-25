package com.intelliChef.use_case;

/**
 * Handles the back button interaction to navigate from a detailed view
 * back to the recipe list.
 */
public class BackButtonInteractor {

    /**
     * Defines the output boundary for the back button interaction.
     * Classes implementing this interface should handle the navigation logic.
     */
    public interface OutputBoundary {
        /**
         * Navigates back to the recipe list view.
         */
        void navigateBackToRecipeList();
    }

    private final OutputBoundary outputBoundary;

    /**
     * Constructs a new BackButtonInteractor with the specified output boundary.
     *
     * @param outputBoundary the {@link OutputBoundary} instance to handle the navigation logic
     */
    public BackButtonInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Triggers the navigation back to the recipe list view by delegating
     * the action to the output boundary.
     */
    public void goBack() {
        outputBoundary.navigateBackToRecipeList();
    }
}
