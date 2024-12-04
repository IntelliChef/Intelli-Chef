package use_case;

import entities.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.get_ingredient_list.GetIngredientListInteractor;
import use_case.get_ingredient_list.GetIngredientListOutputBoundary;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class GetIngredientListInteractorTest {
    private IngredientRepository mockRepository;
    private GetIngredientListOutputBoundary mockOutputBoundary;
    private GetIngredientListInteractor interactor;

    @BeforeEach
    void setUp() {
        mockRepository = mock(IngredientRepository.class);
        mockOutputBoundary = mock(GetIngredientListOutputBoundary.class);
        interactor = new GetIngredientListInteractor(mockRepository, mockOutputBoundary);
    }

    @Test
    void testGetIngredients() {
        // Arrange
        List<Ingredient> mockIngredients = Arrays.asList(
                new Ingredient("Flour", 2.0),
                new Ingredient("Sugar", 1.5)
        );

        when(mockRepository.getAllIngredients()).thenReturn(mockIngredients);

        // Act
        interactor.getIngredients();

        // Assert
        verify(mockRepository).getAllIngredients();
        verify(mockOutputBoundary).present(argThat(outputData ->
                outputData.getIngredients().equals(mockIngredients)
        ));
    }
}

