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
			//System.out.println(this.termes.get(terme).toString());
		}
		else {
			Set<Document> document = new HashSet<>();
			document.add(docs);
			this.termes.put(terme, document);
		}
	}
	
	public String toString() {
		return this.termes.keySet().toString();
	}
	
}
