package moteur;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private List<Pion> lesPions;
	private String nom;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		lesPions = new ArrayList<Pion>();
		creationPion();
	}
	
	public void creationPion() {
		for(int i=0;i<5;i++) {
			Pion unPion = new Pion(nom, null);
			lesPions.add(unPion);
		}
	}
	
	public void recuperPion(Pion unPion) {
		lesPions.add(unPion);
	}
	
	public Pion sortirPion() {
		if(lesPions.size()==0) {
			System.err.println("Vous n'avez plus de pion");
			return null;
		}else {
			Pion unPion=lesPions.get(0);
			lesPions.remove(0);
			return unPion;
		}
	}
	
	
}
