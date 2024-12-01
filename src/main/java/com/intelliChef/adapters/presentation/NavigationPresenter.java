package com.intelliChef.adapters.presentation;

import com.intelliChef.use_case.ports.output.NavigationOutputPort;

/**
 * Presenter for navigation actions.
 * This class implements the output port from the use case layer and
 * delegates navigation requests to the view.
 */
public class NavigationPresenter implements NavigationOutputPort {
    private final NavigationView view;

    /**
     * Constructs a new NavigationPresenter.
     *
     * @param view the view that will handle the navigation
     */
    public NavigationPresenter(NavigationView view) {
        this.view = view;
    }

    /**
     * Triggers navigation back to the previous view.
     */
    @Override
    public void navigateToPreviousView() {
        view.navigateBack();
    }
}