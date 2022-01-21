package app;

import javax.swing.*;
import java.awt.*;

public class Window {

	/* pewnie potem sie przydadza wymiary */
	private final JFrame window;
	private final JPanel toolbar;
	private AnimPanel animPanel;
	private static final Elements elements = new Elements();
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	public static int DELTA_SPEED = 1;

	public Window() {
		window = elements.createFrame("Symulacja", WIDTH, HEIGHT);
		toolbar = new JPanel();
		addContent();
	}

	private void addContent() {
		JButton addDoctorButton = elements.createButton("Add Doctor");
		JButton animateButton = elements.createButton("Animate");
		JButton addNormalButton = elements.createButton("Add Normal");

		animPanel = new AnimPanel(WIDTH-200,HEIGHT-200);

		toolbar.setBackground(Color.lightGray);
		toolbar.setLayout(new FlowLayout());

		addDoctorButton.addActionListener(e -> animPanel.addDoctor());
		animateButton.addActionListener(e -> animPanel.animate());
		addNormalButton.addActionListener(e -> animPanel.addNormal());

		toolbar.add(addDoctorButton);
		toolbar.add(addNormalButton);
		toolbar.add(animateButton);

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