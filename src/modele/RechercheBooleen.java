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
		List<ParametreRechercheBoolIndexInv> parametres = decoupageRequete(requete);
		Set<Document> docsRecherche = new HashSet<Document>();
		docsRecherche.addAll(documents);
		
		Set<Document> setDocsParMotParametre = new HashSet<Document>();
		for (ParametreRechercheBoolIndexInv parametre : parametres) {

			//On modifie la liste docsRecherche en fonction des mots et des options qui leur sont liee
			setDocsParMotParametre = termes.get(parametre.getMotRecherche());
			if(setDocsParMotParametre != null) {
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
		}
		
		return docsRecherche;
	}
	
	private static List<ParametreRechercheBoolIndexInv> decoupageRequete(String requete) {
		List<ParametreRechercheBoolIndexInv> parametres = new ArrayList<ParametreRechercheBoolIndexInv>();
		List<String> motRequete = Outils.normalizeForBoolean(requete);
		
		for(int i = 0; i<motRequete.size(); i++) {
			ParametreRechercheBoolIndexInv newParam;
			
			if(i!=motRequete.size()-1) {
				switch (motRequete.get(i)) {

				case "negate":
					newParam = new ParametreRechercheBoolIndexInv(motRequete.get(i+1), "negate");
					i++;
					break;

				case "and":
					newParam = new ParametreRechercheBoolIndexInv(motRequete.get(i+1), "and");
					i++;
					break;

				case "or":
					newParam = new ParametreRechercheBoolIndexInv(motRequete.get(i+1), "or");
					i++;
					break;

				default:
					newParam = new ParametreRechercheBoolIndexInv(motRequete.get(i), "or");
					break;
				}
			}
			else
				newParam = new ParametreRechercheBoolIndexInv(motRequete.get(i), "or");
			
			parametres.add(newParam);
		}
		
		return parametres;
	}
	
}
