package moteur;

public enum Orientation {
	
	NORD,
	SUD,
	OUEST,
	EST;
	
	
	public Orientation oppose() {
		if(this==NORD) {
			return SUD;
		}
		if(this==SUD) {
			return NORD;
		}
		if(this==OUEST) {
			return EST;
		}
		if(this==EST) {
			return OUEST;
		}
		return null;
	}

}
