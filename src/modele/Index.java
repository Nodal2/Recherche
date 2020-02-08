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
	
	public Set<Document> rechercheBooleen(Predicate<Document> condition) {
		Set<Document> listDoc = new HashSet<Document>();
		for (Document document : documents) {
			if(condition.test(document)) {
				listDoc.add(document);
			}
		}
		return listDoc;
	}
}
