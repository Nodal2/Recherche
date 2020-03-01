package modele;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RechercheBooleen {

	public static Set<Document> documents = new HashSet<Document>();


	public static TreeSet<Document> rechercheBooleen(String requete) {
		Map<String, Set<Document>> termes = Crawler.indexInv.getTermes();
		Set<Document> docsRecherche = new HashSet<>();
		TreeSet<Document> docsResultat = new TreeSet<>();
		docsRecherche.addAll(documents);

		List<String> motRequete = Outils.normalizeForBoolean(requete);

		if(motRequete.size() == 1) {
			if(motRequete.get(0).equals("and") || motRequete.get(0).equals("or") || motRequete.get(0).equals("not")) {
				docsResultat.addAll(docsRecherche);
				return docsResultat;
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
				for (Document doc : docsRecherche) {
					if(doc.getMapMots().get(motRequete.get(0)).getFrequence() <=
							doc.getMapMots().get(motRequete.get(2)).getFrequence()) {
						doc.setScore(doc.getMapMots().get(motRequete.get(0)).getFrequence());
					}
					else {
						doc.setScore(doc.getMapMots().get(motRequete.get(2)).getFrequence());
					}
				}
				break;

			case "or":
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
				docsRecherche.addAll(termes.get(motRequete.get(2)));
				for (Document doc : docsRecherche) {
					double score = doc.getMapMots().get(motRequete.get(0)) != null && 
							doc.getMapMots().get(motRequete.get(2)) != null ? 
									doc.getMapMots().get(motRequete.get(0)).getFrequence() >=
									doc.getMapMots().get(motRequete.get(2)).getFrequence() ? 
									doc.getMapMots().get(motRequete.get(0)).getFrequence() : 
									doc.getMapMots().get(motRequete.get(2)).getFrequence() : 
									doc.getMapMots().get(motRequete.get(0)) == null ?
									doc.getMapMots().get(motRequete.get(2)).getFrequence() :
									doc.getMapMots().get(motRequete.get(0)).getFrequence();
					doc.setScore(score);

				}
				break;

			case "not":
				docsRecherche.retainAll(termes.get(motRequete.get(0)));
				docsRecherche.removeAll(termes.get(motRequete.get(2)));
				break;
			}
		}
		docsResultat.addAll(docsRecherche);
		return docsResultat;
	}

}
