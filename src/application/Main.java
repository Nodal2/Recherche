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
		//Code de lancement d'application et indexation des fichiers
		Crawler cr = new Crawler();
		RechercheBooleen.documents = Crawler.getIndex().getDoc();
		RechercheBooleen.termes = Crawler.getIndexInv().getTermes();
		
		launch(args);
		
		System.out.println(Crawler.getIndex().getDoc().stream().count()+" fichiers");
	}
}
