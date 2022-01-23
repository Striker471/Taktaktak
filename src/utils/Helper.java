package utils;

import java.util.ArrayList;
import java.util.Random;

import app.AnimPanel;
import person.Person;

// klasa pomocnicza 
public class Helper {

	/*
	 statyczna metoda ktora odpowiada za sprawdzanie kolizji odpala sie w
	 * przypadku gdy obiekt byl‚ lekarzem albo chorym Math.abs() oblicza wartosc
	 * bezwzgledna z roznicy wspolrzednych obiektow
	 * jezeli doktor spotkal‚ chorego to ma szanse go uleczyc,
	 * a jezeli spotkal zdrowego, bez odpornosci to go szczepi
	 * jezeli chory spotkal zdrowego, bez odpornosci to go zaraza,
	 * a jezeli spotkal zaszczepionego to ma szanse go zarazic
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
					if(!p.isInfected() && !p.isImmune() && !p.isDoctor() && Person.startVaxx){
						p.setVaxxed(true);
					}
				}
				
				if (thePerson.isInfected() && !p.isDoctor()) {
					if (!p.isImmune() && !p.isVaxxed()){
						p.setInfected(true);
					}
				}
			}
		}
	}
}
