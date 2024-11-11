package com.intelliChef.interactors;

import javax.swing.*;
import java.awt.*;

public class DietPreferenceForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Diet Preference");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());


        JLabel title = new JLabel("Diet Preference:", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);


        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        JCheckBox option1CheckBox = new JCheckBox("Keto");
        JCheckBox option2CheckBox = new JCheckBox("Gluten-free");
        JCheckBox option3CheckBox = new JCheckBox("Protein-Rich");
        JCheckBox option4CheckBox = new JCheckBox("Fiber-Rich");
        JCheckBox option5CheckBox = new JCheckBox("Alcohol-free");
        JCheckBox option6CheckBox = new JCheckBox("Vegan");

        option1CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        option2CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        option3CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        option4CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        option5CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        option6CheckBox.setFont(new Font("Arial", Font.PLAIN, 18));

        checkBoxPanel.add(option1CheckBox);
        checkBoxPanel.add(option2CheckBox);
        checkBoxPanel.add(option3CheckBox);
        checkBoxPanel.add(option4CheckBox);
        checkBoxPanel.add(option5CheckBox);
        checkBoxPanel.add(option6CheckBox);

        frame.add(checkBoxPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        JButton confirmButton = new JButton("Confirm");

        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}
