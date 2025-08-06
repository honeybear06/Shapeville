package com.qmul.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A custom JPanel that displays a background image.
 * This panel loads an image from a specified file path and renders it as the background,
 * automatically scaling the image to fill the entire panel area.
 * The panel is set to be transparent to allow proper layering with other components.
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Creates a new BackgroundPanel with the specified background image.
     * Loads the image from the given path and sets the panel to be transparent.
     *
     * @param imagePath Path to the image file to be used as background
     * @throws IOException If the image file cannot be loaded or accessed
     */
    public BackgroundPanel(String imagePath) throws IOException {
        backgroundImage = ImageIO.read(new File(imagePath));
        this.setOpaque(false); // Make panels transparent to avoid masking
    }

    /**
     * Overrides the paintComponent method to draw the background image.
     * The image is scaled to fit the current size of the panel.
     *
     * @param g The Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Must be retained
        // Zoom and draw backgrounds
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
