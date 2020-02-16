package application;

import javafx.application.Application;
import javafx.stage.Stage;
import modele.Crawler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import modele.Document;
import modele.ParametreRechercheBoolIndexInv;

import java.util.ArrayList;
import java.util.List;
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
		
		//Set<Document> docs = cr.getIndex().rechercheBooleen(d-> d.getMapMots().containsKey("soldier") && d.getMapMots().containsKey("korea"));
		

		
		ArrayList<ParametreRechercheBoolIndexInv> parametres = new ArrayList<ParametreRechercheBoolIndexInv>();
		parametres.add(new ParametreRechercheBoolIndexInv("soldier", "and"));
		parametres.add(new ParametreRechercheBoolIndexInv("korea", "or"));
		Set<Document> docs = cr.getIndexInv().rechercheBooleen(parametres);
		
//		for (Document document : docs) {
//		System.out.println(document.getMapMots().keySet());
//	}
		
		System.out.println(docs.size());
		
		System.out.println(cr.getIndex().getDoc().stream().count()+" fichiers");
	}
}
