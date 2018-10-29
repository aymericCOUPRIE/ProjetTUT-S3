package moteur;

public class Plateau {

	Jeton[][] plateau;
	int taillePlateau;

	public Plateau(int taillePlateau) {
		super();
		plateau = new Jeton[taillePlateau][taillePlateau];
		this.taillePlateau = taillePlateau;
	}

	public void ajouterJeton(Jeton unJeton, int x, int y) {

		plateau[x][y] = unJeton;
	}

	public void afficherPlateau() {
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				if (plateau[i][j] != null) {
					System.out.println(plateau[i][j] + " � la position " + i + " " + j);
				}

			}
		}
	}

	public void deplacement(Jeton unJeton, Orientation newPosition) {
		// On recupere les coordonne du jeton
		int[] coordonne = recherchePosition(unJeton);
		int x = coordonne[0], y = coordonne[1];
		// Variable qui vas servir a v�rifier si le d�placment est possible
		int contreAttaque = 1;

		// deplacemnt vers le nord
		if (newPosition == Orientation.EST) {
			int i = x;
			i++;
			// tant qu'il y a un jeton est qu'on ne d�place pas la taille du plateau
			while (i < taillePlateau) {

				// additionne la valeur de retour du jeton
				// 0 si il n'a pas d'impacte 1 si il est dans notre sens et -1 si il est contre
				// nous
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(newPosition);
					i++;
				} else {
					break;
				}
			}
			// v�rification si la contre attaque permet le d�placement
			// si elle est inf�rieur � 0 c'est impossible
			if (deplacementPossible(contreAttaque)) {
				pousserEst(unJeton, i-1, y);
			}
		}
		// deplacemnt vers le sud
		if (newPosition == Orientation.OUEST) {
			int i = x;
			i--;
			while (i >= 0) {
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(newPosition);
					i--;
				} else {
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				plateau[x - 1][y] = plateau[x][y];
				plateau[x][y] = null;
			}

		}
		// deplacement vers le ouest
		if (newPosition == Orientation.SUD) {
			int i = y;
			i--;
			while (i >= 0) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(newPosition);
					i--;
				} else {
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				plateau[x][y - 1] = plateau[x][y];
				plateau[x][y] = null;
			}
		}
		// deplacement vers le nord
		if (newPosition == Orientation.NORD) {
			int i = y;
			i++;
			while (i < taillePlateau) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(newPosition);
					i++;
				} else {
					
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				plateau[x][y + 1] = plateau[x][y];
				plateau[x][y] = null;
			}
		}

	}
// fonction pousser
	public void pousserEst(Jeton unJeton, int xMax, int y) {
		System.out.println("je suis xMax "+xMax);
		if (xMax >= taillePlateau-1) {
			System.out.println("il faudras r�cup�re le pion qui en dehors du plateau");
			xMax--;
		}

		while (plateau[xMax][y] != unJeton) {
			plateau[xMax + 1][y] = plateau[xMax][y];
			System.out.println("je d�place " + plateau[xMax][y] + "� la position " + (xMax + 1) + " " + y);
			xMax--;
		}
		plateau[xMax][y] = null;

	}

	public boolean deplacementPossible(int contre) {
		if (contre >= 0) {
			return true;
		} else {
			System.out.println("D�placement impossible car contre = " + contre);
			return false;
		}
	}

	public int[] recherchePosition(Jeton unJeton) {
		int[] coordonne = new int[2];
		for (int x = 0; x < taillePlateau; x++) {
			for (int y = 0; y < taillePlateau; y++) {
				if (plateau[x][y] == unJeton) {
					coordonne[0] = x;
					coordonne[1] = y;
					return coordonne;
				}
			}
		}
		return coordonne;
	}

}
