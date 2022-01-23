package person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import static java.lang.Math.abs;

import app.AnimPanel;
import utils.Helper;

public class Person implements Runnable, ActionListener {

	private boolean immune = false;
	private boolean infected = false; // informacja czy jest zarazony
	private boolean vaxxed = false; // informacja czy jest zaszczepiony
	private final boolean doctor; // informacja czy jest doktorem
	public static boolean startVaxx; // informacja czy zaczeto szczepienia
	private Color color = PersonColors.NORMAL;
	protected AffineTransform affineTransform; // przeksztalcenie obiektu
	protected Graphics2D g2DBuffer;
	protected Shape shape;
	protected Area area;

	public int speed = 2; // wspolny bufor

	// Swing nie tworzy okna o zadanych wymiarach, to są realne
	final private int WIDTH = 984;
	final private int HEIGHT = 645;

	private static final int MARGIN = 20; // margines spawnowania

	private static final Random RND = new Random();
	private int vx, vy; // wektory
	private int x, y; // wartość obecnego x i y obiektu
	private int maxiV = 4;
	private int miniV = 3;


	/* pelny konstruktor */
	public Person(Graphics2D buffer, boolean isADoctor) {
		g2DBuffer = buffer;

		x = RND.nextInt(WIDTH - 3*MARGIN) + MARGIN;
		y = RND.nextInt(HEIGHT - 3*MARGIN) + MARGIN;

		shape = new Ellipse2D.Double(x, y, 10, 10);
		affineTransform = new AffineTransform();
		area = new Area(shape);

		vx = RND.nextBoolean() ? RND.nextInt(speed) + 1 : RND.nextInt(speed) + -speed;
		vy = RND.nextBoolean() ? RND.nextInt(speed) + 1 : RND.nextInt(speed) + -speed;

		doctor = isADoctor;
		if (isADoctor) {
			color = PersonColors.DOCTOR;
		}
	}

	public void setInfected(boolean val) {
		this.infected = val;

		if (!val) {
			maxiV = 4;
			miniV = 3;

			this.color = PersonColors.NORMAL;
			this.immune = true;
		} else {
			maxiV = 3;
			miniV = 2;

			this.color = PersonColors.INFECTED;
		}
	}


	public void setVaxxed(boolean val) {
		maxiV=RND.nextInt(6-3)+3; //jesli dobrze przyjmie szczepionke to jest szybszy jesli zle to wolniejszy
		miniV = maxiV - 2;
		if(miniV<2) miniV =2;

		this.vaxxed = val;
		this.color = PersonColors.VAXXED;
	}


	protected Shape nextFrame() {
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2; 	// X srodka
		int cy = bounds.y + bounds.height / 2; 	// Y srodka

		// odbicie
		if (cx <= 5 || cx >= WIDTH - 5) {
			vx = -vx/abs(vx);
			vx = vx *(RND.nextInt(maxiV-miniV) + miniV);
		}
		if (cy <= 5 || cy >= HEIGHT - 5) {
			vy = -vy/abs(vy);
			vy = vy * (RND.nextInt(maxiV-miniV) + miniV);
		}

		this.x += vx;
		this.y += vy;

		affineTransform = new AffineTransform();
		affineTransform.translate(vx, vy);

		// przeksztalcenie obiektu
		area.transform(affineTransform);
		return area;
	}

	public Rectangle getBounds() {
		return shape.getBounds();
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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