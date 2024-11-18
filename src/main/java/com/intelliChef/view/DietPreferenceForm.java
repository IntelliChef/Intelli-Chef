package com.intelliChef.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class DietPreferenceForm {
    public static void main(String[] args) {
        List<String> dietPreference = new ArrayList<>();

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

        option1CheckBox.addActionListener(e -> {
            if (option1CheckBox.isSelected()) {
                dietPreference.add("Keto");
            } else {
                if (dietPreference.contains("Keto")) {
                    dietPreference.remove("Keto");
                }
            }
        });


        option2CheckBox.addActionListener(e -> {
            if (option2CheckBox.isSelected()) {
                dietPreference.add("Gluten-free");
            } else {
                if (dietPreference.contains("Gluten-free")) {
                    dietPreference.remove("Gluten-free");
                }
            }
        });

        option3CheckBox.addActionListener(e -> {
            if (option3CheckBox.isSelected()) {
                dietPreference.add("Protein-Rich");
            } else {
                if (dietPreference.contains("Protein-Rich")) {
                    dietPreference.remove("Protein-Rich");
                }
            }
        });

        option4CheckBox.addActionListener(e -> {
            if (option4CheckBox.isSelected()) {
                dietPreference.add("Fiber-Rich");
            } else {
                if (dietPreference.contains("Fiber-Rich")) {
                    dietPreference.remove("Fiber-Rich");
                }
            }
        });

        option5CheckBox.addActionListener(e -> {
            if (option5CheckBox.isSelected()) {
                dietPreference.add("Alcohol-free");
            } else {
                if (dietPreference.contains("Alcohol-free")) {
                    dietPreference.remove("Alcohol-free");
                }
            }
        });

        option6CheckBox.addActionListener(e -> {
            if (option6CheckBox.isSelected()) {
                dietPreference.add("Vegan");
            } else {
                if (dietPreference.contains("Vegan")) {
                    dietPreference.remove("Vegan");
                }
            }
        });


        frame.setVisible(true);
    }
}
