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
	private IndexInverse indexInv;
	private List<Path> files;

	public Crawler() {
		this.index = new Index();

		this.files = new ArrayList<Path>();
		Path dir = Paths.get("fichiers/corpusRI");
		try {
			listFiles(dir);
		} catch (IOException e) {
			System.out.println("Répertoire introuvable");
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
			List<String> mots = new ArrayList<>();
			try {

				File fXmlFile = new File(path.toString());
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				NodeList headList = doc.getElementsByTagName("HEADLINE");
				NodeList nList = doc.getElementsByTagName("TEXT");
				String premierP = null;
				String corps = "";

				if(headList.getLength() == 0) {
					headList = doc.getElementsByTagName("HEADER");

					// au cas où pas de titre, on prends le premier paragraphe

					if(headList.getLength() == 0) {
						premierP = nList.item(0).getTextContent();
					}
				}
				

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					corps += nNode.getTextContent();

				}
				
				try {
					this.index.ajouterDocument(new Document(headList.item(0).getTextContent(), corps));

				} catch (NullPointerException e) {
					this.index.ajouterDocument(new Document(premierP, corps));

				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("REPERTOIRE : "+path.toString());
				System.exit(0);

			}
		}
	}

	public Index getIndex() {
		return index;
	}
}
