package com.intelliChef.app;

import com.intelliChef.adapters.UploadImageController;
import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_cases.AnalyzeImageUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeAppUI extends JFrame {
    private final JFrame frame;

    public RecipeAppUI(UploadImageController uploadImageController) {
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

                    System.out.println(imagePath);

                    List<Ingredient> ingredientList = new ArrayList<>();
                    try {
                        ingredientList = uploadImageController.execute(imagePath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        frame.getContentPane().add(uploadButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeminiAIClient vertexAIClient = new GeminiAIClient(
                    "API-KEY", // TODO: Change this to a valid API key
                    "us-central1",
                    "gemini-1.5-flash-001");
            AnalyzeImageUseCase analyzeImageUseCase = new AnalyzeImageUseCase(vertexAIClient);
            UploadImageController uploadImageController = new UploadImageController(analyzeImageUseCase);

            RecipeAppUI recipeAppUI = new RecipeAppUI(uploadImageController);
            recipeAppUI.setVisible(true);
        });
    }
}

