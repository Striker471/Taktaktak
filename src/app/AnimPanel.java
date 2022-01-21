package app;

import person.Circle;
import person.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimPanel extends JPanel implements ActionListener {

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private int delay = 70;

	private final Timer timer;

	public AnimPanel(int width, int height) {

		super();

		this.setPreferredSize(new Dimension(width,height));

		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// bazowa ilość INFECTED
		for(int i = 0; i < 10; i++){
			addInfected();
		}
	}

	void addInfected() {
		Circle infected = new Person(buffer, delay, false);
		((Person) infected).setInfected(true);
		timer.addActionListener(infected);
		new Thread(infected).start();
	}

	void addDoctor() {
		Circle doctor =  new Person(buffer, delay, true);
		timer.addActionListener(doctor);
		new Thread(doctor).start();
	}

	void addNormal() {
		Circle normal = new Person(buffer, delay, false);
		timer.addActionListener(normal);
		new Thread(normal).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	protected void setDelay(int delay)
	{
		this.delay = delay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}
}
