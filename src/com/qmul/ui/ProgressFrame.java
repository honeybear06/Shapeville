package com.qmul.ui;

import javax.swing.*;
import java.awt.*;
import com.qmul.Main;

public class ProgressFrame extends JFrame {

    public ProgressFrame() {
        setTitle("Task Progress Display");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false); // fixed size

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // absolute layout
        mainPanel.setBounds(0, 0, 400, 400);

        // Row 1: Total score
        JLabel totalScoreLabel = new JLabel("Total Score: " + Main.totalScore1);
        totalScoreLabel.setBounds(20, 20, 200, 25);
        mainPanel.add(totalScoreLabel);

        // Add 6 task progress bars
        mainPanel.add(createTaskPanel("Task 1", Main.finishedQuizCount1, 2, 60));
        mainPanel.add(createTaskPanel("Task 2", Main.finishedQuizCount2, 1, 100));
        mainPanel.add(createTaskPanel("Task 3", Main.finishedQuizCount3, 4, 140));
        mainPanel.add(createTaskPanel("Task 4", Main.finishedQuizCount4, 4, 180));
        mainPanel.add(createTaskPanel("Bonus 1", Main.finishedQuizCount5, 6, 220));
        mainPanel.add(createTaskPanel("Bonus 2", Main.finishedQuizCount6, 8, 260));

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createTaskPanel(String taskName, int finished, int total, int yPosition) {
        JPanel panel = new JPanel(null);
        panel.setBounds(20, yPosition, 350, 30);

        JLabel label = new JLabel(taskName + " (" + finished + "/" + total + ")");
        label.setBounds(0, 5, 100, 20);
        panel.add(label);

        JProgressBar progressBar = new JProgressBar(0, total);
        progressBar.setValue(finished);
        progressBar.setStringPainted(true);
        progressBar.setBounds(110, 5, 220, 20);
        panel.add(progressBar);

        return panel;
    }
}
