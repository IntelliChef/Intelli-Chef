package use_case;

import use_case.view_interactors.NavigationInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import use_case.ports.output.NavigationOutputPort;

@ExtendWith(MockitoExtension.class)
class NavigationInteractorTest {
    @Mock private NavigationOutputPort outputPort;
    private NavigationInteractor interactor;

    @BeforeEach
    void setUp() {
        interactor = new NavigationInteractor(outputPort);
    }

    @Test
    void testSuccessfulNavigation() {
        // Act
        interactor.navigateBack();

        // Assert
        verify(outputPort).navigateToPreviousView();
        verifyNoMoreInteractions(outputPort);
    }
}