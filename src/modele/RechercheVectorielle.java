package modele;

import java.util.HashMap;

public class RechercheVectorielle{
	public static HashMap<Long, Double> resultatMap = new HashMap<>();
	
	public static void rechercher(Document requete) {
		for(Document doc : Crawler.index.getDoc()) {
			double similarite = Outils.similarite(requete.getMapMots(), doc.getMapMots());
			doc.setScore(similarite);
			resultatMap.put(doc.getId(), doc.getScore());
		}
	}
}
