package use_case.view_interactors;

import entities.Recipe;
import use_case.dto.RecipeDTO;
import use_case.ports.input.LoadRecipesUseCase;
import use_case.ports.output.LoadRecipesOutputPort;
import use_case.ports.output.RecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the load recipes use case.
 * This class coordinates the loading of recipes from the repository
 * and presents them through the output port.
 */
public class LoadRecipesInteractor implements LoadRecipesUseCase {
    private final RecipeRepository recipeRepository;
    private final LoadRecipesOutputPort outputPort;

    /**
     * Constructs a new LoadRecipesInteractor.
     *
     * @param recipeRepository the repository to fetch recipes from
     * @param outputPort the port through which to present the results
     */
    public LoadRecipesInteractor(RecipeRepository recipeRepository, LoadRecipesOutputPort outputPort) {
        this.recipeRepository = recipeRepository;
        this.outputPort = outputPort;
    }

    /**
     * Loads recipes from the repository and presents them through the output port.
     * Handles any errors that occur during the loading process.
     */
    @Override
    public void loadRecipes() {
        try {
            List<Recipe> recipes = recipeRepository.fetchRecipes();
            List<RecipeDTO> recipeDTOs = recipes.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            outputPort.presentRecipes(recipeDTOs);
        } catch (Exception e) {
            outputPort.presentError("Failed to load recipes: " + e.getMessage());
        }
    }

    /**
     * Converts a Recipe entity to a RecipeDTO.
     *
     * @param recipe the Recipe entity to convert
     * @return the corresponding RecipeDTO
     */
    private RecipeDTO toDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getName(),
                recipe.getImageUrl(),
                recipe.getCalories(),
                recipe.getCookingTime(),
                recipe.getUrl()
        );
    }
}