package com.intelliChef.use_case.ports.input;

/**
 * Input port for navigation actions.
 * This interface defines the use case of navigating back in the application.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be called by the outer layers (adapters/UI) and implemented by an interactor.
 */
public interface NavigationUseCase {
    /**
     * Handles the navigation back action.
     * The actual implementation will process the navigation request
     * and trigger the appropriate view change through the output port.
     */
    void navigateBack();
}