package modele;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Index {

	private HashMap<Long, Document> documents;

	public Index() {
		this.documents = new HashMap<Long,Document>();
	}

	public void ajouterDocument(Long id,Document doc) {
		this.documents.put(id, doc);
	}

	public Set<Document> getDoc() {
		return this.documents.values().stream().collect(Collectors.toSet());
	}
	
	public HashMap<Long,Document> getMap() {
		return this.documents;
	}
	
	public int nbDocument() {
		return this.documents.size();
	}
	
}
