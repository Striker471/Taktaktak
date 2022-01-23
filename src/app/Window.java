package app;

import person.Person;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
	public static int currentspeed = 2 ;
	public Window() {
		window = elements.createFrame("Symulacja", WIDTH, HEIGHT);
		toolbar = new JPanel();
		addContent();
	}

	private void addContent() {
		JButton addDoctorButton = elements.createButton("Add Doctor");
		JButton addNormalButton = elements.createButton("Add Normal");
		JButton startVaccinating = elements.createButton("Start vaccinating");
		JLabel speedLabel = new JLabel("Animation speed:");
		JSlider speedSetter = elements.createSlider(1, 5,2 );

		animPanel = new AnimPanel(WIDTH, HEIGHT - 36);
		int movingSpeed = speedSetter.getValue();
		// predkosc osoby bedzie zmieniana w Circle.speed
		// ale trzeba dobrze wartosci ustawic, bo 5 to juz jest szybko 

		toolbar.setBackground(Color.lightGray);
		toolbar.setLayout(new FlowLayout());

		addDoctorButton.addActionListener(e -> animPanel.addDoctor());
		addNormalButton.addActionListener(e -> animPanel.addNormal());
    startVaccinating.addActionListener(e-> Person.startVaxx = true);
    
		speedSetter.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				 if(currentspeed!=source.getValue()){
					currentspeed= source.getValue();
					Person.speed = currentspeed;
         }
       }
		});


		toolbar.add(addDoctorButton);
		toolbar.add(addNormalButton);
		toolbar.add(startVaccinating);
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