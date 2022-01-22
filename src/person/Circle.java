package person;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public abstract class Circle implements Runnable, ActionListener {
	protected AffineTransform affineTransform; // przeksztalcenie obiektu
	protected Graphics2D g2DBuffer;
	protected Shape shape;

	// wspolny bufor
	public int speed = 2;
	protected Area area;

	// Swing nie tworzy okna o zadanych wymiarach, to są realne
	final private int WIDTH = 984;
	final private int HEIGHT = 645;

	// private int radius;
	private static final int MARGIN = 20; // margines spawnowania

	private static final Random RND = new Random();
	private int vx, vy; // wektory
	private int x, y; // wartość obecnego x i y obiektu

	public Circle(Graphics2D buffer) {
		/* TO WSZYSTKO IMO DO KLASY PERSON */
		g2DBuffer = buffer;

		x = RND.nextInt(WIDTH - 3*MARGIN) + MARGIN;
		y = RND.nextInt(HEIGHT - 3*MARGIN) + MARGIN;

		shape = new Ellipse2D.Double(x, y, 10, 10);

		vx = RND.nextBoolean() ? RND.nextInt(speed) + 1 : RND.nextInt(speed) + -speed;
		vy = RND.nextBoolean() ? RND.nextInt(speed) + 1 : RND.nextInt(speed) + -speed;
	}

	protected Shape nextFrame() {
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2; 	// X srodka
		int cy = bounds.y + bounds.height / 2; 	// Y srodka

		// odbicie
		if (cx <= 5 || cx >= WIDTH - 5) {
			vx = -vx;
		}
		if (cy <= 5 || cy >= HEIGHT - 5) {
			vy = -vy;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
