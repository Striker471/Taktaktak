package person;

/*
 * AKowalczyk 15.01.2022: 	Moim zdaniem lepiej przekazać wymiary window.HEIGHT, WIDTH. Gdyby ktoś chciał
 * 							wykorzystać klasę Person ale nie koniecznie z klasą window lub tym bardziej
 * 							pakietem app. Można podawać je przy tworzeniu obiektu ale ustawić żeby tylko
 * 							przy pierwszym odpaleniu konstruktora się zapisywały.
 *
 * 							Alternatywnie można też przy pierwszym odpaleniu konstruktora zapytać klase
 * 							wywołującą konstruktor o podanie wymiarów jeśli któś wie jak to zrobić
 *
 * Zmiany: 	> zakomentowanie linijek "import static app.Window.HEIGHT" i analogicznej dla WIDTH
 * 			> dodanie zmiennych statycznych WIDTH i HEIGHT oraz ich inicjalizacji w konstruktorach
 * 			> konstruktor z podaną pozycją wywołuje konstruktora z losową a nie na odwrót
 */

/*
import static app.Window.HEIGHT;
import static app.Window.WIDTH;
*/

import java.util.Random;

public abstract class Person {	// musi rozszerzac jakas klase od ksztaltu
	
	private static final int MARGIN = 10;	// margines spawnowania
	private static final Random RND = new Random();

	private static int HEIGHT;
	private static int WIDTH;
	private static boolean czyIstnieje = false;


	private double x;	// obecny X
	private double y;	// obecny Y
	private double vx;	// wektor X
	private double vy;	// wektor Y
	private boolean infected = false;	// informacja czy jest zarazony
	private boolean vaxxed = false;		// informacja czy jest zaszczepiony
	private final boolean doctor;		// informacja czy jest doktorem
	
	/* konstruktor z losowa pozycja */
	public Person(boolean doctor, int w, int h) {
		this.doctor = doctor;

		if(!czyIstnieje){
			WIDTH=w;
			HEIGHT=h;
			czyIstnieje=true;
		}

		x=RND.nextInt(WIDTH - 2 * MARGIN) + MARGIN;
		y=RND.nextInt(HEIGHT - 2 * MARGIN) + MARGIN;

		/* poczatkowy wektor: + albo - (przy kolizji z krawedzia okna bedzie zmienial znak) */
		this.vx = RND.nextBoolean() ? 4 : -4;
		this.vy = RND.nextBoolean() ? 4 : -4;

		/*
		 * Ustawienie x/y i koloru w metodach Swinga
		 */

	}

	
	/* Konstruktor z podaną pozycją */
	public Person(double x, double y, boolean doctor, int w, int h) {
		this(doctor, w, h);
		this.x = x;
		this.y = y;
	}

	public void setInfected(boolean val) {
		this.infected = val;
		// zmiana koloru metoda Swinga
	}
	
	public void setVaxxed(boolean val) {
		this.vaxxed = val;
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
