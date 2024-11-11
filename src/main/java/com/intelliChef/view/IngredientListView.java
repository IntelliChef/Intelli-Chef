import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class IngredientListView extends JFrame {

    public IngredientListView(List<Ingredient> ingredients) {
        // Set up the frame
        setTitle("Ingredients List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Title label at the top
        JLabel titleLabel = new JLabel("Ingredients List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Create a list model to hold ingredient items
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Add ingredients from the list to the list model
        for (Ingredient ingredient : ingredients) {
            String ingredientEntry = ingredient.getName() + " - " + ingredient.getQuantity();
            listModel.addElement(ingredientEntry);
        }

        // Create the JList with the list model
        JList<String> ingredientList = new JList<>(listModel);
        ingredientList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Make the list scrollable
        JScrollPane scrollPane = new JScrollPane(ingredientList);
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Example list of ingredients
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomato", 5.0));
        ingredients.add(new Ingredient("Onion", 2.0));
        ingredients.add(new Ingredient("Garlic", 3.0));
        ingredients.add(new Ingredient("Olive Oil", 0.5));

        // Create and show the Ingredient List view
        new IngredientListView(ingredients);
    }
}

