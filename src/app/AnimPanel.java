package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import person.Circle;
import person.Person;

public class AnimPanel extends JPanel implements ActionListener {

    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;

    // lista wszystkich obiektów klasy Person
    public static List<Person> people = new ArrayList<>();
    private int delay = 70;

    private int movingSpeed;

    private final Timer timer;

    public AnimPanel(int width, int height) {
        super();

        this.setPreferredSize(new Dimension(width, height));

        displayMainScreenMessage();
        setBackground(Color.GRAY);

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
        for (int i = 0; i < 10; i++) {
            addInfected();
        }
    }

    // Dodawanie zarażonych do listy people i tworzenie nowego wątku
    void addInfected() {
        people.add(new Person(buffer, delay, false));
        ((Person) people.get(people.size() - 1)).setInfected(true);
        timer.addActionListener(people.get(people.size() - 1));
        new Thread(people.get(people.size() - 1)).start();

    }

    // Dodawanie doktora do listy people i tworzenie nowego wątku
    void addDoctor() {
        people.add(new Person(buffer, delay, true));
        timer.addActionListener(people.get(people.size() - 1));
        new Thread(people.get(people.size() - 1)).start();
    }

    // Dodawanie zdrowego do listy people i tworzenie nowego wątku
    void addNormal() {
        people.add(new Person(buffer, delay, false));
        timer.addActionListener(people.get(people.size() - 1));
        new Thread(people.get(people.size() - 1)).start();
    }

    void animate() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    void displayMainScreenMessage() {
        final JLabel simulationNameLabel = new JLabel("<html><center><sup>Virus</sup><br><sup>Spreading</sup><br><sup>Animation</sup></center>");
        simulationNameLabel.setSize(200, 1000);
        simulationNameLabel.setFont(new Font(simulationNameLabel.getFont().getName(), Font.PLAIN, 120));
        this.add(simulationNameLabel);

    }

    protected void setDelay(int delay) {
        this.delay = delay;
    }

    public void setMovingSpeed(int movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }

    //zwracanie listy
    public static List<Person> getPeople() {
        return people;
    }


}
