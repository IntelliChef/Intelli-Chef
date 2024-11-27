package com.intelliChef.use_case;

import com.intelliChef.use_case.ports.input.NavigationUseCase;
import com.intelliChef.use_case.ports.output.NavigationOutputPort;

/**
 * Implementation of the navigation use case.
 * This class handles navigation requests and delegates
 * the actual navigation to the output port.
 */
public class NavigationInteractor implements NavigationUseCase {
    private final NavigationOutputPort outputPort;

    /**
     * Constructs a new NavigationInteractor.
     *
     * @param outputPort the port through which to trigger navigation
     */
    public NavigationInteractor(NavigationOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    /**
     * Handles the navigation back request by delegating to the output port.
     */
    @Override
    public void navigateBack() {
        outputPort.navigateToPreviousView();
    }
}