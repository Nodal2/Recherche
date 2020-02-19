package modele;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RechercheBooleen {
	
	public static Set<Document> documents = new HashSet<Document>();
	public static Map<String, Set<Document>> termes;
	
	
	public static Set<Document> rechercheBooleen(List<ParametreRechercheBoolIndexInv> parametres) {
		Set<Document> docsRecherche = new HashSet<Document>();
		docsRecherche.addAll(documents);
		
		Set<Document> setDocsParMotParametre = new HashSet<Document>();
		for (ParametreRechercheBoolIndexInv parametre : parametres) {

			//On modifie la liste docsRecherche en fonction des mots et des options qui leur sont liee
			setDocsParMotParametre = termes.get(parametre.getMotRecherche());
			
			switch (parametre.getOption()) {
		
			case "and":
				docsRecherche.retainAll(setDocsParMotParametre);
				break;

			case "negate":
				docsRecherche.removeAll(setDocsParMotParametre);
				break;

			case "or" :
				
			default :
				docsRecherche.addAll(setDocsParMotParametre);
				break;
			}
		}
		
		return docsRecherche;
	}
	
}
