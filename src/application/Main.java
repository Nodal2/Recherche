package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.Crawler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import modele.Document;
import modele.ParametreRechercheBoolIndexInv;
import modele.RechercheBooleen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import modele.RechercheVectorielle;
import java.util.Set;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			FXMLLoader loader = new FXMLLoader();
			URL xmlUrl = getClass().getResource("/vue/vueRecherche.fxml");
			loader.setLocation(xmlUrl);
			Parent root = loader.load();

			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Crawler cr = new Crawler();
		RechercheBooleen.documents = Crawler.getIndex().getDoc();
		RechercheBooleen.termes = Crawler.getIndexInv().getTermes();
		
		launch(args);
		
		//--------------------------------------------------Code de test
		//Crawler cr = new Crawler();
		
//		//test vectoriel
		Document requete = new Document("requete","israel","hello, let's talk about the israel country");
		Crawler.getIndexRequete().ajouterDocument(requete);
		Crawler.indexInvRequete.calculerIdf(1);
		Crawler.ajouterPoids(Crawler.indexRequete);
		RechercheVectorielle.corpus = Crawler.getIndex().getDoc();
		RechercheVectorielle.rechercher(requete);
//		//fin test 
	
		//test booleen
//		RechercheBooleen.documents = cr.getIndex().getDoc();
//		RechercheBooleen.termes = cr.getIndexInv().getTermes();
//		ParametreRechercheBoolIndexInv p1 = new ParametreRechercheBoolIndexInv("soldier", "negate");
//		
//		List<ParametreRechercheBoolIndexInv> parametres = new ArrayList<ParametreRechercheBoolIndexInv>();
//		parametres.add(p1);
//		Set<Document> docs = RechercheBooleen.rechercheBooleen(parametres);
//		System.out.println(docs.size());
		//fin test boul
		
		System.out.println(cr.getIndex().getDoc().stream().count()+" fichiers");
	}
}
