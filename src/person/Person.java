package person;

import java.awt.*;
import java.util.Random;

import static app.Window.HEIGHT;
import static app.Window.WIDTH;

public class Person extends Rectangle {	// musi rozszerzac jakas klase od ksztaltu
	private static final int size = 4;
	private static final int MARGIN = 10;	// margines spawnowania
	private static final Random RND = new Random();


	private double x;	// obecny X
	private double y;	// obecny Y
	private double vx;	// wektor X
	private double vy;	// wektor Y
	private boolean infected = false;	// informacja czy jest zarazony
	private boolean vaxxed = false;		// informacja czy jest zaszczepiony
	private final boolean doctor;		// informacja czy jest doktorem
	private Color color;
	
	/* konstruktor z losowa pozycja */
	public Person(boolean doctor) {
		this(RND.nextInt(WIDTH-2*MARGIN) + MARGIN, RND.nextInt(HEIGHT-2*MARGIN) + MARGIN, doctor);
	}
	
	/* pelny konstruktor */
	public Person(double x, double y, boolean doctor) {
		this.doctor = doctor;
		this.x = x;
		this.y = y;

		/* poczatkowy wektor: + albo - (przy kolizji z krawedzia okna bedzie zmienial znak) */
		this.vx = RND.nextBoolean() ? 4 : -4;
		this.vy = RND.nextBoolean() ? 4 : -4;
		color = doctor ? PersonColors.DOCTOR: PersonColors.NORMAL;

		/*
		 * Ustawienie x/y i koloru w metodach Swinga
		 */
	}
	
	public void setInfected(boolean val) {
		this.infected = val;
		this.color = PersonColors.INFECTED;
		// zmiana koloru metoda Swinga
	}
	
	public void setVaxxed(boolean val) {
		this.vaxxed = val;
		this.color = PersonColors.VAXXED;
		// zmiana koloru metoda Swinga
	}
		
	public boolean isInfected() {
		return infected;
	}
	
	public boolean isVaxxed() {
		return vaxxed;
	}
	
	public boolean isDoctor() {
		return doctor;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void move() {
		/*
		 * 	setX(getX() + vx);
		 * 	setY(getY() + vy);
		 * 	+ wykrywanie kolizji z oknem
		 */
	}
}
