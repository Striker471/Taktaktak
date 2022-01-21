package utils;

import java.util.concurrent.locks.ReentrantLock;

import app.AnimPanel;
import person.Person;

// klasa pomocnicza 
public class Helper {
	
	/*
		statyczna metoda która odpowiada za sprawdzanie kolizji
		odpala się w przypadku gdy obiekt był lekarzem albo chorym 
		Math.abs() oblicza wartość bezwzględną z różnicy współrzędnych obiektów 
		jeżeli doktor spotkał chorego to go leczy
		jeżeli chory spotkał zdrowego to go zaraża 
	 */
	
	public static void isColliding(Person thePerson) {


		for (Person p : AnimPanel.getPeople()) {

			if (!p.equals(thePerson) && Math.abs(p.getX() - thePerson.getX()) < 10 && Math.abs(p.getY() - thePerson.getY()) < 10) {
				if(thePerson.isDoctor()) {
					if(p.isInfected()) p.setInfected(false);;
				}
				else if (thePerson.isInfected()) {
					if(!p.isDoctor()) {
						p.setInfected(true);
					}
				}
			}
		}

	}
}
