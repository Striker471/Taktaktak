package person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import app.AnimPanel;
import utils.Helper;

public class Person extends Circle implements ActionListener {

	private boolean immune = false;
	private boolean infected = false; // informacja czy jest zarazony
	private boolean vaxxed = false; // informacja czy jest zaszczepiony
	private final boolean doctor; // informacja czy jest doktorem
	private Color color = PersonColors.NORMAL;

	/* pelny konstruktor */
	public Person(Graphics2D buffer, boolean isADoctor) {
		super(buffer);
		doctor = isADoctor;
		if (isADoctor) {
			color = PersonColors.DOCTOR;
		}
		affineTransform = new AffineTransform();

		area = new Area(super.shape);
	}

	public void setInfected(boolean val) {
		this.infected = val;

		if (!val) {
			this.color = PersonColors.NORMAL;
			this.immune = true;
		} else {
			this.color = PersonColors.INFECTED;
		}
	}

	public void setVaxxed(boolean val) {
		this.vaxxed = val;
		this.color = PersonColors.VAXXED;
	}
	
	public boolean isImmune() {
		return immune;
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

	@Override
	public void run() {
		// przesuniecie na srodek
		area.transform(affineTransform);

		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(AnimPanel.delay);
				if (!vaxxed || doctor || infected) {
					Helper.isColliding(this);
				}
			} catch (Exception e) {
				return;
			}
		}
	}

}