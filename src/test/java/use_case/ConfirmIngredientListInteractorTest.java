package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.confirm_ingredient_list.ConfirmIngredientListInputData;
import use_case.confirm_ingredient_list.ConfirmIngredientListInteractor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ConfirmIngredientListInteractorTest {
    private IngredientRepository mockRepository;
    private ConfirmIngredientListInteractor interactor;

    @BeforeEach
    void setUp() {
        mockRepository = mock(IngredientRepository.class);
        interactor = new ConfirmIngredientListInteractor(mockRepository);
    }

    @Test
    void testExecute() {
        // Arrange
        List<Integer> idsToDelete = Arrays.asList(1, 2, 3);
        ConfirmIngredientListInputData mockInputData = mock(ConfirmIngredientListInputData.class);
        when(mockInputData.getIds()).thenReturn(idsToDelete);

        // Act
        interactor.execute(mockInputData);

        // Assert
        for (Integer id : idsToDelete) {
            verify(mockRepository).deleteIngredient(id);
        }
    }
}

