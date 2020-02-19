package modele;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class RechercheVectorielle{
	public static TreeSet<Document> resultat = new TreeSet<>();
	public static Set<Document> corpus = new HashSet<Document>();
	
	public static void rechercher(Document requete) {
		
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
