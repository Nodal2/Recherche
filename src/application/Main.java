package application;

import javafx.application.Application;
import javafx.stage.Stage;
import modele.Crawler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import modele.Document;
import modele.ParametreRechercheBoolIndexInv;
import modele.RechercheBooleen;

import java.util.ArrayList;
import java.util.List;
import modele.RechercheVectorielle;
import java.util.Set;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		Crawler cr = new Crawler();
		
		//test vectoriel
		Document requete = new Document("requete","israel","hello, let's talk about the israel country");
		cr.getIndexRequete().ajouterDocument(requete);
		Crawler.indexInvRequete.calculerIdf(1);
		cr.ajouterPoids(cr.indexRequete);
		RechercheVectorielle.rechercher(requete, cr.getIndex().getDoc());
		//fin test 
	
		//test booleen
		System.out.println("Test modèle Booleen");
		RechercheBooleen.documents = cr.getIndex().getDoc();
		RechercheBooleen.termes = cr.getIndexInv().getTermes();
		ParametreRechercheBoolIndexInv p1 = new ParametreRechercheBoolIndexInv("soldier", "negate");
		
		List<ParametreRechercheBoolIndexInv> parametres = new ArrayList<ParametreRechercheBoolIndexInv>();
		parametres.add(p1);
		Set<Document> docs = RechercheBooleen.rechercheBooleen(parametres);
		System.out.println(docs.size());
		//fin test boul
		
		System.out.println(cr.getIndex().getDoc().stream().count()+" fichiers");
	}
}
