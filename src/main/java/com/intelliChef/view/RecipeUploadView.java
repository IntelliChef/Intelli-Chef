package com.intelliChef.view;

import com.intelliChef.adapters.UploadImageController;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeUploadView extends JFrame {
    private final List<Ingredient> ingredientList = new ArrayList<>();
    private JLabel scanningLabel;


    public RecipeUploadView(UploadImageController uploadImageController) {
        super("IntelliChef");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("IntelliChef", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        scanningLabel = new JLabel("Image is being scanned. Please wait...", SwingConstants.CENTER);
        scanningLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        scanningLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        scanningLabel.setVisible(false);
        add(scanningLabel, BorderLayout.SOUTH);

        JButton ingredientsButton = new JButton("Enter Ingredients");
        styleButton(ingredientsButton);
        ingredientsButton.addActionListener(e -> {
                Main.showIngredientListView(ingredientList);
                dispose();
        });

        JButton uploadButton = new JButton("Upload Image");
        styleButton(uploadButton);
        uploadButton.addActionListener(e -> {handleUploadImageButton(uploadImageController);});

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        buttonPanel.add(uploadButton);
        buttonPanel.add(ingredientsButton);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleUploadImageButton(UploadImageController uploadImageController) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(RecipeUploadView.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();

            scanningLabel.setVisible(true);
            new SwingWorker<List<Ingredient>, Void>() {
                @Override
                protected List<Ingredient> doInBackground() throws Exception {
                    return uploadImageController.execute(imagePath);
                }

                @Override
                protected void done() {
                    scanningLabel.setVisible(false);
                    try {
                        List<Ingredient> returnedList = get();
                        if (returnedList.isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    RecipeUploadView.this,
                                    "No ingredients found. Please upload a valid image.",
                                    "Ingredient Error",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            ingredientList.addAll(returnedList);
                            Main.showIngredientsDetectedView(ingredientList);
                            dispose();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                RecipeUploadView.this,
                                "There was an error processing the image. Please try again.",
                                "Undefined Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }.execute();
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(40, 118, 167));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFocusPainted(false);
    }
}
