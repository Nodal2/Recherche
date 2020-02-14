package modele;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Crawler {
	private Index index;
	public Index indexRequete; //a modifier
	
	public static IndexInverse indexInv;
	public static IndexInverse indexInvRequete; //a modifier
	private List<Path> files;

	public Crawler() {
		this.index = new Index();
		this.setIndexRequete(new Index());
		Crawler.indexInv = new IndexInverse();
		Crawler.indexInvRequete = new IndexInverse();

		this.files = new ArrayList<Path>();
		Path dir = Paths.get("fichiers/corpusRI");
		try {
			listFiles(dir);
		} catch (IOException e) {
			System.out.println("Repertoire introuvable");
		}
		lireFichiers();
	}

	public void listFiles(Path path) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					listFiles(entry);
				}
				else {
					files.add(entry);
				}

			}
		}
	}

	public void lireFichiers() {
		for (Path path : this.files) {
			try {

				File fXmlFile = new File(path.toString());
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

				NodeList headList = doc.getElementsByTagName("HEADLINE");
				NodeList nList = doc.getElementsByTagName("TEXT");
				String premierP = null;
				String corps = "";

				if(headList.getLength() == 0) {
					headList = doc.getElementsByTagName("HEADER");

					// au cas pas de titre, on prends le premier paragraphe
					if(headList.getLength() == 0) {
						premierP = nList.item(0).getTextContent();
					}
				}


				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					corps += nNode.getTextContent();

				}

				Document document;

				try {
					document = new Document(path.toString(), headList.item(0).getTextContent(), corps);
				} catch(NullPointerException e) {
					document = new Document(path.toString(), premierP, corps);
				}
				
				this.index.ajouterDocument(document);

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		indexInv.calculerIdf(this.index.getDoc().size());
		ajouterPoids(this.index);
		
		
	}

	public Index getIndex() {
		return index;
	}
	
	public void ajouterPoids(Index index) {
		for(Document doc : index.getDoc()) {
			for(String mot : doc.getMapMots().keySet()) {
				doc.getMapMots().get(mot).calculerPoids(indexInv.getIdfs().get(mot));
			}
		}
	}
	public static IndexInverse getIndexInv() {
		return indexInv;
	}

	public Index getIndexRequete() {
		return indexRequete;
	}

	public void setIndexRequete(Index indexRequete) {
		this.indexRequete = indexRequete;
	}
}

