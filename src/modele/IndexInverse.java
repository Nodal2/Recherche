package modele;

import java.util.HashMap;
import java.util.HashSet;
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
