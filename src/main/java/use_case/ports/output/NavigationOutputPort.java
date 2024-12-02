package use_case.ports.output;

/**
 * Output port for handling navigation responses.
 * This interface defines how the use case will trigger navigation changes in the outer layers.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be implemented by presenters in the interface adapters layer.
 */
public interface NavigationOutputPort {
    /**
     * Triggers navigation back to the previous view.
     */
    void navigateToPreviousView();
}