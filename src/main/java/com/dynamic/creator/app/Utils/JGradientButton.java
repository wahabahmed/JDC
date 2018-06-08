package com.dynamic.creator.app.Utils;

import javax.swing.*;
import java.awt.*;

public class JGradientButton extends JButton {
    public JGradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                new Color(155, 224, 253),
                new Point(0, getHeight() / 3),
                Color.WHITE));
        g2.fillRect(0, 0, getWidth(), getHeight() / 3);
        g2.setPaint(new GradientPaint(
                new Point(0, getHeight() / 3),
                Color.WHITE,
                new Point(0, getHeight()),
                new Color(155, 224, 253)));
        g2.fillRect(0, getHeight() / 3, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}
