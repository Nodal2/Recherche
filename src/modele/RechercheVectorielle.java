package modele;

import java.util.Set;
import java.util.TreeSet;

public class RechercheVectorielle{
	public static TreeSet<Document> resultat = new TreeSet<>();
	
	public static void rechercher(Document requete, Set<Document> corpus) {
		
		for(Document doc : corpus) {
			double similarite = Outils.similarite(requete.getMapMots(), doc.getMapMots());
			doc.setScore(similarite);
			resultat.add(doc);
		}
		for(Document doc : resultat) {
			System.out.println("score : "+doc.getScore()+" fichier : "+doc.getNomFichier());
		}
	}
	
	

}
