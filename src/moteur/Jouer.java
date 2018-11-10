package moteur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Jouer {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		Plateau unPlateau = new Plateau(5);
		Plateau unPlateau2 = new Plateau();
		
//		Pion elephant = new Pion("elephant",Orientation.SUD);
//		//Pion rhinoceros = new Pion("rhino",Orientation.NORD);
//		Pion rhinoceros2 = new Pion("rhino adulte",Orientation.SUD);
		Rocher unRocher = new Rocher("cailloux 1");
		Rocher unRocher2 = new Rocher("cailloux 2");
		Rocher unRocher3 = new Rocher("cailloux 3");
		unPlateau.ajouterRocher(unRocher, 2, 3);
		unPlateau.ajouterRocher(unRocher2, 3, 3);
		unPlateau.ajouterRocher(unRocher3, 4, 3);
//		unPlateau.ajouterPion(elephant, 0, 1,Orientation.EST);
//		unPlateau.afficherPlateau();
//		//unPlateau.ajouterJeton(rhinoceros, 1,0);
//		unPlateau.ajouterPion(rhinoceros2, 1,0,Orientation.NORD);
		unPlateau.afficherPlateauVide();
		unPlateau2.afficherPlateauVide();
//		try {
//			unPlateau.save("data/plateauX.xml", "unittest comment");
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("\n\n");
		
//		//unPlateau.deplacement(elephant,Orientation.NORD);
//		//unPlateau.deplacement(rhinoceros2, Orientation.SUD);
//		//unPlateau.deplacement(rhinoceros2, Orientation.SUD);
//		MoteurJeu unePartie = new MoteurJeu(5);
//		unePartie.jouer();
		
		
		
	}

}
