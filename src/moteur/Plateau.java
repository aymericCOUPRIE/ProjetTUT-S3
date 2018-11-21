package moteur;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;


public class Plateau {

	Jeton[][] plateau;
	int taillePlateau;
	Pion pionARecupere;
	boolean finJeu;

	public Plateau(int taillePlateau) {
		super();
		finJeu = false;
		plateau = new Jeton[taillePlateau + 2][taillePlateau + 2];
		this.taillePlateau = taillePlateau;
		remplissageOut();
	}
//	public Plateau () throws ParserConfigurationException, SAXException, IOException {
//		load("data/plateauX.xml");
//	}

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

//	public void ajouterPion(Jeton unJeton, int x, int y, Orientation uneOrientation) {
//	
//		if (x == 0 || y == 0 || x == taillePlateau + 1 || y == taillePlateau - 1) {
//			plateau[x][y] = unJeton;
//			deplacement(unJeton, uneOrientation);
//		} else {
//			System.err.println("le pion n'est pas placé a l'extérieur du plateau");
//		}
//
//	}
	
	//pour ajouter un pion on doit le mettre au coordoner que le joueur voie
	
	//on est obligé de mettre le pion sur un extérieur du plateau car sinon il y auras une erreur
	
	public void ajouterPion(Jeton unJeton, int x, int y) {
		if(x<taillePlateau || y<taillePlateau) {
			if(x==0) {
				plateau[x][y+1]=unJeton;
				deplacement(unJeton, Orientation.EST);
			}else if(x==taillePlateau) {
				plateau[x+2][y+1]=unJeton;
				deplacement(unJeton, Orientation.OUEST);
			}else if(y==0) {
				plateau[x+1][y]=unJeton;
				deplacement(unJeton, Orientation.NORD);
			}else if(y==taillePlateau) {
				plateau[x+1][y+2]=unJeton;
				deplacement(unJeton, Orientation.SUD);
			}else {
				System.err.println("Vous devez placer votre pion sur le rebord du plateau");
			}
			
		}else {
			System.err.println("Vous devez placer votre pion dans le plateau");
		}
	}
	

	public void afficherPlateauVisionDev() {
		System.out.println("\n\nVoici le plateau :");
		for (int i = 0; i < taillePlateau + 2; i++) {
			for (int j = 0; j < taillePlateau + 2; j++) {
				if (plateau[i][j] != null && !(plateau[i][j] instanceof Out)) {
					System.out.println(plateau[i][j] + " à la position " + i + " " + j);
				}
			}
		}
	}
	public void afficherPlateauVisionJoueur() {
		System.out.println("\n\nVoici le plateau :");
		for (int i = 1; i < taillePlateau + 1; i++) {
			for (int j = 1; j < taillePlateau + 1; j++) {
				if (plateau[i][j] != null && !(plateau[i][j] instanceof Out)) {
					System.out.println(plateau[i][j] + " à la position " + (i-1) + " " + (j-1));
				}
			}
		}
	}
	
	public void afficherPlateauVide() {
		System.out.println("\n\nVoici le plateau :");
		for (int i = 0; i < taillePlateau + 2; i++) {
			for (int j = 0; j < taillePlateau + 2; j++) {
				if (plateau[i][j] == null) {
					System.out.println(plateau[i][j] + " à la position " + i + " " + j);
				}else if ((plateau[i][j] instanceof Out)) {
					System.out.println(plateau[i][j] + " à la position " + i + " " + j);
				}else if ((plateau[i][j] instanceof Rocher)) {
					System.out.println(plateau[i][j] + " à la position " + i + " " + j);
				}
			}
		}
	}
	
	//Fonction qui sert pour le déplacement d'un pion en récupérant le pion sur le plateau si il existe
	public Pion recuperePion(int x,int y) {
		if(plateau[x+1][y+1] instanceof Pion) {
			return (Pion) plateau[x+1][y+1];
		}		
		return null;
	}

