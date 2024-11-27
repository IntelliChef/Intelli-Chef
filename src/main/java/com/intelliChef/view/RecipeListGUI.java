package com.intelliChef.view;

import com.intelliChef.entities.Recipe;
import com.intelliChef.interfaces.JSONRecipeGateway;
import com.intelliChef.interfaces.RecipeGateway;
import com.intelliChef.use_case.RecipeSelectionInteractor;
import com.intelliChef.presenters.RecipePresenter;
import com.intelliChef.presenters.RecipeListView;
import com.intelliChef.presenters.RecipeView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

/**
 * A graphical user interface (GUI) for displaying a list of recipes.
 * Allows users to select a recipe to view its details or return to the list view.
 */
public class RecipeListGUI extends JFrame implements RecipeListView, RecipeView {

    private final RecipeGateway recipeGateway = new JSONRecipeGateway();
    private RecipeSelectionInteractor interactor;

    /**
     * Constructs a new RecipeListGUI, initializes the recipe list view,
     * and loads recipes from a data source.
     */
    public RecipeListGUI() {
        RecipePresenter presenter = new RecipePresenter(this);
        this.interactor = new RecipeSelectionInteractor(presenter);

        setTitle("Recipe List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        List<Recipe> recipes = recipeGateway.loadRecipes();
        for (Recipe recipe : recipes) {
            contentPanel.add(createRecipePanel(recipe));
        }

        setVisible(true);
    }

    public void setInteractor(RecipeSelectionInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Creates a panel displaying the details of a single recipe.
     * Includes the recipe's image, name, calorie count, cooking time, and a clickable link.
     *
     * @param recipe the {@link Recipe} to display
     * @return a {@link JPanel} containing the recipe's information
     */
    private JPanel createRecipePanel(Recipe recipe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setMaximumSize(new Dimension(750, 200)); // Limit box size
        panel.setBackground(Color.WHITE);

        // Left: Recipe Image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        try {
            BufferedImage image = ImageIO.read(new URL(recipe.getImageUrl()));
            JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("Image not available", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(150, 150));
            placeholder.setBackground(Color.LIGHT_GRAY);
            placeholder.setOpaque(true);
            imagePanel.add(placeholder, BorderLayout.CENTER);
        }
        panel.add(imagePanel, BorderLayout.WEST);

        // Center: Recipe Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        detailsPanel.add(nameLabel);

        JLabel calorieLabel = new JLabel("Calories: " + recipe.getCalories());
        calorieLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsPanel.add(calorieLabel);

        if (recipe.getCookingTime() > 0) {
            JLabel timeLabel = new JLabel("Cooking Time: " + recipe.getCookingTime() + " mins");
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            detailsPanel.add(timeLabel);
        }

        JLabel urlLabel = new JLabel("<html><a href='" + recipe.getUrl() + "'>Recipe Link</a></html>");
        urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLabel.setForeground(Color.BLUE);
        urlLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URL(recipe.getUrl()).toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        detailsPanel.add(urlLabel);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Add click listener for navigation to RecipeDetailGUI
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                interactor.selectRecipe(recipe);
            }
        });

        return panel;
    }

    /**
     * Displays the details of a selected recipe in a new window.
     *
     * @param recipe the {@link Recipe} to display
     */
    @Override
    public void displayRecipe(Recipe recipe) {
        SwingUtilities.invokeLater(() -> {
            new RecipeDetailGUI(recipe, this);
            this.setVisible(false); // Hide RecipeListGUI when opening RecipeDetailGUI
        });
    }

    /**
     * Displays the recipe list view, making the current window visible.
     */
    @Override
    public void showRecipeList() {
        this.setVisible(true); // Show RecipeListGUI when back button is pressed
    }

    /**
     * Main method to launch the RecipeListGUI application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecipeListGUI::new);
    }
}
