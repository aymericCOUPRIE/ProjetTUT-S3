package moteur;

public class Jouer {

	public static void main(String[] args) {
		Plateau unPlateau = new Plateau(5);
		Pion elephant = new Pion("elephant",Orientation.NORD);
		Pion rhinoceros = new Pion("rhino",Orientation.SUD);
		Pion rhinoceros2 = new Pion("rhino adulte",Orientation.SUD);
		Rocher unRocher = new Rocher("cahioux");
		unPlateau.ajouterJeton(elephant, 2, 2);
		//unPlateau.ajouterJeton(rhinoceros, 1,0);
		unPlateau.ajouterJeton(rhinoceros2, 2,3);
		unPlateau.ajouterJeton(unRocher, 2, 4);
		unPlateau.afficherPlateau();
		System.out.println("\n\n");
		unPlateau.deplacement(elephant,Orientation.NORD);
		unPlateau.afficherPlateau();
		
	}

}
