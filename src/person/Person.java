package person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;


public class Person extends Circle implements ActionListener {	// musi rozszerzac jakas klase od ksztaltu

//	private double x;	// obecny X
//	private double y;	// obecny Y
//	private double vx;	// wektor X
//	private double vy;	// wektor Y
	private boolean infected = false;	// informacja czy jest zarazony
	private boolean vaxxed = false;		// informacja czy jest zaszczepiony
	private final boolean doctor;		// informacja czy jest doktorem
	private Color color = PersonColors.NORMAL;

	/* pelny konstruktor */
	public Person(Graphics2D buffer, int delay, boolean isADoctor) {
		super(buffer, delay);
		doctor = isADoctor;
		if(isADoctor) {
			color = PersonColors.DOCTOR;
		}
		affineTransform = new AffineTransform();

		/* poczatkowy wektor: + albo - (przy kolizji z krawedzia okna bedzie zmienial znak) */
//		this.vx = RND.nextBoolean() ? 4 : -4;
//		this.vy = RND.nextBoolean() ? 4 : -4;

	}

	public void setInfected(boolean val) {
		this.infected = val;
		this.color = PersonColors.INFECTED;
	}

	public void setVaxxed(boolean val) {
		this.vaxxed = val;
		this.color = PersonColors.VAXXED;
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

	@Override
	public void actionPerformed(ActionEvent evt) {
		// wypelnienie obiektu
		g2DBuffer.setColor(color);
		g2DBuffer.fill(shape);
		g2DBuffer.draw(shape);
	}

//	public double getdWidth() {
//		return x;
//	}
//
//	public double getdHeight() {
//		return y;
//	}

//	public void move() {
//		/*
//		 * 	setX(getdWidth() + vx);
//		 * 	setY(getdHeight() + vy);
//		 * 	+ wykrywanie kolizji z oknem
//		 */
//	}
}