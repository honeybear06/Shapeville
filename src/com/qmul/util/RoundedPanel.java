package com.qmul.util;

import javax.swing.*;
import java.awt.*;

/**
 * A custom panel component with rounded corners.
 * This panel extends JPanel but overrides the painting to create a rounded rectangle
 * appearance. It is set as non-opaque to allow for custom background painting.
 * This component can be used to create visually appealing containers with soft edges.
 */
public class RoundedPanel extends JPanel {
    private int cornerRadius;

    /**
     * Creates a new rounded panel with the specified corner radius.
     * Configures the panel to be non-opaque to allow for custom background painting.
     *
     * @param radius The corner radius in pixels
     */
    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // Enables us to draw custom backgrounds
    }

    /**
     * Overrides the paintComponent method to draw a rounded panel.
     * Renders the panel as a filled rounded rectangle using the panel's background color.
     * Uses anti-aliasing to create smooth rounded corners.
     *
     * @param g The Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Setting the background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcs.width, arcs.height);
        g2.dispose();
    }
}
