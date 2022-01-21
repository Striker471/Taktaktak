package person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

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
    private static final int MARGIN = 10;	// margines spawnowania

    private static final Random RND = new Random();

    // przeksztalcenie obiektu
    protected AffineTransform affineTransform;

    // przesuniecie
    private int dPosX, dPosY;
    private final int dDelay;

    protected static final Random rand = new Random();



    public Circle(Graphics2D buffer, int delay) {
        this.dWidth = RND.nextInt(WIDTH-2*MARGIN) + MARGIN;
        this.dHeight = RND.nextInt(HEIGHT-2*MARGIN) + MARGIN;
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

//    public int getRadius() {
//        return radius;
//    }

    public Rectangle getBounds() {
        return shape.getBounds();
    }


    @Override
    public void run() {
        // przesuniecie na srodek
        affineTransform.translate(100, 100);
        area.transform(affineTransform);
        shape = area;

        while (true) {
            // przygotowanie nastepnego kadru
            shape = nextFrame();
            try {
                Thread.sleep(dDelay);
            } catch (InterruptedException e) {
                return ;
            }
        }
    }

    protected Shape nextFrame() {
        // zapamietanie na zmiennej tymczasowej
        // aby nie przeszkadzalo w wykreslaniu
    	System.out.println(this);
        area = new Area(area);
        affineTransform = new AffineTransform();

        Rectangle bounds = area.getBounds();

        int cx = bounds.x + bounds.width / 2;
        int cy = bounds.y + bounds.height / 2;
        
        // odbicie
        System.out.println(dWidth);
        if (cx < 0 || cx > WIDTH-MARGIN) //te wartości można by zweryfikować
            dPosX = -dPosX;
        if (cy < 0 || cy > HEIGHT-MARGIN) //te wartości też można by zweryfikować
            dPosY = -dPosY;

        // konstrukcja przeksztalcenia
//        affineTransform.translate(cx, cy);
//        affineTransform.translate(-cx, -cy);
        affineTransform.translate(dPosX, dPosY);

        // przeksztalcenie obiektu
        area.transform(affineTransform);
        return area;
    }
 
  
}