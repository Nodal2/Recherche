package modele;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
