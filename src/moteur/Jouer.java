package moteur;

public class Jouer {

	public static void main(String[] args) {
		Plateau unPlateau = new Plateau(5);
		Pion elephant = new Pion("elephant",Orientation.EST);
		//Pion rhinoceros = new Pion("rhino",Orientation.NORD);
		Pion rhinoceros2 = new Pion("rhino adulte",Orientation.NORD);
		Rocher unRocher = new Rocher("cahioux");
		unPlateau.ajouterJeton(elephant, 1, 1);
		//unPlateau.ajouterJeton(rhinoceros, 1,0);
		unPlateau.ajouterJeton(rhinoceros2, 3,1);
		unPlateau.ajouterJeton(unRocher, 2, 1);
		unPlateau.afficherPlateau();
		System.out.println("\n\n");
		unPlateau.deplacement(elephant,Orientation.EST);
		unPlateau.afficherPlateau();
		
	}

}
