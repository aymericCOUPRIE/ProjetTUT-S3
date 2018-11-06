package moteur;

public class Jouer {

	public static void main(String[] args) {
		Plateau unPlateau = new Plateau(5);
		Pion elephant = new Pion("elephant",Orientation.SUD);
		//Pion rhinoceros = new Pion("rhino",Orientation.NORD);
		Pion rhinoceros2 = new Pion("rhino adulte",Orientation.SUD);
		Rocher unRocher = new Rocher("cailloux");
		unPlateau.ajouterJeton(elephant, 1, 1);
		//unPlateau.ajouterJeton(rhinoceros, 1,0);
		unPlateau.ajouterJeton(rhinoceros2, 1,3);
		unPlateau.ajouterJeton(unRocher, 1, 2);
		unPlateau.afficherPlateau();
		System.out.println("\n\n");
		//unPlateau.deplacement(elephant,Orientation.NORD);
		unPlateau.deplacement(rhinoceros2, Orientation.SUD);
		unPlateau.deplacement(rhinoceros2, Orientation.SUD);
		unPlateau.afficherPlateau();
		
	}

}
