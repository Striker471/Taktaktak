package person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import app.AnimPanel;

import static app.Window.HEIGHT;
import static app.Window.WIDTH;

public abstract class Circle implements Runnable, ActionListener {

    // wspolny bufor
    protected Graphics2D g2DBuffer;
    protected Area area;

    // do wykreslania
    private final int dWidth;
    private final int dHeight;
    //    private int radius;
    protected Shape shape;
    private static final int MARGIN = 10;    // margines spawnowania

    private static final Random RND = new Random();

    // przeksztalcenie obiektu
    protected AffineTransform affineTransform;

    // przesuniecie
    private int dPosX, dPosY;
    protected final int dDelay;

    // wartość obecnego x i y obiektu 
    private int x, y;


    protected static final Random rand = new Random();


    public Circle(Graphics2D buffer, int delay) {
        this.dWidth = RND.nextInt(WIDTH - 2 * MARGIN) + MARGIN;
        this.dHeight = RND.nextInt(HEIGHT - 2 * MARGIN) + MARGIN;
        this.x = dWidth;
        this.y = dHeight;
        g2DBuffer = buffer;
        dDelay = delay;
        this.shape = new Ellipse2D.Double(dWidth, dHeight, 10, 10);
        dPosX = 1 + rand.nextInt(5);
        dPosY = 1 + rand.nextInt(5);
    }

    public double getdWidth() {
        return dWidth;
    }

    public double getdHeight() {
        return dHeight;
    }


    public Rectangle getBounds() {
        return shape.getBounds();
    }


    protected Shape nextFrame() {
        // zapamietanie na zmiennej tymczasowej
        // aby nie przeszkadzalo w wykreslaniu

        area = new Area(area);
        affineTransform = new AffineTransform();

        Rectangle bounds = area.getBounds();

        int cx = bounds.x + bounds.width / 2;
        int cy = bounds.y + bounds.height / 2;

        // odbicie

        if (cx < 10 || cx > WIDTH - MARGIN - 15)
            dPosX = -dPosX;
        if (cy < 10 || cy > HEIGHT - MARGIN - 70)
            dPosY = -dPosY;


        this.x += dPosX;
        this.y += dPosY;

        affineTransform.translate(dPosX, dPosY);

        // przeksztalcenie obiektu
        area.transform(affineTransform);
        return area;
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
