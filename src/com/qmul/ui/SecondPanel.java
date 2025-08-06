package com.qmul.ui;

import com.qmul.util.BackgroundPanel;
import com.qmul.util.CircleButton;
import com.qmul.util.RoundedButton;
import com.qmul.util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A panel for task selection in the application.
 * This panel displays different task options using a card layout, which users
 * can navigate through using directional buttons. Each card represents a different
 * mathematical task or challenge that the user can select to play.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class SecondPanel extends BackgroundPanel {

    public RoundedButton playButton;
    public RoundedButton backButton;
    public CircleButton leftButton;
    public CircleButton rightButton;
    public CircleButton upButton;
    public CircleButton downButton;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    public int cardIndex = 0;
    private final int CARD_COUNT = 6; // You can change the number of cards by yourself

    /**
     * Creates a new SecondPanel with the specified background image.
     * Initializes the UI components including task cards, navigation buttons,
     * and action buttons for playing tasks and returning to the previous screen.
     *
     * @param backgroundImagePath Path to the background image
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public SecondPanel(String backgroundImagePath) throws IOException {
        super(backgroundImagePath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        // Card Panel Configuration
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(308, 90, 280, 210); // Centered, adjustable
        //cardPanel.setOpaque(false);

        Color[] bgColors = {
                new Color(255, 228, 225),
                new Color(224, 255, 255),
                new Color(240, 255, 240),
                new Color(255, 228, 225),
                new Color(224, 255, 255),
                new Color(255, 228, 225),
                new Color(255, 228, 225),
                new Color(255, 228, 225)};
        String[] labelTexts = {
                "<html><div style='text-align:center;'>Task 1:<br><span style='font-size:14px;'>Identification of Shapes</span></div></html>",
                "<html><div style='text-align:center;'>Task 2:<br><span style='font-size:14px;'>Identification of Angle Types</span></div></html>",
                "<html><div style='text-align:center;'>Task 3:<br><span style='font-size:14px;'>Area Calculation of shapes</span></div></html>",
                "<html><div style='text-align:center;'>Task 4:<br><span style='font-size:14px;'>Area and Circumference Calculation of Circle</span></div></html>",
                "<html><div style='text-align:center;'>Bonus 1:<br><span style='font-size:14px;'>Compound Shape Area Calculation</span></div></html>",
                "<html><div style='text-align:center;'>Bonus 2:<br><span style='font-size:14px;'>Sector Area and Arc Length Calculation</span></div></html>"
        };

        for (int i = 1; i <= CARD_COUNT; i++) {
            JLabel label = new JLabel(labelTexts[i - 1], SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 28));
            label.setForeground(Color.BLACK);
            label.setOpaque(false); // open (non-secretive)

            RoundedPanel panel = new RoundedPanel(30);
            panel.setLayout(new BorderLayout());
            panel.setBackground(bgColors[i - 1]); // Background color in effect
            panel.setOpaque(false); // Allows us to draw our own rounded corners
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // increase padding
            panel.add(label, BorderLayout.CENTER);

            cardPanel.add(panel, "Card" + i);

        }

        this.add(cardPanel);

        // Left page turn button
        leftButton = new CircleButton("L",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        leftButton.setBounds(275, 370, 50, 50);
        leftButton.setFont(new Font("Arial", Font.BOLD, 25));
        leftButton.setVerticalAlignment(SwingConstants.CENTER);   // vertical centering
        // Setting the button text inner margin
        leftButton.setMargin(new Insets(0, 0, 0, 0));
        leftButton.addActionListener(e -> {
            cardIndex = (cardIndex - 1 + CARD_COUNT) % CARD_COUNT;
            cardLayout.previous(cardPanel);
        });
        this.add(leftButton);

        // Right flip button
        rightButton = new CircleButton("R",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        rightButton.setBounds(372, 370, 50, 50);
        rightButton.setFont(new Font("Arial", Font.BOLD, 20));
        rightButton.addActionListener(e -> {
            cardIndex = (cardIndex + 1) % CARD_COUNT;
            cardLayout.next(cardPanel);
        });
        this.add(rightButton);

        upButton = new CircleButton("X",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        upButton.setBounds(323, 325, 50, 50);
        upButton.setFont(new Font("Arial", Font.BOLD, 20));
        upButton.addActionListener(e -> {
            new ProgressFrame();
        });
        this.add(upButton);

        downButton = new CircleButton("Y",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE);
        downButton.setBounds(323, 415, 50, 50);
        downButton.setFont(new Font("Arial", Font.BOLD, 20));
        downButton.addActionListener(e -> {
            new ProgressFrame();
        });
        this.add(downButton);

        // playback button
        playButton = new RoundedButton(
                "PLAY",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE
        );

        playButton.setBounds(475, 375, 160, 40);
        this.add(playButton);


        // Back button
        backButton = new RoundedButton(
                "BACK",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE
        );
        backButton.setBounds(475, 425, 160, 40);
        this.add(backButton);
    }
}