	public void suppresionPionDehors() {
		for (int i = 0; i < taillePlateau + 2; i++) {
			if (plateau[0][i] != null && !(plateau[0][i] instanceof Out)) {
				sortiePlateau(plateau[0][i]);
			}
			if (plateau[i][0] != null && !(plateau[i][0] instanceof Out) ) {
				sortiePlateau(plateau[i][0]);
			}
			if (plateau[taillePlateau + 1][i] != null && !(plateau[taillePlateau + 1][i] instanceof Out) ) {
				sortiePlateau(plateau[taillePlateau + 1][i]);
			}
			if (plateau[i][taillePlateau + 1] != null && !(plateau[i][taillePlateau + 1] instanceof Out) ) {
				sortiePlateau(plateau[i][taillePlateau + 1]);
			}
		}
		remplissageOut();
	}
	
	
	// cette fonction est un boolean qui retourn true si il y a eu un deplacement et false sinon
	public boolean deplacement(Jeton unJeton, Orientation directionDeplacement) {
		pionARecupere = null;
		// On recupere les coordonne du jeton
		int[] coordonne = recherchePosition(unJeton);
		int x = coordonne[0], y = coordonne[1];
		// Variable qui vas servir a vérifier si le déplacment est possible
		int contreAttaque = 1;
		// Si le pion a deplacer des pion la cariable passe a true
		boolean aPousser = false;

		// deplacemnt vers le nord
		if (directionDeplacement == Orientation.EST) {
			int i = x;
			i++;
			// tant qu'il y a un jeton et qu'on ne dépace pas la taille du plateau
			while (i < taillePlateau + 1) {
				// additionne la valeur de retour du jeton
				// 0 si il n'a pas d'impacte 1 si il est dans notre sens et -1 si il est contre
				// nous
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
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
		if (directionDeplacement == Orientation.OUEST) {
			int i = x;
			i--;
			while (i >= 1) {
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
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
		if (directionDeplacement == Orientation.SUD) {
			int i = y;
			i--;
			while (i >= 1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
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
		if (directionDeplacement == Orientation.NORD) {
			int i = y;
			i++;
			while (i < taillePlateau+1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
					i++;
				} else {
					break;
				}
			}
			if (deplacementPossible(contreAttaque)) {
				pousserNord(unJeton, x, i - 1);
			}
		}
		suppresionPionDehors();
		return aPousser;
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
			finJeu = true;
		} else {
			pionARecupere=(Pion)unJeton;
			System.out.println("vous recupere un pion dans votre inventaire"+unJeton);
		}
	}
	
	public boolean isFinJeu() {
		return finJeu;
	}
	
	public Pion recuperePionDehorsPlateau() {
		if(pionARecupere!=null) {
			return pionARecupere;
		}else {
			return null;
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
	
	// Sauvegarde des plateaux dans un fichier XML
		// filename : nom du fichier
		// comment : commentaire
//	public void save(String filename, String comment) throws ParserConfigurationException {
//		// On crée un nouveau Document JDOM
//		Document document = new Document();
//
//		// on set la racine du document avec l'élément meilleurScore
//		Element racine = new Element("Plateau");
//		document.setRootElement(racine);
//		
//		Attribute taille = new Attribute ("taille",""+taillePlateau);
//		racine.setAttribute(taille);
//
//		for (int x = 0; x < taillePlateau+2; x++) {
//			for (int y = 0; y < taillePlateau+2; y++) {
//
//			Element uneCase = new Element("Case");
//			racine.addContent(uneCase);
//			
//			if (plateau[x][y] instanceof Out) {
//				Attribute type1 = new Attribute("type","Out");
//			    uneCase.setAttribute(type1);
//			}else if(plateau[x][y] instanceof Rocher) {
//				Attribute type2 = new Attribute("type","Rocher");
//			    uneCase.setAttribute(type2);
//			}else{
//				Attribute type3 = new Attribute("type","In");
//			    uneCase.setAttribute(type3);
//			}
//			
//		      
//			Element horizontale = new Element("X");// balise valeur du score (nb de tentative)
//			uneCase.addContent(horizontale);
//			horizontale.setText(""+x);// on remplis les elements(balises) avec le contenu de la
//															// liste
//
//			Element verticale = new Element("Y");// balise pour savoir qui la réalisé
//			uneCase.addContent(verticale);
//			verticale.setText(""+y);
//			
//
//			}
//		}
//
//		try
//
//		{
//			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
//			sortie.output(document, new FileWriter(filename));
//		} catch (IOException e) {
//		}
//	}
//	// Chargement des plateaux depuis un fichier XML
//		// filename : nom du fichier
//		public void load(String filename) throws ParserConfigurationException, SAXException, IOException {
//			// permet la création d'un fichier pour extraire les données du fichier xml
//			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
//			org.w3c.dom.Document domDocument = documentBuilder.parse(filename);
//			DOMBuilder domBuilder = new DOMBuilder();
//			Document doc = domBuilder.build(domDocument);
//
//			// Element racine = doc.getRootElement();
//			// on recupère les fils de case a patir de la racine plateau
//			List<Element> attribElments = doc.getRootElement().getChildren("Case");
//			this.taillePlateau = Integer.parseInt(doc.getRootElement().getAttributeValue("taille")) ;
//			plateau = new Jeton[taillePlateau +2][taillePlateau+2];
//			// pour chaque case du fichier xml on écrit la case avec un x , un y et en focntion de ses attribut in , out , rien 
//			for (Element uneCase : attribElments) {
//				int x = Integer.parseInt(uneCase.getChildText("X")); 
//					int y = Integer.parseInt(uneCase.getChildText("Y")); 
//				if (uneCase.getAttributeValue("type").equals("Out")) {
//					plateau [x][y] = new Out();
//				}else if (uneCase.getAttributeValue("type").equals("In")){
//					plateau [x][y] = null;
//				}else if (uneCase.getAttributeValue("type").equals("Rocher")) {
//					int id = 1 ;
//					Rocher unRocher = new Rocher("cailloux "+id);
//					ajouterRocher(unRocher, x ,y);
//					id++;
//				
//				}
//			}
//
//		}
//	
//	
}
