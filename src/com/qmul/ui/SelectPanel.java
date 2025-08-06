package com.qmul.ui;

import com.qmul.Main;
import com.qmul.util.BackgroundPanel;
import com.qmul.util.CircleButton;
import com.qmul.util.RoundedButton;
import com.qmul.util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A panel for selecting specific tasks within a chosen category.
 * This panel displays different task options as image cards, which users
 * can navigate through using directional buttons. The panel shows a progress bar
 * indicating the current position in the card sequence.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class SelectPanel extends BackgroundPanel {

    public RoundedButton playButton;
    public RoundedButton backButton;
    public CircleButton leftButton;
    public CircleButton rightButton;
    public CircleButton upButton;
    public CircleButton downButton;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    public int cardIndex = 0;
    private int CARD_COUNT;

    private JProgressBar progressBar; // progress bar

    private String[] imagePaths;

    public int totalScore;
    public int finishedQuizCount;

    private JLabel scoreLabel;
    private JLabel finishedLabel;
    /**
     * Creates a new SelectPanel with the specified background image and task type.
     * Initializes the UI components including task image cards, navigation buttons,
     * progress indicator, and action buttons for playing tasks and returning.
     *
     * @param backgroundImagePath Path to the background image
     * @param task The task category to display (task1, task2, task3, task4, bonus1, or bonus2)
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public SelectPanel(String backgroundImagePath, String task) throws IOException {
        super(backgroundImagePath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(308, 90, 280, 210);

        int width = 280;
        int height = 210;
        if (task.equals("task1")) {
            imagePaths = new String[]{"src/image/task1/2D.jpg", "src/image/task1/3D.jpg"};
            totalScore = Main.totalScore1;
            finishedQuizCount = Main.finishedQuizCount1;
        } else if (task.equals("task2")) {
            imagePaths = new String[]{"images/task1.gif", "images/task2.gif"};
        } else if (task.equals("task3")) {
            imagePaths = new String[]{"src/image/task3/1.png", "src/image/task3/2.png", "src/image/task3/3.png", "src/image/task3/4.png"};
            height = 153;
            totalScore = Main.totalScore3;
            finishedQuizCount = Main.finishedQuizCount3;

        } else if (task.equals("task4")) {
            imagePaths = new String[]{"src/image/task4/1.png", "src/image/task4/2.png","src/image/task4/3.png", "src/image/task4/4.png"};
            width = 210;
            totalScore = Main.totalScore4;
            finishedQuizCount = Main.finishedQuizCount4;

        } else if (task.equals("bonus1")) {
            imagePaths = new String[]{"src/image/bonus1/2.png", "src/image/bonus1/3.png", "src/image/bonus1/4.png", "src/image/bonus1/5.png", "src/image/bonus1/6.png", "src/image/bonus1/7.png"};
            height = 153;
            totalScore = Main.totalScore5;
            finishedQuizCount = Main.finishedQuizCount5;

        } else if (task.equals("bonus2")) {
            imagePaths = new String[]{"src/image/bonus2/1.png", "src/image/bonus2/2.png", "src/image/bonus2/3.png", "src/image/bonus2/4.png", "src/image/bonus2/5.png", "src/image/bonus2/6.png", "src/image/bonus2/7.png", "src/image/bonus2/8.png"};
            height = 153;
            totalScore = Main.totalScore6;
            finishedQuizCount = Main.finishedQuizCount6;
        }
        CARD_COUNT = imagePaths.length;

        for (int i = 0; i < CARD_COUNT; i++) {
            ImageIcon icon = loadImageIcon(imagePaths[i], width, height);
            JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);
            imageLabel.setOpaque(false);
            RoundedPanel panel = new RoundedPanel(30);
            panel.setLayout(new BorderLayout());
            panel.setBackground(Color.WHITE);
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            panel.add(imageLabel, BorderLayout.CENTER);

            cardPanel.add(panel, "Card" + (i + 1));
        }

        this.add(cardPanel);

        // Adding a progress bar
        progressBar = new JProgressBar(0, CARD_COUNT);
        progressBar.setBounds(308, 300, 280, 17);
        progressBar.setValue(cardIndex + 1);
        progressBar.setStringPainted(true);
        updateProgressBar();
        this.add(progressBar);

        leftButton = createCircleButton("L", 275, 370, e -> showPreviousCard());
        rightButton = createCircleButton("R", 372, 370, e -> showNextCard());
        upButton = createCircleButton("X", 323, 325, e -> showprogress());
        downButton = createCircleButton("Y", 323, 415, e -> showprogress());

        this.add(leftButton);
        this.add(rightButton);
        this.add(upButton);
        this.add(downButton);

        playButton = new RoundedButton("PLAY", new Color(220, 53, 69),
                new Color(200, 35, 51), new Color(180, 20, 30), Color.WHITE);
        playButton.setBounds(475, 375, 160, 40);
        this.add(playButton);

        backButton = new RoundedButton("BACK", new Color(220, 53, 69),
                new Color(200, 35, 51), new Color(180, 20, 30), Color.WHITE);
        backButton.setBounds(475, 425, 160, 40);
        this.add(backButton);
    }

    /**
     * Navigates to the previous card in the sequence.
     * Updates the card index, shows the previous card, and refreshes the progress bar.
     */
    private void showPreviousCard() {
        cardIndex = (cardIndex - 1 + CARD_COUNT) % CARD_COUNT;
        cardLayout.previous(cardPanel);
        updateProgressBar();
    }

    private void showprogress() {
        new ProgressFrame();

    }

    /**
     * Navigates to the next card in the sequence.
     * Updates the card index, shows the next card, and refreshes the progress bar.
     */
    private void showNextCard() {
        cardIndex = (cardIndex + 1) % CARD_COUNT;
        cardLayout.next(cardPanel);
        updateProgressBar();
    }

    /**
     * Updates the progress bar to reflect the current card position.
     * Sets the value and text of the progress bar based on the current card index.
     */
    private void updateProgressBar() {
        progressBar.setValue(cardIndex + 1);
        progressBar.setString((cardIndex + 1) + " / " + CARD_COUNT);
    }

    /**
     * Creates a circular button with the specified properties.
     * Configures the button's appearance, position, and click handler.
     *
     * @param text The text to display on the button
     * @param x The x-coordinate position of the button
     * @param y The y-coordinate position of the button
     * @param action The action listener to handle button clicks
     * @return A configured CircleButton instance
     */
    private CircleButton createCircleButton(String text, int x, int y, java.awt.event.ActionListener action) {
        CircleButton btn = new CircleButton(text, new Color(220, 53, 69),
                new Color(200, 35, 51), new Color(180, 20, 30), Color.WHITE);
        btn.setBounds(x, y, 50, 50);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.addActionListener(action);
        return btn;
    }

    /**
     * Loads and scales an image from the specified file path.
     * Verifies that the file exists before attempting to load it.
     *
     * @param path The file path to the image
     * @param width The desired width of the scaled image
     * @param height The desired height of the scaled image
     * @return An ImageIcon containing the scaled image
     */
    private ImageIcon loadImageIcon(String path, int width, int height) {
        File imageFile = new File(path);
        if (!imageFile.exists()) {
            System.err.println("Image not found: " + path);
            return new ImageIcon();
        }
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }


}
