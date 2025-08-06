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
 * A panel for displaying timed quiz questions with various mathematical content.
 * This panel implements a question interface with countdown timer functionality,
 * allowing users to answer questions within a time limit. Users have three attempts
 * for each question and earn points based on correct answers.
 * Different question modes (3, 4, 5, 6) provide different types of mathematical challenges.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class QuestionPanel3 extends BackgroundPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JTextField answerField;
    private final JLabel attemptLabel;
    private final JLabel scoreLabel;
    public final RoundedButton submitButton;
    public final RoundedButton endButton;
    public final RoundedButton homeButton;

    private String currentPicturePath;
    private String currentCorrectAnswer;

    private final int attemptsAllowed = 3;
    private int remainingAttempts = attemptsAllowed;
    private int totalScore = 0;
    private final int CARD_COUNT = 1;

    private Timer countdownTimer;
    private int timeLeft = 180; // Countdown seconds, customizable
    private final JLabel timerLabel;
    private int bonus = 0;
    private String answerPath;


    /**
     * Creates a new QuestionPanel3 with the specified background image and question type.
     * Initializes the UI components and loads a question based on the specified mode and index.
     *
     * @param backgroundPath Path to the background image
     * @param x Question index or selection within the chosen mode
     * @param mode Question type/set identifier:
     *        3 - Task 3 basic math questions (subtypes 1-4)
     *        4 - Task 4 circle area/circumference questions (subtypes 1-4)
     *        5 - Bonus task 1 questions with extended time
     *        6 - Bonus task 2 questions with extended time
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public QuestionPanel3(String backgroundPath, int x, int mode) throws IOException {
        super(backgroundPath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        // cardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(248, 196, 370, 180); // 居中显示图片
        cardPanel.setOpaque(false);
        setBackground(Color.BLACK);
        add(cardPanel);

        // Setting up question images and answers
        String[] picturePathTask3a = {
                "image/task3/a/1a.png",
                "image/task3/a/2a.png",
                "image/task3/a/3a.png",
                "image/task3/a/4a.png",
                "image/task3/a/5a.png",
                "image/task3/a/6a.png",
                "image/task3/a/7a.png",
                "image/task3/a/8a.png",
                "image/task3/a/9a.png",
                "image/task3/a/10a.png"
        };
        String[] correctAnswersTask3a = {
                "4", "2", "6", "8", "12", "20",
                "21", "35", "32", "54"
        };

        String[] picturePathTask3b = {
                "image/task3/b/1b.png",
                "image/task3/b/2b.png",
                "image/task3/b/3b.png",
                "image/task3/b/4b.png",
                "image/task3/b/5b.png",
                "image/task3/b/6b.png",
                "image/task3/b/7b.png",
                "image/task3/b/8b.png",
                "image/task3/b/9b.png",
                "image/task3/b/10b.png"
        };
        String[] correctAnswersTask3b = {
                "4", "2", "6", "8", "12", "20",
                "21", "35", "32", "54"
        };

        String[] picturePathTask3c = {
                "image/task3/c/11a.png",
                "image/task3/c/12a.png",
                "image/task3/c/13a.png",
                "image/task3/c/14a.png",
                "image/task3/c/15a.png",
                "image/task3/c/16a.png",
                "image/task3/c/17a.png",
                "image/task3/c/18a.png",
                "image/task3/c/19a.png",
                "image/task3/c/20a.png"
        };
        String[] correctAnswersTask3c = {
                "7.5", "14", "24", "5", "36", "6",
                "15", "14", "18", "4.5"
        };

        String[] picturePathTask3d = {
                "image/task3/d/11b.png",
                "image/task3/d/12b.png",
                "image/task3/d/13b.png",
                "image/task3/d/14b.png",
                "image/task3/d/15b.png",
                "image/task3/d/16b.png",
                "image/task3/d/17b.png",
                "image/task3/d/18b.png",
                "image/task3/d/19b.png",
                "image/task3/d/20b.png"
        };
        String[] correctAnswersTask3d = {
                "32.5", "13.5", "12", "19.5", "32.5", "8",
                "18", "12", "16", "4"
        };

        String[] picturePathTask41 = {
                "image/task4/Area/0.785d.png",
                "image/task4/Area/3.14d.png",
                "image/task4/Area/7.065d.png",
                "image/task4/Area/12.25d.png",
                "image/task4/Area/19.625d.png",
                "image/task4/Area/28.26d.png",
                "image/task4/Area/38.465d.png",
                "image/task4/Area/50.24d.png",
                "image/task4/Area/63.585d.png",
                "image/task4/Area/78.50d.png",
                "image/task4/Area/94.985d.png",
                "image/task4/Area/113.04d.png",
                "image/task4/Area/132.665d.png",
                "image/task4/Area/153.86d.png",
                "image/task4/Area/176.625d.png",
                "image/task4/Area/200.96d.png",
                "image/task4/Area/226.865d.png",
                "image/task4/Area/254.34d.png",
                "image/task4/Area/283.385d.png",
                "image/task4/Area/314.00d.png",

                "image/task4/Area/3.14r.png",
                "image/task4/Area/12.56r.png",
                "image/task4/Area/28.26r.png",
                "image/task4/Area/50.26r.png",
                "image/task4/Area/78.50r.png",
                "image/task4/Area/113.04r.png",
                "image/task4/Area/153.86r.png",
                "image/task4/Area/200.96r.png",
                "image/task4/Area/254.34r.png",
                "image/task4/Area/314.00r.png",
                "image/task4/Area/379.94r.png",
                "image/task4/Area/452.16r.png",
                "image/task4/Area/530.66r.png",
                "image/task4/Area/615.44r.png",
                "image/task4/Area/706.50r.png",
                "image/task4/Area/907.46r.png",
                "image/task4/Area/1017.36r.png",
                "image/task4/Area/1133.54r.png",
                "image/task4/Area/1256.00r.png"
        };

        String[] correctAnswersTask41 = {
                "0.785", "3.14", "7.065", "12.25", "19.625", "28.26", "38.465", "50.24", "63.585", "78.50",
                "94.985", "113.04", "132.665", "153.86", "176.625", "200.96", "226.865", "254.34", "283.385", "314.00",
                "3.14", "12.56", "28.26", "50.26", "78.50", "113.04", "153.86", "200.96", "254.34", "314.00",
                "379.94", "452.16", "530.66", "615.44", "706.50", "907.46", "1017.36", "1133.54", "1256.00"
        };

        String[] picturePathTask42 = {
                "image/task4/Circumference/3.14d.png",
                "image/task4/Circumference/6.28d.png",
                "image/task4/Circumference/9.42d.png",
                "image/task4/Circumference/12.56d.png",
                "image/task4/Circumference/15.70d.png",
                "image/task4/Circumference/18.84d.png",
                "image/task4/Circumference/21.98d.png",
                "image/task4/Circumference/25.12d.png",
                "image/task4/Circumference/28.26d.png",
                "image/task4/Circumference/31.40d.png",
                "image/task4/Circumference/34.54d.png",
                "image/task4/Circumference/37.68d.png",
                "image/task4/Circumference/40.82d.png",
                "image/task4/Circumference/43.96d.png",
                "image/task4/Circumference/47.10d.png",
                "image/task4/Circumference/50.24d.png",
                "image/task4/Circumference/53.38d.png",
                "image/task4/Circumference/56.52d.png",
                "image/task4/Circumference/59.66d.png",
                "image/task4/Circumference/62.80d.png",

                "image/task4/Circumference/6.28r.png",
                "image/task4/Circumference/12.56r.png",
                "image/task4/Circumference/18.84r.png",
                "image/task4/Circumference/25.12r.png",
                "image/task4/Circumference/31.40r.png",
                "image/task4/Circumference/37.68r.png",
                "image/task4/Circumference/43.96r.png",
                "image/task4/Circumference/50.24r.png",
                "image/task4/Circumference/56.52r.png",
                "image/task4/Circumference/62.80r.png",
                "image/task4/Circumference/69.08r.png",
                "image/task4/Circumference/75.36r.png",
                "image/task4/Circumference/81.64r.png",
                "image/task4/Circumference/87.92r.png",
                "image/task4/Circumference/94.20r.png",
                "image/task4/Circumference/100.48r.png",
                "image/task4/Circumference/106.76r.png",
                "image/task4/Circumference/113.04r.png",
                "image/task4/Circumference/119.32r.png",
                "image/task4/Circumference/125.60r.png"
        };
        String[] correctAnswersTask42 = {
                "3.14",
                "6.28",
                "9.42",
                "12.56",
                "15.70",
                "18.84",
                "21.98",
                "25.12",
                "28.26",
                "31.40",
                "34.54",
                "37.68",
                "40.82",
                "43.96",
                "47.10",
                "50.24",
                "53.38",
                "56.52",
                "59.66",
                "62.80",

                "6.28",
                "12.56",
                "18.84",
                "25.12",
                "31.40",
                "37.68",
                "43.96",
                "50.24",
                "56.52",
                "62.80",
                "69.08",
                "75.36",
                "81.64",
                "87.92",
                "94.20",
                "100.48",
                "106.76",
                "113.04",
                "119.32",
                "125.60"
        };

        String[] picturePathTask5 = {
                "image/bonus1/2.png",
                "image/bonus1/3.png",
                "image/bonus1/4.png",
                "image/bonus1/5.png",
                "image/bonus1/6.png",
                "image/bonus1/7.png"
        };
        String[] correctAnswersTask5 = {
                "310", "598", "288", "18", "3456", "174"
        };

        String[] picturePathTask6 = {
                "image/bonus2/1.png",
                "image/bonus2/2.png",
                "image/bonus2/3.png",
                "image/bonus2/4.png",
                "image/bonus2/5.png",
                "image/bonus2/6.png",
                "image/bonus2/7.png",
                "image/bonus2/8.png"
        };
        String[] correctAnswersTask6 = {
                "50.24", "367.38", "377.85", "464.37", "10.68", "150.72",
                "351.68", "490.63"
        };

        String[] answer2 = {
                "image/bonus2/answer/1.png",
                "image/bonus2/answer/2.png",
                "image/bonus2/answer/new.jpg",
                "image/bonus2/answer/4.png",
                "image/bonus2/answer/5.png",
                "image/bonus2/answer/6.png",
                "image/bonus2/answer/7.png",
                "image/bonus2/answer/8.png"
        };

        String[] answer1 = {
                "image/bonus1/answer/1.png",
                "image/bonus1/answer/2.png",
                "image/bonus1/answer/3.png",
                "image/bonus1/answer/4.png",
                "image/bonus1/answer/5.png",
                "image/bonus1/answer/6.png"
        };


        if (mode == 3) {
            int random = (int) (Math.random() * 10);
            switch(x){
                case 1:
                    currentPicturePath = picturePathTask3a[random];
                    currentCorrectAnswer = correctAnswersTask3a[random];
                    break;
                case 2:
                    currentPicturePath = picturePathTask3b[random];
                    currentCorrectAnswer = correctAnswersTask3b[random];
                    break;
                case 3:
                    currentPicturePath = picturePathTask3c[random];
                    currentCorrectAnswer = correctAnswersTask3c[random];
                    break;
                case 4:
                    currentPicturePath = picturePathTask3d[random];
                    currentCorrectAnswer = correctAnswersTask3d[random];
                    break;
            }
        } else if (mode == 4) {

            int random = (int) (Math.random() * 20);
            switch(x){
                case 1:
                    currentPicturePath = picturePathTask41[random];
                    currentCorrectAnswer = correctAnswersTask41[random];
                    break;
                case 2:
                    currentPicturePath = picturePathTask41[random + 18];
                    currentCorrectAnswer = correctAnswersTask41[random + 18];
                    break;
                case 3:
                    currentPicturePath = picturePathTask42[random];
                    currentCorrectAnswer = correctAnswersTask42[random];
                    break;
                case 4:
                    currentPicturePath = picturePathTask42[random + 18];
                    currentCorrectAnswer = correctAnswersTask42[random + 18];
                    break;
            }

        } else if (mode == 5) {
            timeLeft += 120;
            bonus = 1;
            currentPicturePath = picturePathTask5[x-1];
            currentCorrectAnswer = correctAnswersTask5[x-1];
            answerPath = answer1[x-1];

        } else if (mode == 6) {
            timeLeft += 120;
            bonus = 1;
            currentPicturePath = picturePathTask6[x-1];
            currentCorrectAnswer = correctAnswersTask6[x-1];
            answerPath = answer2[x-1];

        }

        // Load a picture
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

            cardPanel.add(panel, "Card0");
        } else {
            System.err.println("no picture：" + currentPicturePath);
        }

        // input box
        answerField = new JTextField();
        answerField.setBounds(655, 240, 150, 30);
        answerField.setBackground(new Color(60, 101, 85));
        answerField.setForeground(Color.BLACK);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.setFont(new Font("Arial", Font.BOLD, 14));
        add(answerField);

        // Attempts Tip
        attemptLabel = new JLabel("Attempts Left: 3", SwingConstants.CENTER);
        attemptLabel.setForeground(Color.white);
        attemptLabel.setFont(new Font("Arial", Font.BOLD, 14));
        attemptLabel.setBounds(555, 190, 350, 20);
        add(attemptLabel);

        // score
        scoreLabel = new JLabel("Current Score: 0", SwingConstants.CENTER);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setBounds(555, 210, 350, 20);
        add(scoreLabel);


        // Submit button
        submitButton = new RoundedButton("SUBMIT",
                new Color(40, 167, 69),
                new Color(33, 136, 56),
                new Color(25, 105, 44),
                Color.WHITE);
        submitButton.setBounds(655, 280, 150, 35);
        submitButton.addActionListener(this::handleSubmit);
        add(submitButton);

        // end button
        endButton = new RoundedButton("END",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        endButton.setBounds(655, 320, 150, 35);
        add(endButton);

        // Back to Home Button
        homeButton = new RoundedButton("HOME",
                new Color(0, 123, 255),
                new Color(0, 86, 214),
                new Color(0, 53, 179),
                Color.WHITE);
        homeButton.setBounds(655, 360, 150, 35);
        homeButton.addActionListener(e -> endQuiz());
        add(homeButton);

        // countdown timer
        timerLabel = new JLabel("Time Left: " + timeLeft + "s", SwingConstants.CENTER);
        timerLabel.setForeground(Color.white);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setBounds(355, 410, 350, 20);
        add(timerLabel);

        // Start countdown timer
        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");

            if (timeLeft <= 0) {
                countdownTimer.stop();
                submitButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Time's up!");
                endQuiz();
            }
        });
        countdownTimer.start();

    }

    /**
     * Handles the submit button action.
     * Validates the user's answer, updates the score, and manages attempts.
     * Shows the correct answer when all attempts are used up.
     * For bonus questions, displays the answer explanation image.
     *
     * @param e The action event from the submit button
     */
    private void handleSubmit(ActionEvent e) {
        String userAnswer = answerField.getText().trim();

        if (remainingAttempts <= 0) {
            JOptionPane.showMessageDialog(this, "This question has been completed！");
            return;
        }

        if (userAnswer.equalsIgnoreCase(currentCorrectAnswer)) {
            if (bonus == 0) {
                totalScore = remainingAttempts;
            } else {
                totalScore = remainingAttempts * 2;
            }

            JOptionPane.showMessageDialog(this, "Well done, get " + totalScore + " points！");
            Main.totalScore1 += totalScore;
            remainingAttempts = 0;
            scoreLabel.setText("Current Score: " + totalScore);
            submitButton.setEnabled(false);
            endQuiz();
        } else {
            remainingAttempts--;
            if (remainingAttempts > 0) {
                attemptLabel.setText("Attempts Left: " + remainingAttempts);
                JOptionPane.showMessageDialog(this, "Incorrect answer, please try again！");
            } else {
                submitButton.setEnabled(false);
                scoreLabel.setText("Current Score: " + totalScore);
                if (bonus == 0) {
                    JOptionPane.showMessageDialog(this, "The three chances have been used up, and the correct answer is: " + currentCorrectAnswer);
                } else {
                    showBonusImageFrame(answerPath);
                    JOptionPane.showMessageDialog(this, "The three chances have been used up, and the correct answer is: " + currentCorrectAnswer);
                }
                endQuiz();
            }
        }
    }

    /**
     * Displays a bonus image in a simple dialog.
     * Used for showing explanatory images for certain questions.
     *
     * @param imagePath Path to the image to be displayed
     */
    private void showBonusImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(this, label, "Bonus Image", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays a bonus image in a separate frame with custom styling.
     * Creates a new window with the specified image as its background.
     *
     * @param imagePath Path to the image to be displayed in the frame
     */
    public void showBonusImageFrame(String imagePath) {
        JFrame frame = new JFrame("Bonus Image");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(747, 588); // You can resize the window as needed
        try {
            BackgroundPanel bgPanel = new BackgroundPanel("src/" + imagePath);
            frame.setContentPane(bgPanel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load image: " + e.getMessage());
            return;
        }

        frame.setLocationRelativeTo(null); // center the screen
        frame.setVisible(true);
    }

    /**
     * Ends the current quiz session.
     * Stops the countdown timer if running and displays the final score.
     */
    public void endQuiz() {
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }
        JOptionPane.showMessageDialog(this, "You have achieved " + totalScore + " points in this session. Goodbye!");
    }

}
