package moteur;

public class Jouer {

	public static void main(String[] args) {
		Plateau unPlateau = new Plateau(5);
		Pion elephant = new Pion("elephant",Orientation.NORD);
		Pion rhinoceros = new Pion("rhino",Orientation.NORD);
		Rocher unRocher = new Rocher("cahioux");
		unPlateau.ajouterJeton(elephant, 2, 3);
		unPlateau.ajouterJeton(rhinoceros, 1,0);
		unPlateau.ajouterJeton(unRocher, 3, 4);
		unPlateau.afficherPlateau();
		unPlateau.deplacement(elephant,Orientation.EST);
		unPlateau.afficherPlateau();
		
	}

}
