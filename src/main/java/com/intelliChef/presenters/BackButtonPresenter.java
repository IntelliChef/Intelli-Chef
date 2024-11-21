package com.intelliChef.presenters;

import com.intelliChef.use_case.BackButtonInteractor.OutputBoundary;
import javax.swing.*;

public class BackButtonPresenter implements OutputBoundary {
    private final RecipeListView view;
    private JFrame detailFrame;

    public BackButtonPresenter(RecipeListView view) {
        this.view = view;
    }

    public void setDetailFrame(JFrame detailFrame) {
        this.detailFrame = detailFrame;
    }

    @Override
    public void navigateBackToRecipeList() {
        view.showRecipeList();
        if (detailFrame != null) {
            detailFrame.dispose();
        }
    }
}
