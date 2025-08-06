package com.qmul.ui;

import com.qmul.Main;
import com.qmul.util.BackgroundPanel;
import com.qmul.util.RoundedButton;
import com.qmul.util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A panel for displaying image-based questions and accepting user answers.
 * This panel implements a question interface with multiple question sets,
 * allowing users to answer questions about shapes, mathematical calculations, etc.
 * Users have three attempts for each question and earn points based on correct answers.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class QuestionPanel1 extends BackgroundPanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JTextField answerField;
    private final JLabel attemptLabel;
    private final JLabel scoreLabel;
    private final JProgressBar progressBar;
    public final RoundedButton submitButton;
    public final RoundedButton endButton;
    public final RoundedButton homeButton;
    public int ssss ;
    public int mode ;
    private String[] currentPicturePath;
    private String[] currentCorrectAnswers;


    // Question set 1
    private final String[] picturePath1 = {
            "image/task1/plane shapes1/circle.jpg",
            "image/task1/plane shapes1/heptagon.jpg",
            "image/task1/plane shapes1/hexagon.jpg",
            "image/task1/plane shapes1/kite.jpg",
            "image/task1/plane shapes1/octagon.jpg",
            "image/task1/plane shapes1/oval.jpg",
            "image/task1/plane shapes1/pentagon.jpg",
            "image/task1/plane shapes1/rectangle.jpg",
            "image/task1/plane shapes1/rhombus.jpg",
            "image/task1/plane shapes1/square.jpg",
            "image/task1/plane shapes1/triangle.jpg"
    };
    private final String[] correctAnswers1 = {
            "circle",       //
            "heptagon",     //
            "hexagon",      //
            "kite",         //
            "octagon",      //
            "oval",         //
            "pentagon",     //
            "rectangle",    //
            "rhombus",      //
            "square",       //
            "triangle"      //
    };

    // 题集2
    private final String[] picturePath2 = {
            "image/task1/solid shapes2/Cone.jpg",
            "image/task1/solid shapes2/Cube.jpg",
            "image/task1/solid shapes2/Cuboid.jpg",
            "image/task1/solid shapes2/Cylinder.jpg",
            "image/task1/solid shapes2/Sphere.jpg",
            "image/task1/solid shapes2/Square-based pyramid.jpg",
            "image/task1/solid shapes2/Tetrahedron.jpg",
            "image/task1/solid shapes2/Triangular prism.jpg"
    };

    private final String[] correctAnswers2 = {
            "cone",         //
            "cube",         //
            "cuboid",       //
            "cylinder",     //
            "sphere",       //
            "square-based pyramid", //
            "tetrahedron",  //
            "triangular prism" //
    };

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
            "7.5", "14", "12", "5", "36", "6",
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

    private final java.util.List<Integer> selectedIndices = new ArrayList<Integer>();

    private final int[] attemptsLeft = {3, 3, 3, 3};

    private int currentQuestionIndex = 0;
    private int totalScore = 0;
    private final int CARD_COUNT = 4;

    /**
     * Creates a new QuestionPanel1 with the specified background image and question set.
     * Initializes the UI components and randomly selects questions from the chosen set.
     *
     * @param backgroundPath Path to the background image
     * @param x Question set identifier:
     *          1 - plane shapes
     *          2 - solid shapes
     *          11 - task3a mathematical problems
     *          12 - task3b mathematical problems
     *          13 - task3c mathematical problems
     *          14 - task3d mathematical problems
     * @throws IOException If the background image cannot be loaded or accessed
     * @throws IllegalArgumentException If an invalid question set index is provided
     */
    public QuestionPanel1 (String backgroundPath, int x) throws IOException {

        super(backgroundPath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        // Card Layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(248, 196, 370, 180); // Center the image
        cardPanel.setOpaque(false);
        setBackground(Color.BLACK);
        add(cardPanel);


        if (x == 1) {
            mode = 1;
            currentPicturePath = picturePath1;
            currentCorrectAnswers = correctAnswers1;
        } else if (x == 2) {
            mode = 1;
            ssss = 2;
            currentPicturePath = picturePath2;
            currentCorrectAnswers = correctAnswers2;
        } else if (x == 11) {
            mode = 3;
            currentPicturePath = picturePathTask3a;
            currentCorrectAnswers = correctAnswersTask3a;
        } else if (x == 12) {
            mode = 3;
            currentPicturePath = picturePathTask3b;
            currentCorrectAnswers = correctAnswersTask3b;
        } else if (x == 13) {
            mode = 3;
            currentPicturePath = picturePathTask3c;
            currentCorrectAnswers = correctAnswersTask3c;
        } else if (x == 14) {
            mode = 3;
            currentPicturePath = picturePathTask3d;
            currentCorrectAnswers = correctAnswersTask3d;
        }else {
            throw new IllegalArgumentException("Invalid quiz set index: " + x);
        }


        // Each card shows a picture of the topic
        for (int i = 0; i < CARD_COUNT; i++) {
            int size = currentPicturePath.length;
            int randomNumber;
            do {
                randomNumber = (int)(Math.random() * size);
            } while (selectedIndices.contains(randomNumber)); // Avoiding duplication of selections

            selectedIndices.add(randomNumber);

            java.net.URL imgUrl = getClass().getClassLoader().getResource(currentPicturePath[randomNumber]);
            if (imgUrl == null) {
                System.err.println("find no picture" + currentPicturePath[randomNumber]);
                continue;
            }

            ImageIcon icon = new ImageIcon(imgUrl);
            Image scaled = icon.getImage().getScaledInstance(370, 180, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            RoundedPanel panel = new RoundedPanel(30);
            panel.setLayout(new BorderLayout());
            panel.setOpaque(false);
            panel.add(imageLabel, BorderLayout.CENTER);

            cardPanel.add(panel, "Card" + i);
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

        // progress bar
        progressBar = new JProgressBar(0, CARD_COUNT);
        progressBar.setValue(0);
        progressBar.setBounds(257, 410, 350, 20);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(82, 43, 163)); // 设置进度条的颜色
        progressBar.setBackground(new Color(36, 44, 65)); // 设置背景色（未填充部分）
        add(progressBar);

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
//        endButton.addActionListener(e -> endQuiz());
        add(endButton);

        // Go Home Button
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
     * Handles the submit button action.
     * Validates the user's answer, updates the score, and manages attempts.
     * Moves to the next question when the current question is completed.
     *
     * @param e The action event from the submit button
     */
    private void handleSubmit(ActionEvent e) {

        String userAnswer = answerField.getText().trim();

        if (attemptsLeft[currentQuestionIndex] <= 0) {
            JOptionPane.showMessageDialog(this, "This question has been completed！");
            return;
        }

        String correctAnswer = currentCorrectAnswers[selectedIndices.get(currentQuestionIndex)];

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {

            int earned = attemptsLeft[currentQuestionIndex];
            totalScore += earned;
            if (ssss == 2){
                totalScore += earned;
            }
            if (ssss == 2){
                JOptionPane.showMessageDialog(this, "Well done, get " + earned * 2 + " points！");
                Main.totalScore1 += earned *2 ;
            } else {
                JOptionPane.showMessageDialog(this, "Well done, get " + earned + " points！");
                Main.totalScore1 += earned ;
            }
            attemptsLeft[currentQuestionIndex] = 0;

            moveToNextQuestion();
        } else {

            attemptsLeft[currentQuestionIndex]--;

            if (attemptsLeft[currentQuestionIndex] > 0) {

                attemptLabel.setText("Attempts Left: " + attemptsLeft[currentQuestionIndex]);
                JOptionPane.showMessageDialog(this, "Incorrect answer, please try again！");
            } else {
                JOptionPane.showMessageDialog(this, "The three chances have been used up, and the correct answer is:" + correctAnswer);
                moveToNextQuestion();
            }
        }

        scoreLabel.setText("Current Score: " + totalScore);
    }


    /**
     * Advances to the next question in the quiz.
     * Updates UI components and progress indicators.
     * Ends the quiz if all questions have been answered.
     */
    public void moveToNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex >= CARD_COUNT) {
            endQuiz();
            submitButton.setEnabled(false);
            return;
        }

        cardLayout.next(cardPanel);
        answerField.setText("");
        attemptLabel.setText("Attempts Left: " + attemptsLeft[currentQuestionIndex]);
        progressBar.setValue(currentQuestionIndex);
    }

    /**
     * Ends the current quiz session.
     * Displays the final score and completion message.
     */
    public void endQuiz() {
        progressBar.setValue(4);
        JOptionPane.showMessageDialog(this, "You have achieved " + totalScore + " points in this session. Goodbye!" );
    }
}
