package com.intelliChef.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.intelliChef.use_case.dto.RecipeDTO;
import com.intelliChef.adapters.presentation.RecipeListPresenter;
import com.intelliChef.adapters.presentation.RecipeListView;
import java.util.Arrays;
import java.util.List;

class RecipeListPresenterTest {
@Mock private RecipeListView view;
private RecipeListPresenter presenter;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    presenter = new RecipeListPresenter(view);
}

@Test
void testPresentRecipes() {
    // Prepare test data
    List<RecipeDTO> recipeDTOs = Arrays.asList(
            new RecipeDTO("Recipe1", "url1", 500.0, 30, "recipeUrl1"),
            new RecipeDTO("Recipe2", "url2", 600.0, 45, "recipeUrl2")
    );

    // Execute
    presenter.presentRecipes(recipeDTOs);

    verify(view).displayRecipes(anyList());
    verifyNoMoreInteractions(view);
}

@Test
void testPresentError() {
    // Execute
    presenter.presentError("Test error");

    verify(view).displayError("Test error");
    verifyNoMoreInteractions(view);
}
}
