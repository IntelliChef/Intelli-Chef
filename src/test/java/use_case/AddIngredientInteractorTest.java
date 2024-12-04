package use_case;

import entities.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import use_case.add_ingredient.AddIngredientInputData;
import use_case.add_ingredient.AddIngredientInteractor;

class AddIngredientInteractorTest {
    private IngredientRepository mockRepository;
    private AddIngredientInteractor interactor;

    @BeforeEach
    void setUp() {
        mockRepository = mock(IngredientRepository.class);
        interactor = new AddIngredientInteractor(mockRepository);
    }

    @Test
    void testExecute() {
        // Arrange
        Ingredient ingredient = new Ingredient("Flour", 5.0);
        AddIngredientInputData mockInputData = mock(AddIngredientInputData.class);
        when(mockInputData.getIngredient()).thenReturn(ingredient);
        when(mockRepository.getNextId()).thenReturn(42);

        // Act
        interactor.execute(mockInputData);

        // Assert
        assertEquals(42, ingredient.getId());
        verify(mockRepository).getNextId();
        verify(mockRepository).addIngredient(ingredient);
    }
}
