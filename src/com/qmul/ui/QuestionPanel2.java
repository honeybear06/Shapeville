package com.qmul.ui;

import com.qmul.Main;
import com.qmul.util.BackgroundPanel;
import com.qmul.util.RoundedButton;
import com.qmul.util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * A panel for displaying angle measurement questions and accepting user answers.
 * This panel implements a question interface where users can select and load specific questions
 * about angle measurements by their index. Users have three attempts for each question and
 * earn points based on correct answers.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class QuestionPanel2 extends BackgroundPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JTextField answerField;
    private final JLabel attemptLabel;
    private final JLabel scoreLabel;
    private final JProgressBar progressBar;
    public final RoundedButton submitButton;
    public final RoundedButton endButton;
    public final RoundedButton homeButton;

    private final JTextField indexField;
    private final RoundedButton loadButton;

    private String currentPicturePath;
    private String currentCorrectAnswer;

    private final int attemptsAllowed = 3;
    private int remainingAttempts = attemptsAllowed;
    private int totalScore = 0;
    private final int MAX_LOADS = 5;
    private int loadedCount = 0;

    private final String[] picturePath1 = {
            "image/task2/10.png",
            "image/task2/20.png",
            "image/task2/30.png",
            "image/task2/40.png",
            "image/task2/50.png",
            "image/task2/60.png",
            "image/task2/70.png",
            "image/task2/80.png",
            "image/task2/90.png",
            "image/task2/100.png",
            "image/task2/110.png",
            "image/task2/120.png",
            "image/task2/130.png",
            "image/task2/140.png",
            "image/task2/150.png",
            "image/task2/160.png",
            "image/task2/170.png",
            "image/task2/180.png",
            "image/task2/190.png",
            "image/task2/200.png",
            "image/task2/210.png",
            "image/task2/220.png",
            "image/task2/230.png",
            "image/task2/240.png",
            "image/task2/250.png",
            "image/task2/260.png",
            "image/task2/270.png",
            "image/task2/280.png",
            "image/task2/290.png",
            "image/task2/300.png",
            "image/task2/310.png",
            "image/task2/320.png",
            "image/task2/330.png",
            "image/task2/340.png",
            "image/task2/350.png"
    };

    private final String[] correctAnswers1 = {
            "acute",
            "acute",
            "acute",
            "acute",
            "acute",
            "acute",
            "acute",
            "acute",

            "right",

            "obtuse",
            "obtuse",
            "obtuse",
            "obtuse",
            "obtuse",
            "obtuse",
            "obtuse",
            "obtuse",

            "straight",

            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex",
            "reflex"
    };

    /**
     * Creates a new QuestionPanel2 with the specified background image.
     * Initializes the UI components for selecting and answering angle measurement questions.
     *
     * @param backgroundPath Path to the background image
     * @param x Parameter that could be used for different question sets (currently unused)
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public QuestionPanel2(String backgroundPath, int x) throws IOException {
        super(backgroundPath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(248, 196, 370, 180);
        cardPanel.setOpaque(false);
        setBackground(Color.BLACK);
        add(cardPanel);

        indexField = new JTextField();
        indexField.setBounds(655, 160, 80, 30);
        indexField.setBackground(new Color(60, 101, 85));
        indexField.setForeground(Color.BLACK);
        indexField.setHorizontalAlignment(JTextField.CENTER);
        indexField.setFont(new Font("Arial", Font.BOLD, 14));
        add(indexField);

        loadButton = new RoundedButton("LOAD",
                new Color(255, 193, 7),
                new Color(255, 160, 0),
                new Color(255, 130, 0),
                Color.BLACK);
        loadButton.setBounds(745, 160, 60, 30);
        loadButton.addActionListener(this::handleLoad);
        add(loadButton);

        answerField = new JTextField();
        answerField.setBounds(655, 240, 150, 30);
        answerField.setBackground(new Color(60, 101, 85));
        answerField.setForeground(Color.BLACK);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setFont(new Font("Arial", Font.BOLD, 14));
        add(answerField);

        attemptLabel = new JLabel("Attempts Left: 3", SwingConstants.CENTER);
        attemptLabel.setForeground(Color.white);
        attemptLabel.setFont(new Font("Arial", Font.BOLD, 14));
        attemptLabel.setBounds(555, 190, 350, 20);
        add(attemptLabel);

        scoreLabel = new JLabel("Current Score: 0", SwingConstants.CENTER);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setBounds(555, 210, 350, 20);
        add(scoreLabel);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setBounds(257, 410, 350, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(82, 43, 163));
        progressBar.setBackground(new Color(36, 44, 65));
        add(progressBar);

        submitButton = new RoundedButton("SUBMIT",
                new Color(40, 167, 69),
                new Color(33, 136, 56),
                new Color(25, 105, 44),
                Color.WHITE);
        submitButton.setBounds(655, 280, 150, 35);
        submitButton.addActionListener(this::handleSubmit);
        add(submitButton);

        endButton = new RoundedButton("END",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        endButton.setBounds(655, 320, 150, 35);
        add(endButton);

        homeButton = new RoundedButton("HOME",
                new Color(0, 123, 255),
                new Color(0, 86, 214),
                new Color(0, 53, 179),
                Color.WHITE);
        homeButton.setBounds(655, 360, 150, 35);
        homeButton.addActionListener(e -> endQuiz());
        add(homeButton);
    }

    /**
     * Handles the load button action.
     * Loads a question based on the index entered by the user.
     * Validates the index and updates the UI with the corresponding question image.
     * Limits the total number of questions that can be loaded to MAX_LOADS.
     *
     * @param e The action event from the load button
     */
    private void handleLoad(ActionEvent e) {
        if (loadedCount >= MAX_LOADS) {
            JOptionPane.showMessageDialog(this, "You have already attempted 5 questions!");
            return;
        }

        String input = indexField.getText().trim();
        try {
            int index = Integer.parseInt(input) / 10;
            if (index >= 1 && index <= picturePath1.length) {
                currentPicturePath = picturePath1[index - 1];
                currentCorrectAnswer = correctAnswers1[index - 1];
                remainingAttempts = attemptsAllowed;
                attemptLabel.setText("Attempts Left: " + remainingAttempts);
                answerField.setText("");
                submitButton.setEnabled(true);

                cardPanel.removeAll();
                java.net.URL imgUrl = getClass().getClassLoader().getResource(currentPicturePath);
                if (imgUrl != null) {
                    ImageIcon icon = new ImageIcon(imgUrl);
                    Image scaled = icon.getImage().getScaledInstance(370, 180, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(scaled));
                    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    RoundedPanel panel = new RoundedPanel(30);
                    panel.setLayout(new BorderLayout());
                    panel.setOpaque(false);
                    panel.add(imageLabel, BorderLayout.CENTER);

                    cardPanel.add(panel, "Card" + loadedCount);
                    cardLayout.show(cardPanel, "Card" + loadedCount);
                    cardPanel.revalidate();
                    cardPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "havenot find picture" + currentPicturePath);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid index range (must be between 1 and " + picturePath1.length + "0）");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric index!");
        }
    }

    /**
     * Handles the submit button action.
     * Validates the user's answer and updates the score based on remaining attempts.
     * Manages the attempt counter and provides appropriate feedback to the user.
     *
     * @param e The action event from the submit button
     */
    private void handleSubmit(ActionEvent e) {
        if (remainingAttempts <= 0) {
            JOptionPane.showMessageDialog(this, "This question has been completed！");
            return;
        }

        String userAnswer = answerField.getText().trim();

        if (userAnswer.equalsIgnoreCase(currentCorrectAnswer)) {
            totalScore += remainingAttempts;  // Points are awarded for the number of attempts remaining
            JOptionPane.showMessageDialog(this, "Well done, get " + remainingAttempts + " points！");
            Main.totalScore1 += remainingAttempts;
            endCurrentQuestion();
        } else {
            remainingAttempts--;
            if (remainingAttempts > 0) {
                attemptLabel.setText("Attempts Left: " + remainingAttempts);
                JOptionPane.showMessageDialog(this, "Incorrect answer, please try again！");
            } else {
                JOptionPane.showMessageDialog(this, "The three chances have been used up, correct answer is: " + currentCorrectAnswer);
                endCurrentQuestion();
            }
        }
    }

    /**
     * Finalizes the current question after it has been answered or attempts exhausted.
     * Updates the progress bar, score display, and enables/disables relevant buttons.
     * Checks if the quiz is complete (reached MAX_LOADS questions).
     */
    private void endCurrentQuestion() {
        loadedCount++;
        remainingAttempts = 0;
        submitButton.setEnabled(false);
        attemptLabel.setText("Attempts Left: 0");
        scoreLabel.setText("Current Score: " + totalScore);
        progressBar.setValue(loadedCount * 20); // Plus 20 per question

        if (loadedCount >= MAX_LOADS) {
            loadButton.setEnabled(false);
            submitButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Quiz completed! Total score: " + totalScore);
            endQuiz();
        }
    }
    /**
     * Ends the current quiz session.
     * Displays the final score and completion message.
     */
    public void endQuiz() {
        JOptionPane.showMessageDialog(this, "You have achieved " + totalScore + " points in this session. Goodbye!");
    }
}
