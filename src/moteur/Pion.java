package moteur;

public class Pion extends Jeton {
	String nom;
	Orientation regard;
	//a supprimer
	int id;

	public Pion(String nom,Orientation regard,int id) {
		this.nom = nom;
		this.regard = regard;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Je suis " + nom+" "+id+" et je regard vers "+regard;
	}

	@Override
	public int veriforientation(Orientation testRegard) {
		if(regard.oppose()==testRegard) {
			return -1;
		}else if(regard==testRegard) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public String getNom() {
		return nom;
	}

}
