package com.intelliChef.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.intelliChef.use_case.ports.output.NavigationOutputPort;

class NavigationInteractorTest {
    @Mock private NavigationOutputPort outputPort;
    private NavigationInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new NavigationInteractor(outputPort);
    }

    @Test
    void testNavigation() {
        // Execute
        interactor.navigateBack();

        verify(outputPort).navigateToPreviousView();
        verifyNoMoreInteractions(outputPort);
    }
}