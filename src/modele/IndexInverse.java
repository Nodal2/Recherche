package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexInverse {

	private Map<String, Set<Document>> termes;
	private Map<String, Float> idfs;

	public IndexInverse() {
		this.termes = new HashMap<String, Set<Document>>();
		this.idfs = new HashMap<String, Float>();
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
	
	public Map<String, Float> getIdfs() {
		return idfs;
	}

	public void calculerIdf(int nbDocs) {
		for(String mot : this.termes.keySet()) {	
			if(nbDocs != 0) {
				float idf = (float)nbDocs/this.termes.get(mot).size();
				this.idfs.put(mot,(float) Math.log(idf));
			}
			
		}
		
	}

}
