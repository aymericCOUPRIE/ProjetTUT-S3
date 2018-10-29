package moteur;

public class Pion extends Jeton {
	String nom;
	Orientation regard;

	public Pion(String nom,Orientation regard) {
		this.nom = nom;
		this.regard = regard;
	}

	@Override
	public String toString() {
		return "Je suis " + nom;
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

}
