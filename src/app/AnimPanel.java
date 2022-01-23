package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import person.Person;

public class AnimPanel extends JPanel implements ActionListener {

	
	Image image; 		// bufor
	Graphics2D device; 	// wykreslacz ekranowy
	Graphics2D buffer; 	// wykreslacz bufora 

	// lista wszystkich obiektów klasy Person
	public final static List<Person> people = new ArrayList<>();
	public final static int delay = 15;

	private final Timer timer;

	public AnimPanel(int width, int height) {
		super();
		
		setSize(new Dimension(width, height));
		setBackground(Color.GRAY);

		timer = new Timer(delay, this);
		timer.start();
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
		for (int i = 0; i < 3; i++) {
			addInfected();
		}
		// bazowa ilość ZDROWYCH
		for (int i = 0; i < 50; i++) {
			addNormal();
		}
		// bazowa ilość DOKTOROW
		for (int i = 0; i < 2; i++) {
			addDoctor();
		}
	}


	// Dodawanie zarażonych do listy people i tworzenie nowego wątku
	void addInfected() {
		final Person newPerson = new Person(buffer, false);
		
		newPerson.setInfected(true);
		timer.addActionListener(newPerson);
		people.add(newPerson);
		new Thread(newPerson).start();
	}

	// Dodawanie doktora do listy people i tworzenie nowego wątku
	void addDoctor() {
		final Person newPerson = new Person(buffer, true);

		people.add(newPerson);
		timer.addActionListener(newPerson);
		new Thread(newPerson).start();
	}

	// Dodawanie zdrowego do listy people i tworzenie nowego wątku
	void addNormal() {
		final Person newPerson = new Person(buffer, false);

		people.add(newPerson);
		timer.addActionListener(newPerson);
		new Thread(newPerson).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}

	// zwracanie listy
	public static List<Person> getPeople() {
		return people;
	}

	public  void toolbarspeed(float sliderfloat){
		for(Person a : people)
			a.setspeed(sliderfloat);


	}
}
