package app;

import javax.swing.*;
import java.awt.*;

public class Window {

	/* pewnie potem sie przydadza wymiary */
	private final JFrame window;
	private final JPanel toolbar;
	private AnimPanel animPanel;
	private static final Elements elements = new Elements();
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 720;
	public static int DELTA_SPEED = 1;

	public Window() {
		window = elements.createFrame("Symulacja", WIDTH, HEIGHT);
		toolbar = new JPanel();
		addContent();
	}

	private void addContent() {
		JButton addDoctorButton = elements.createButton("Add Doctor");
		JButton addNormalButton = elements.createButton("Add Normal");
		JLabel speedLabel = new JLabel("Animation speed:");
		JSlider speedSetter = elements.createSlider(1, 50, 5);

		animPanel = new AnimPanel(WIDTH, HEIGHT - 36);
		int movingSpeed = speedSetter.getValue();
		// predkosc osoby bedzie zmieniana w Circle.speed
		// ale trzeba dobrze wartosci ustawic, bo 5 to juz jest szybko 

		toolbar.setBackground(Color.lightGray);
		toolbar.setLayout(new FlowLayout());

		addDoctorButton.addActionListener(e -> animPanel.addDoctor());
		addNormalButton.addActionListener(e -> animPanel.addNormal());
		speedSetter.addChangeListener(e -> animPanel.addNormal());

		toolbar.add(addDoctorButton);
		toolbar.add(addNormalButton);
		toolbar.add(speedLabel);
		toolbar.add(speedSetter);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				animPanel.initialize();
			}
		});

		window.add(toolbar, BorderLayout.SOUTH);
		window.add(animPanel, BorderLayout.CENTER);
	}
}