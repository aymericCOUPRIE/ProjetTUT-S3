package moteur;

import java.util.Scanner;

public class MoteurJeu {

	Plateau lePlateau;
	Joueur joueur1, joueur2;
	boolean fin;

	public MoteurJeu(int taillePlateau) {
		lePlateau = new Plateau(taillePlateau);
		joueur1 = new Joueur("Elephant");
		joueur2 = new Joueur("Rhinoceros");
		fin = false;

	}

	public void jouer() {
		// ajouter aléatoire
		boolean tour = false;
		while (!lePlateau.isFinJeu()) {
			if (!tour) {
				tour = true;
				unTour(joueur1);
			} else {
				tour = false;
				unTour(joueur2);
			}
			lePlateau.afficherPlateauVisionJoueur();
		}
	}
	

	public void unTour(Joueur joueur) {
		System.out.println("\n"
				+ ""+joueur.getNom()+": 1 pour ajouter un Pion 2 pour déplacer un Pion");
		Scanner sc = new Scanner(System.in);
		int reponse = sc.nextInt();
		if (reponse == 1) {
			ajouterPionPlateau(joueur);
		} else if (reponse == 2) {
			deplacerPion(joueur);
		} else {
			System.err.println("error entre texte");
		}

		if (lePlateau.recuperePionDehorsPlateau() != null) {
			if (joueur1.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur1.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			} else if (joueur2.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur2.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			}
		}

	}

	public void ajouterPionPlateau(Joueur unJoueur) {
		Scanner sca = new Scanner(System.in);
		int x, y;
		System.out.println("Quelle coordonne pour le pion ?");
		System.out.println("x = ");
		x = sca.nextInt();
		System.out.println("y = ");
		y = sca.nextInt();
		unJoueur.setRegardProchainPion(choisirRegard());
		lePlateau.ajouterPion(unJoueur.posserPionPlateau(), x, y);
	}
	
	public Orientation choisirDirection() {
		System.out.println("Vers ou voulez vous vous déplacer votre pion 1=Nord, 2=Sud, 3=Est, 4=Ouest");
		return choisirOrientation();
	}
	
	public Orientation choisirRegard() {
		System.out.println("Vers ou voulez vous que regard votre pion 1=Nord, 2=Sud, 3=Est, 4=Ouest");
		return choisirOrientation();
	}

	public Orientation choisirOrientation() {
		Scanner sca = new Scanner(System.in);
		int resultat = sca.nextInt();
		switch (resultat) {
		case 1:
			return Orientation.NORD;
		case 2:
			return Orientation.SUD;
		case 3:
			return Orientation.EST;
		case 4:
			return Orientation.OUEST;
		default:
			return null;

		}
	}

//	public void deplacerPion(Joueur unJoueur) {
//		lePlateau.deplacement(unJoueur.pionDeplacer(), choisirOrientation());
//
//	}
	
	public void deplacerPion(Joueur joueur) {
		Scanner sca = new Scanner(System.in);
		System.out.println("Quelle coordonne pour le pion ?");
		System.out.println("x = ");
		int x = sca.nextInt();
		System.out.println("y = ");
		int y = sca.nextInt();	
		Pion pion =lePlateau.recuperePion(x, y);
		if (pion == null) {
			System.out.println("il n'y pas de pion a cette position ");
			deplacerPion(joueur);
		}else {
			boolean peutPasFaireRotation = lePlateau.deplacement(pion, choisirDirection());
			if(!peutPasFaireRotation) {
				pion.setRegard(choisirRegard());
			}
		}
	}

	public void gagner() {
		fin = true;
		System.out.println("vous avez gagnez");
	}

}
