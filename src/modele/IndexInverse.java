package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexInverse {
	private Map<String, Set<Document>> termes;
	
	public IndexInverse() {
		this.termes = new HashMap<String, Set<Document>>();
	}
	
	public void ajouterTerme(String terme, Set<Document> docs) {
		this.termes.put(terme, docs);
	}
}
