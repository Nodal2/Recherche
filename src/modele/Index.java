package modele;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Index {

	private Set<Document> documents;

	public Index() {
		this.documents = new HashSet<Document>();
	}

	public void ajouterDocument(Document doc) {
		this.documents.add(doc);
	}

	public Set<Document> getDoc() {
		return this.documents;
	}
	
	public int nbDocument() {
		return this.documents.size();
	}
	
}
