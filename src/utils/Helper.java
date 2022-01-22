package utils;

import java.util.ArrayList;
import java.util.Random;

import app.AnimPanel;
import person.Person;

// klasa pomocnicza 
public class Helper {

	/*
	 * statyczna metoda ktĂłra odpowiada za sprawdzanie kolizji odpala siÄ™ w
	 * przypadku gdy obiekt byĹ‚ lekarzem albo chorym Math.abs() oblicza wartoĹ›Ä‡
	 * bezwzglÄ™dnÄ… z rĂłĹĽnicy wspĂłĹ‚rzÄ™dnych obiektĂłw jeĹĽeli doktor spotkaĹ‚
	 * chorego to go leczy jeĹĽeli chory spotkaĹ‚ zdrowego to go zaraĹĽa
	 */
	private static Random RND = new Random();
	
	public static void isColliding(Person thePerson) {
		ArrayList<Person> copy = new ArrayList<>(AnimPanel.getPeople());

		for (Person p : copy) {
			if (!p.equals(thePerson)
					&& Math.abs(p.getX() - thePerson.getX()) < 10
					&& Math.abs(p.getY() - thePerson.getY()) < 10){
				
				if (thePerson.isDoctor()) {
					if (p.isInfected() && RND.nextDouble() > 0.4) {
						p.setInfected(false);
					}
				}
				
				if (thePerson.isInfected()) {
					if (!p.isDoctor() && !p.isImmune()) {
						p.setInfected(true);
					}
				}
			}
		}
	}
}
