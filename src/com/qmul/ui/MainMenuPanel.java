package com.qmul.ui;

import java.awt.*;
import java.io.IOException;
import com.qmul.util.BackgroundPanel;
import com.qmul.util.RoundedButton;

/**
 * Represents the main menu panel of the application.
 * This panel serves as the entry point for the user interface, displaying the start and quit buttons.
 * Extends BackgroundPanel to provide a customized background image.
 */
public class MainMenuPanel extends BackgroundPanel {
    public RoundedButton startButton;
    public RoundedButton quitButton;

    /**
     * Creates a new MainMenuPanel with the specified background image.
     * Sets up the panel layout and initializes the start and quit buttons with their respective styles.
     *
     * @param backgroundImagePath The file path to the background image to be displayed
     * @throws IOException If the background image cannot be loaded or accessed
     */
    public MainMenuPanel(String backgroundImagePath) throws IOException {
        super(backgroundImagePath);
        this.setBounds(0, 0, 900, 600);
        this.setLayout(null);

        startButton = new RoundedButton(
                "START",
                new Color(0, 123, 255),
                new Color(0, 86, 214),
                new Color(0, 53, 179),
                Color.WHITE
        );
        startButton.setBounds(125, 470, 220, 80);
        this.add(startButton);

        quitButton = new RoundedButton(
                "QUIT",
                new Color(220, 53, 69),
                new Color(200, 35, 51),
                new Color(180, 20, 30),
                Color.WHITE
        );
        quitButton.setBounds(555, 470, 220, 80);
        this.add(quitButton);
    }
}
