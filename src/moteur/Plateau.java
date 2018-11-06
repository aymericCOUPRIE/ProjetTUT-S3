package moteur;

public class Plateau {

	Jeton[][] plateau;
	int taillePlateau;

	public Plateau(int taillePlateau) {
		super();
		plateau = new Jeton[taillePlateau + 2][taillePlateau + 2];
		this.taillePlateau = taillePlateau;
		remplissageOut();
	}

	public void remplissageOut() {
		for (int i = 0; i < taillePlateau + 2; i++) {

			plateau[0][i] = new Out();

			plateau[i][0] = new Out();

			plateau[taillePlateau + 1][i] = new Out();

			plateau[i][taillePlateau + 1] = new Out();

		}
	}
	public void ajouterRocher(Rocher unRocher,int x,int y) {
		plateau[x][y] = unRocher;
	}

	public void ajouterPion(Jeton unJeton, int x, int y, Orientation uneOrientation) {
		if (x == 0 || y == 0 || x == taillePlateau + 1 || y == taillePlateau - 1) {
			plateau[x][y] = unJeton;
			deplacement(unJeton, uneOrientation);
		} else {
			System.err.println("le pion n'est pas placé a l'extérieur du plateau");
		}

	}

	public void afficherPlateau() {
		System.out.println("\n\nVoici le plateau :");
		for (int i = 0; i < taillePlateau + 2; i++) {
			for (int j = 0; j < taillePlateau + 2; j++) {
				if (plateau[i][j] != null && !(plateau[i][j] instanceof Out)) {
					System.out.println(plateau[i][j] + " à la position " + i + " " + j);
				}

			}
		}
	}

	public void suppresionPionDehors() {
		for (int i = 0; i < taillePlateau + 2; i++) {
			if (plateau[0][i] != null) {
				sortiePlateau(plateau[0][i]);
			}
			if (plateau[i][0] != null) {
				sortiePlateau(plateau[i][0]);
			}
			if (plateau[taillePlateau + 1][i] != null) {
				sortiePlateau(plateau[taillePlateau + 1][i]);
			}
			if (plateau[i][taillePlateau + 1] != null) {
				sortiePlateau(plateau[i][taillePlateau + 1]);
			}
		}
		remplissageOut();
	}

	public void deplacement(Jeton unJeton, Orientation newPosition) {
		// On recupere les coordonne du jeton
		int[] coordonne = recherchePosition(unJeton);
		int x = coordonne[0], y = coordonne[1];
		// Variable qui vas servir a vérifier si le déplacment est possible
		int contreAttaque = 1;

		// deplacemnt vers le nord
		if (newPosition == Orientation.EST) {
			int i = x;
			i++;
			// tant qu'il y a un jeton est qu'on ne déplace pas la taille du plateau
			while (i < taillePlateau + 1) {
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
			// vérification si la contre attaque permet le déplacement
			// si elle est inférieur à 0 c'est impossible
			if (deplacementPossible(contreAttaque)) {
				pousserEst(unJeton, i - 1, y);
			}
		}
		// deplacemnt vers le sud
		if (newPosition == Orientation.OUEST) {
			int i = x;
			i--;
			while (i >= 1) {
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(newPosition);
					i--;
				} else {
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				pousserOuest(unJeton, i + 1, y);
			}

		}
		// deplacement vers le ouest
		if (newPosition == Orientation.SUD) {
			int i = y;
			i--;
			while (i >= 1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(newPosition);
					i--;
				} else {
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				pousserSud(unJeton, x, i + 1);
			}
		}
		// deplacement vers le nord
		if (newPosition == Orientation.NORD) {
			int i = y;
			i++;
			while (i < taillePlateau+1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(newPosition);
					i++;
				} else {

					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				pousserNord(unJeton, x, i - 1);
			}
		}

	}

// fonction pousser
	public void pousserEst(Jeton unJeton, int xMax, int y) {
		while (plateau[xMax][y] != unJeton) {
			plateau[xMax + 1][y] = plateau[xMax][y];
			xMax--;
		}
		plateau[xMax + 1][y] = plateau[xMax][y];
		plateau[xMax][y] = null;

	}

	public void pousserOuest(Jeton unJeton, int xMin, int y) {
		while (plateau[xMin][y] != unJeton) {
			plateau[xMin - 1][y] = plateau[xMin][y];
			xMin++;
		}
		plateau[xMin - 1][y] = plateau[xMin][y];
		plateau[xMin][y] = null;

	}

	public void pousserNord(Jeton unJeton, int x, int yMax) {
		while (plateau[x][yMax] != unJeton) {
			plateau[x][yMax + 1] = plateau[x][yMax];
			yMax--;
		}
		plateau[x][yMax + 1] = plateau[x][yMax];
		plateau[x][yMax] = null;
	}

	public void pousserSud(Jeton unJeton, int x, int yMin) {
		while (plateau[x][yMin] != unJeton) {
			plateau[x][yMin - 1] = plateau[x][yMin];
			yMin++;
		}
		plateau[x][yMin - 1] = plateau[x][yMin];
		plateau[x][yMin] = null;
	}

	public boolean deplacementPossible(int contre) {
		if (contre >= 0) {
			return true;
		} else {
			System.out.println("Déplacement impossible car contre = " + contre);
			return false;
		}
	}

	public void sortiePlateau(Jeton unJeton) {
		if (unJeton instanceof Rocher) {
			System.out.println("Vous avez gg");
		} else {
			System.out.println("vous recpere un pion dans votre inventaire"+unJeton);
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
