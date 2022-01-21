package app;

import javax.swing.*;

import java.awt.*;

public class Elements {

    public JFrame createFrame(String text, int width, int height)
    {
        JFrame frame = new JFrame(text);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(47, 47, 47));
        frame.setVisible(true);

        return frame;
    }

    public JButton createButton(String text)
    {
        return createButton(text, 200, 50);
    }

    public JButton createButton(String text, int width, int height)
    {
        JButton button = new JButton(text);

        button.setSize(width, height);
        button.setFocusable(false);

        return button;
    }

    public JSlider createSlider(int minValue, int maxValue, int defaultValue)
    {
        JSlider slider = new JSlider(minValue, maxValue, defaultValue);

        slider.setFocusable(false);
        slider.setSize(200,50);

        return slider;
    }
}
