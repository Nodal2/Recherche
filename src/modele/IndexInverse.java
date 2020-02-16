package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexInverse {

	private Map<String, Set<Document>> termes;

	public IndexInverse() {
		this.termes = new HashMap<String, Set<Document>>();
	}

	public void ajouterTerme(String terme, Document docs) {
		if(this.termes.keySet().contains(terme)) {
			this.termes.get(terme).add(docs);
		}
		else {
			Set<Document> document = new HashSet<>();
			document.add(docs);
			this.termes.put(terme, document);
		}
	}

	public Set<Document> rechercheBooleen(List<ParametreRechercheBoolIndexInv> parametres) {
		Collection<Set<Document>> tousLesDocs = termes.values();
		Set<Document> docsRecherche = new HashSet<Document>();
		
		//On rentre tous les docs dans une seule liste
		for (Set<Document> documents : tousLesDocs) {
			docsRecherche.addAll(documents);
		}
		
		int i = 0;
		for (ParametreRechercheBoolIndexInv parametre : parametres) {

			//On modifie la liste docsRecherche en fonction des mots et des options qui leur sont liee
			Set<Document> setDocParMotParametre = termes.get(parametre.getMotRecherche());
			
			switch (parametre.getOption()) {
		
			case "and":
				docsRecherche.retainAll(setDocParMotParametre);
				break;

			case "negate":
				if(parametres.size() == 1) {
					//Possibilite d evolution en faisant en sorte que cela soit moins couteux et non comme "index"
				}
				docsRecherche.removeAll(setDocParMotParametre);
				break;

			case "or" :
				
			default :
				docsRecherche.addAll(setDocParMotParametre);
				break;
			}
			i++;
		}

		return docsRecherche;
	}

	public Map<String, Set<Document>> getTermes() {
		return termes;
	}

	public String toStringDocs(String mot) {
		String chaine = "\n\t";
		for (Document doc : this.termes.get(mot)) {
			chaine+= doc.getNomFichier()+"\n\t";
		}
		return chaine;
	}

}
