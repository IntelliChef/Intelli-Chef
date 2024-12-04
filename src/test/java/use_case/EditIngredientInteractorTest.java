package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.edit_ingredient.EditIngredientInputData;
import use_case.edit_ingredient.EditIngredientInteractor;

import static org.mockito.Mockito.*;

class EditIngredientInteractorTest {
    private IngredientRepository mockRepository;
    private EditIngredientInteractor interactor;

    @BeforeEach
    void setUp() {
        mockRepository = mock(IngredientRepository.class);
        interactor = new EditIngredientInteractor(mockRepository);
    }

    @Test
    void testExecute() {
        // Arrange
        EditIngredientInputData mockInputData = mock(EditIngredientInputData.class);
        int ingredientId = 10;
        double newQuantity = 3.5;

        when(mockInputData.getId()).thenReturn(ingredientId);
        when(mockInputData.getQuantity()).thenReturn(newQuantity);

        // Act
        interactor.execute(mockInputData);

        // Assert
        verify(mockRepository).updateQuantity(ingredientId, newQuantity);
    }
}

