package com.intelliChef.view;

import com.intelliChef.adapters.recipeUpload.RecipeUploadController;
import com.intelliChef.adapters.recipeUpload.RecipeUploadPresenter;
import static com.intelliChef.utils.UseCustomFont.loadCustomFont;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecipeUploadView extends JFrame {
    private final JLabel scanningLabel;
    private RecipeUploadController recipeUploadController;
    private RecipeUploadPresenter recipeUploadPresenter;
    private final JProgressBar progressBar;


    public void setRecipeUploadController(RecipeUploadController recipeUploadController) {
        this.recipeUploadController = recipeUploadController;
    }

    public void setRecipeUploadPresenter(RecipeUploadPresenter recipeUploadPresenter) {
        this.recipeUploadPresenter = recipeUploadPresenter;
    }

    public RecipeUploadView() {
        super("IntelliChef");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("IntelliChef", SwingConstants.CENTER);
        titleLabel.setFont(loadCustomFont("fonts/DynaPuff-Medium.ttf", 48f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        scanningLabel = new JLabel("Scanning image...", SwingConstants.CENTER);
        scanningLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        scanningLabel.setForeground(Color.BLACK);
        scanningLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scanningLabel.setVisible(false);
        southPanel.add(scanningLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        southPanel.add(progressBar, BorderLayout.CENTER);

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

        JLabel poweredByLabel = new JLabel("Powered by Gemini", SwingConstants.CENTER);
        poweredByLabel.setFont(loadCustomFont("fonts/DynaPuff-Regular.ttf", 16f));
        poweredByLabel.setForeground(new Color(33, 168, 120));
        poweredByLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(poweredByLabel, BorderLayout.SOUTH);
        southPanel.add(poweredByLabel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

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
        progressBar.setVisible(isVisible);
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
