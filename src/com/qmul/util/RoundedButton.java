package com.qmul.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A custom button component with rounded corners.
 * This button extends JButton but overrides the painting and event handling
 * to create a rounded rectangle appearance with color changes for different states
 * (normal, hover, and pressed). The button's visual feedback helps improve user experience.
 */
public class RoundedButton extends JButton {

    private Color normalColor;
    private Color hoverColor;
    private Color pressedColor;
    private Color textColor;

    private boolean hovered = false;
    private boolean pressed = false;

    private int cornerRadius = 20; // radius of the arc side (of a curve)

    /**
     * Creates a new rounded button with the specified text and colors.
     * Configures the button's appearance and adds mouse listeners to handle
     * state changes for visual feedback.
     *
     * @param text The text to display on the button
     * @param normalColor The button's background color in normal state
     * @param hoverColor The button's background color when hovered over
     * @param pressedColor The button's background color when pressed
     * @param textColor The color of the button's text
     */
    public RoundedButton(String text, Color normalColor, Color hoverColor, Color pressedColor, Color textColor) {
        super(text);
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = pressedColor;
        this.textColor = textColor;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(textColor);
        setFont(new Font("Arial", Font.BOLD, 20));

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
     * Overrides the paintComponent method to draw a rounded button.
     * Renders the button as a filled rounded rectangle with text centered in it.
     * The button's color changes based on its current state (normal, hover, or pressed).
     *
     * @param g The Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color bg = normalColor;
        if (pressed) {
            bg = pressedColor;
        } else if (hovered) {
            bg = hoverColor;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringAscent = fm.getAscent();

        int x = (getWidth() - stringWidth) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + stringAscent;

        g2.drawString(getText(), x, y);


        g2.dispose();
    }

    /**
     * Sets the radius of the button's rounded corners.
     * Updates the button's appearance by changing the corner radius and repainting.
     *
     * @param radius The new corner radius in pixels
     */
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
}

