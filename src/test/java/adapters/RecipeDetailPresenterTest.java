package adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import use_case.dto.RecipeDTO;
import interface_adapters.presentation.RecipeDetailPresenter;
import interface_adapters.presentation.RecipeDetailView;
import interface_adapters.presentation.RecipeViewModel;

class RecipeDetailPresenterTest {
    @Mock private RecipeDetailView view;
    private RecipeDetailPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new RecipeDetailPresenter(view);
    }

    @Test
    void testPresentRecipeDetails() {
        RecipeDTO recipeDTO = new RecipeDTO("Test Recipe", "imageUrl", 500.0, 30, "recipeUrl");

        // Execute
        presenter.presentRecipeDetails(recipeDTO);

        verify(view).displayRecipeDetail(any(RecipeViewModel.class));
        verifyNoMoreInteractions(view);
    }
}
