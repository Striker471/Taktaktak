package person;

import java.awt.*;
import java.util.Random;

import static app.Window.HEIGHT;
import static app.Window.WIDTH;
import static java.lang.Math.abs;

public class Person extends Rectangle {    // musi rozszerzac jakas klase od ksztaltu
	private static final int size = 4;
	private static final int MARGIN = 1;    // margines spawnowania
	private static final Random RND = new Random();


	private double x;    // obecny X
	private double y;    // obecny Y


	private static final double maxiV = 8.0;
	private static final double miniV = 4.0;
	private double vx;    // wektor X
	private double vy;    // wektor Y
	private boolean infected = false;    // informacja czy jest zarazony
	private boolean vaxxed = false;        // informacja czy jest zaszczepiony
	private final boolean doctor;        // informacja czy jest doktorem
	private Color color;

	/* konstruktor z losowa pozycja */
	public Person(boolean doctor) {
		this(RND.nextInt(WIDTH - 2 * MARGIN) + MARGIN, RND.nextInt(HEIGHT - 2 * MARGIN) + MARGIN, doctor);
	}

	/* pelny konstruktor */
	public Person(double x, double y, boolean doctor) {
		this.doctor = doctor;
		this.x = x;
		this.y = y;

		/* poczatkowy wektor: + albo - (przy kolizji z krawedzia okna bedzie zmienial znak) */
		this.vx = RND.nextBoolean() ? 4 : -4;
		this.vy = RND.nextBoolean() ? 4 : -4;
		color = doctor ? PersonColors.DOCTOR : PersonColors.NORMAL;

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

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	private boolean setX(double newbieX) {
		if (newbieX > 0.0 && newbieX < WIDTH) {
			x = newbieX;
			return true;
		} else if (newbieX <= 0.0) {
			x = 0.0;
		} else {
			x = WIDTH;
		}
		return false;
	}

	private boolean setY(double newbieY) {
		if (newbieY > 0.0 && newbieY < HEIGHT) {
			y = newbieY;
			return true;
		} else if (newbieY <= 0.0) {
			y = 0.0;
		} else {
			y = HEIGHT;
		}
		return false;
	}

	public void move() {

		if (!(setX(getX() + vx))) {
			vx = -vx / abs(vx);
			vx = vx * (RND.nextDouble() * (maxiV - miniV) + miniV);
			//vx = 4 * RND.nextDouble();
			// vx *= RND.nextBoolean() ? 1 : -1;
		}
		if (!(setY(getY() + vy))) {
			vy = -vy / abs(vy);
			vy = vy * (RND.nextDouble() * (maxiV - miniV) + miniV);
		}


		// 	+ wykrywanie sąsiadów

	}
}