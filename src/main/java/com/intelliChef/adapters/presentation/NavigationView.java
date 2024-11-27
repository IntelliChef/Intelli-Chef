package com.intelliChef.adapters.presentation;

/**
 * Interface for handling navigation actions.
 * This interface is in the interface adapters layer and will be
 * implemented by the UI framework in the outermost layer.
 */
public interface NavigationView {
    /**
     * Navigates back to the previous view.
     */
    void navigateBack();
}