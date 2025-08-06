package com.qmul.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A panel for displaying timed quiz questions with extended countdown functionality.
 * This panel extends `QuestionPanel1` and adds a countdown timer to limit the total time
 * available for the quiz. Users must complete the quiz before the timer runs out.
 */
public class QuestionPanel4 extends QuestionPanel1 {

    private final JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 180; // countdown time

    /**
     * Creates a new `QuestionPanel4` with the specified background image and question set.
     * Initializes the UI components and starts the countdown timer.
     *
     * @param backgroundPath Path to the background image
     * @param x Question set identifier
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public QuestionPanel4(String backgroundPath, int x) throws IOException {
        super(backgroundPath, x);

        // Creating a countdown display label
        timerLabel = new JLabel("Time Left: " + timeRemaining + "s", SwingConstants.CENTER);
        timerLabel.setForeground(Color.YELLOW);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setBounds(655, 150, 150, 30);
        add(timerLabel);

        startTimer(); // Start the countdown
    }

    /**
     * Starts the countdown timer and updates the timer label every second.
     * Ends the quiz when the timer reaches zero.
     */
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    timeRemaining--;
                    timerLabel.setText("Time Left: " + timeRemaining + "s");

                    if (timeRemaining <= 0) {
                        timer.cancel();
                        JOptionPane.showMessageDialog(null, "Time's up!");
                        endQuiz();
                    }
                });
            }
        }, 1000, 1000); // Executed once per second
    }

    @Override
    public void moveToNextQuestion() {
        super.moveToNextQuestion();
        timeRemaining = 180;
    }

    /**
     * Ends the current quiz session.
     * Stops the countdown timer and calls the parent class's `endQuiz` method.
     */
    @Override
    public void endQuiz() {
        if (timer != null) {
            timer.cancel(); // Stop the countdown.
        }
        super.endQuiz(); // Calling parent class methods
    }
}
