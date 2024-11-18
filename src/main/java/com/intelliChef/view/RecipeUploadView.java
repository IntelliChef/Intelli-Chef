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
    private final JFrame frame;
    private final List<Ingredient> ingredientList = new ArrayList<>();

    public RecipeUploadView(UploadImageController uploadImageController) {
        frame = new JFrame("Image Uploader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JButton uploadButton = new JButton("Upload Image");

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();

                    List<Ingredient> returnedList;
                    try {
                        returnedList = uploadImageController.execute(imagePath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (returnedList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No ingredients found. Please enter a valid image.");
                    } else {
                        ingredientList.addAll(returnedList);

                        // Go to the second page of app
                        IngredientListView ingredientListView = new IngredientListView(ingredientList);
                        ingredientListView.setVisible(true);

                        frame.dispose();
                    }
                }
            }
        });

        frame.getContentPane().add(uploadButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

