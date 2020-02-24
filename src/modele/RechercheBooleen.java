package modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RechercheBooleen {
	
	public static Set<Document> documents = new HashSet<Document>();
	public static Map<String, Set<Document>> termes;
	
	
	public static Set<Document> rechercheBooleen(String requete) {
		Set<Document> docsRecherche = new HashSet<Document>();
		docsRecherche.addAll(documents);
		
		List<String> motRequete = Outils.normalizeForBoolean(requete);
		
		if(motRequete.size() == 1) {
			if(motRequete.get(0).equals("and") || motRequete.get(0).equals("or") || motRequete.get(0).equals("not")) {
				return docsRecherche;
			}else {
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
			}
		}
		
		else if(motRequete.size() == 2) {
			if(motRequete.get(0).equals("not")) {
				docsRecherche.removeAll(termes.get(motRequete.get(1)));
			}
		
		}else if(motRequete.size() == 3){
			switch (motRequete.get(1)) {
			case "and":
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
				docsRecherche.retainAll(termes.get(motRequete.get(2)));
				break;
				
			case "or":
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
				docsRecherche.addAll(termes.get(motRequete.get(2)));
				break;
				
			case "not":
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
				docsRecherche.removeAll(termes.get(motRequete.get(2)));
				break;
			}
		}
		
		return docsRecherche;
	}
	
}
