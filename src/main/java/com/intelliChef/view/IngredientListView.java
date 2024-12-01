package com.intelliChef.view;

import com.intelliChef.adapters.ingredient_list.AddIngredientController;
import com.intelliChef.adapters.ingredient_list.ConfirmIngredientListController;
import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;
import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientListView extends JFrame implements ActionListener {
    private IngredientListViewModel viewModel;
    private final AddIngredientController addIngredientController;
    private final ConfirmIngredientListController confirmController;

    private JTable ingredientTable;

    public IngredientListView(IngredientListViewModel viewModel,
                              AddIngredientController addIngredientController,
                              ConfirmIngredientListController confirmController) {
        this.viewModel = viewModel;
        this.addIngredientController = addIngredientController;
        this.confirmController = confirmController;

        // Set up the frame
        setTitle("Ingredient List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // add a table to the view
        ingredientTable = displayIngredientsAsTable();
        JScrollPane scrollPane = new JScrollPane(ingredientTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel to hold both the input fields and the confirm button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Add "Add ingredient" button and name, quantity field to the view
        addAddIngredientButtonAndField(bottomPanel);

        // Add "Confirm" button to the view
        addConfirmButton(bottomPanel);

        // Add the combined panel to the bottom of the frame
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JTable displayIngredientsAsTable() {
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

        return ingredientTable;
    }

    private void addConfirmButton(JPanel panel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));

        confirmButton.addActionListener(e -> {
            // Confirms the ingredient list
            int column = 0;
            int rowCount = ingredientTable.getRowCount();
            boolean[] selected = new boolean[rowCount];

            for (int i = 0; i < rowCount; i++) {
                Object value = ingredientTable.getValueAt(i, column);
                selected[i] = (value != null && value instanceof Boolean) ? (Boolean) value : false;
            }
            confirmController.execute(selected); // Saves data, transit to the next view
        });

        buttonPanel.add(confirmButton);
        panel.add(buttonPanel);
    }

    private void addAddIngredientButtonAndField(JPanel panel) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2)); // Create a grid for the fields

        // Name field
        JLabel nameLabel = new JLabel("Enter Ingredient Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // Quantity field
        JLabel quantityLabel = new JLabel("Enter quantity:");
        JTextField quantityField = new JTextField();
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        // Add Button
        JButton addButton = new JButton("Add Ingredient");

        addButton.addActionListener(e -> {
            String ingredientName = nameField.getText();
            String ingredientQuantity = quantityField.getText();
            if (!ingredientName.isEmpty() && !ingredientQuantity.isEmpty()
                    && ingredientQuantity.matches("\\d+")) {
                // Call addIngredientController
                addIngredientController.execute(ingredientName, ingredientQuantity);

                // Get the current table model
                DefaultTableModel model = (DefaultTableModel) ingredientTable.getModel();

                // Add the new row to the table
                model.addRow(new Object[]{
                        Boolean.TRUE, // Default selection (checked)
                        ingredientName, // Ingredient Name
                        ingredientQuantity // Ingredient Quantity
                });

                // Clear the fields
                nameField.setText("");
                quantityField.setText("");

            } else {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid name and numeric quantity!");
            }
        });

        inputPanel.add(addButton);

        panel.add(inputPanel);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }
}




