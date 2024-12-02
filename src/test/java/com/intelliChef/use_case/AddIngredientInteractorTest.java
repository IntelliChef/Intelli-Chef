package com.intelliChef.use_case;

import entities.Ingredient;
import use_case.IngredientRepository;
import use_case.add_ingredient.AddIngredientInputData;
import use_case.add_ingredient.AddIngredientInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddIngredientInteractorTest {
    private IngredientRepository repository;
    private AddIngredientInteractor interactor;
    private AddIngredientInputData inputData;

    @BeforeEach
    void setUp() {
        // Mocking the IngredientRepository
        repository = Mockito.mock(IngredientRepository.class);
        interactor = new AddIngredientInteractor(repository);

        // Create a sample input data
        inputData = Mockito.mock(AddIngredientInputData.class);
        Ingredient ingredient = new Ingredient("Tomato", 5);
        when(inputData.getIngredient()).thenReturn(ingredient);
    }

    @Test
    void testExecute() {
        // Arrange: Prepare the next ID mock
        when(repository.getNextId()).thenReturn(1);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Verify the ingredient ID was set correctly
        Ingredient ingredient = inputData.getIngredient();
        verify(repository).addIngredient(ingredient);
        verify(ingredient).setId(1);
    }
}