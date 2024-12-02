package use_case.ports.input;

/**
 * Input port for loading recipes.
 * This interface defines the use case of loading recipes in the application.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be called by the outer layers (adapters/UI) and implemented by an interactor.
 */
public interface LoadRecipesUseCase {
    /**
     * Loads all available recipes.
     * The actual implementation will use a repository to fetch the recipes
     * and present them through the output port.
     */
    void loadRecipes();
}