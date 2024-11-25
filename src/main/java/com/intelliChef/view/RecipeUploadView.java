package com.intelliChef.view;

import com.intelliChef.adapters.recipeUpload.RecipeUploadController;
import com.intelliChef.adapters.recipeUpload.RecipeUploadPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecipeUploadView extends JFrame {
    private final JLabel scanningLabel;
    private RecipeUploadController recipeUploadController;
    private RecipeUploadPresenter recipeUploadPresenter;

    public void setRecipeUploadController(RecipeUploadController recipeUploadController) {
        this.recipeUploadController = recipeUploadController;
    }

    public void setRecipeUploadPresenter(RecipeUploadPresenter recipeUploadPresenter) {
        this.recipeUploadPresenter = recipeUploadPresenter;
    }

    public RecipeUploadView() {
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
            recipeUploadController.ingredientButtonClick(recipeUploadPresenter);
        });

        JButton uploadButton = new JButton("Upload Image");
        styleButton(uploadButton);
        uploadButton.addActionListener(e -> handleUploadImageButton());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        buttonPanel.add(uploadButton);
        buttonPanel.add(ingredientsButton);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleUploadImageButton() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(RecipeUploadView.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            recipeUploadController.uploadImageClick(selectedFile.getAbsolutePath(), recipeUploadPresenter);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(40, 118, 167));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFocusPainted(false);
    }

    public void updateScanningLabel(boolean isVisible) {
        scanningLabel.setVisible(isVisible);
    }

    public void showWarningMessage(String warningMessage) {
        JOptionPane.showMessageDialog(
                this,
                warningMessage,
                "No Ingredients Found",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(
                this,
                errorMessage,
                "Internal Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
