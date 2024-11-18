package com.intelliChef.view;

import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;
import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IngredientListView extends JFrame implements ActionListener {
    private final GetIngredientListPresenter presenter;

    public IngredientListView(GetIngredientListPresenter presenter) {
        this.presenter = presenter;

        // Set up the frame
        setTitle("Ingredient List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label at the top
        JLabel titleLabel = new JLabel("Ingredients List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Display the ingredients
        // displayIngredients();
        displayIngredientsAsTable();

        // Add "Confirm" button at the bottom
        addConfirmButton();
    }

    private void displayIngredients() {
        // Get the View Model from the Presenter
        IngredientListViewModel viewModel = presenter.getViewModel();

        // Create a panel to hold ingredient checkboxes in a scrollable pane
        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));

        // Add a checkbox for each ingredient
        List<String> ingredients = viewModel.getIngredientsDisplayList();
        for (String ingredient : ingredients) {
            JCheckBox checkBox = new JCheckBox(ingredient, true);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            ingredientPanel.add(checkBox);
        }

        // Make the panel scrollable
        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        setVisible(true);
    }

    private void displayIngredientsAsTable() {
        // Get the View Model from the Presenter
        IngredientListViewModel viewModel = presenter.getViewModel();

        // Table column headers
        String[] columnNames = {"Select", "Name", "Quantity"};

        // Extract ingredient data and include a checkbox for each row
        Object[][] tableData = viewModel.getIngredientsDisplayList().stream()
                .map(ingredient -> ingredient.split(", "))
                .map(data -> new Object[]{
                        Boolean.TRUE, // Default selection is true (checked)
                        data[0].replace("Name: ", ""),
                        data[1].replace("Quantity: ", "")
                })
                .toArray(Object[][]::new);

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // First column (Select) is Boolean, others are String
                return columnIndex == 0 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing the checkbox column
                return column == 0;
            }
        };

        // Create a JTable
        JTable ingredientTable = new JTable(tableModel);

        // Set column widths for better spacing
        ingredientTable.getColumnModel().getColumn(0).setPreferredWidth(50); // Checkbox column
        ingredientTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name column
        ingredientTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Quantity column

        // Wrap the table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(ingredientTable);

        // Add the table to the center of the frame
        add(scrollPane, BorderLayout.CENTER);
    }


    private void addConfirmButton() {
        // Create a panel with a FlowLayout aligned to the right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));

        confirmButton.setActionCommand("Confirm"); // Assign ActionCommand for identification
        confirmButton.addActionListener(this);    // Attach ActionListener
        // Add the button to the panel
        buttonPanel.add(confirmButton);

        // Add the panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if ("Confirm".equals(command)) {
            // invokes the next page, possibly call ConfirmIngredientListController
        }
    }
}



