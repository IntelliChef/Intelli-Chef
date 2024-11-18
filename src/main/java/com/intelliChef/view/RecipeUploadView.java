package com.intelliChef.view;

import com.intelliChef.adapters.UploadImageController;
import com.intelliChef.entities.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeUploadView extends JFrame {
    private final List<Ingredient> ingredientList = new ArrayList<>();

    public RecipeUploadView(UploadImageController uploadImageController) {
        super("Image Uploader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JButton ingredientsButton = new JButton("Enter Ingredients");

        JButton uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(RecipeUploadView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();

                    List<Ingredient> returnedList;
                    try {
                        returnedList = uploadImageController.execute(imagePath);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                                RecipeUploadView.this,
                                "Error processing the image.");
                        return;
                    }

                    if (returnedList.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                RecipeUploadView.this,
                                "No ingredients found. Please enter a valid image.");
                    } else {
                        ingredientList.addAll(returnedList);
                        IngredientListView ingredientListView = new IngredientListView(ingredientList);
                        ingredientListView.setVisible(true);
                        dispose();
                    }
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        buttonPanel.add(ingredientsButton);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}

