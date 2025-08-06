package com.qmul.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * A custom button component with a circular shape.
 * This button extends JButton but overrides the painting and event handling
 * to create a circular appearance with color changes for different states
 * (normal, hover, and pressed). The button's hit detection is also modified
 * to respond only to clicks within the circular area.
 */
public class CircleButton extends JButton {

    private Color normalColor;
    private Color hoverColor;
    private Color pressedColor;
    private Color textColor;

    private boolean hovered = false;
    private boolean pressed = false;

    /**
     * Creates a new circular button with the specified text and colors.
     * Configures the button's appearance and adds mouse listeners to handle
     * state changes for visual feedback.
     *
     * @param text The text to display on the button
     * @param normalColor The button's background color in normal state
     * @param hoverColor The button's background color when hovered over
     * @param pressedColor The button's background color when pressed
     * @param textColor The color of the button's text
     */
    public CircleButton(String text, Color normalColor, Color hoverColor, Color pressedColor, Color textColor) {
        super(text);
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = pressedColor;
        this.textColor = textColor;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(textColor);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                pressed = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    /**
     * Overrides the paintComponent method to draw a circular button.
     * Renders the button as a filled circle with text centered in it.
     * The button's color changes based on its current state (normal, hover, or pressed).
     *
     * @param g The Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        Color bg = normalColor;
        if (pressed) {
            bg = pressedColor;
        } else if (hovered) {
            bg = hoverColor;
        }

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw a circle
        g2.setColor(bg);
        g2.fillOval((width - diameter) / 2, (height - diameter) / 2, diameter, diameter);

        // picture picture (e.g. of a character)
        g2.setColor(textColor);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textAscent = fm.getAscent();

        int x = (width - textWidth) / 2;
        int y = (height - fm.getHeight()) / 2 + textAscent;
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    /**
     * Overrides the contains method to provide circular hit detection.
     * Returns true only if the specified point is within the circular area of the button.
     *
     * @param x The x-coordinate of the point to test
     * @param y The y-coordinate of the point to test
     * @return true if the point is inside the circular area of the button, false otherwise
     */
    @Override
    public boolean contains(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);

        Ellipse2D circle = new Ellipse2D.Double((width - diameter) / 2, (height - diameter) / 2, diameter, diameter);
        return circle.contains(x, y);
    }
}
