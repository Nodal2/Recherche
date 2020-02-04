package modele;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		this.indexInv = new IndexInverse();

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
				
				Document document;
				
				try {
					document = new Document(headList.item(0).getTextContent(), corps);
				} catch(NullPointerException e) {
					document = new Document(premierP, corps);
				}
			
				
				Set<String> motsCle = new HashSet<>();
				List<String> mots = Outils.split(document.getTitre());
				mots.addAll(Outils.split(document.getCorp())); 
				mots = Outils.removePonctuation(mots);
				mots = Outils.removeStopWord(mots);
				motsCle.addAll(mots);
				for (String motCle : motsCle) {
					this.indexInv.ajouterTerme(motCle, document);
					//System.out.println(this.indexInv.toString());
					
					if(document.getMapMots().keySet().contains(motCle))
						document.getMapMots().get(motCle).incrementeOccurence();
					else
						document.getMapMots().put(motCle, new Keyword(motsCle.size()));
				}
				
				this.index.ajouterDocument(document);
				
				
				
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

