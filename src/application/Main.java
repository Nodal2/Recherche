package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modele.Crawler;
import javafx.scene.Parent;
import javafx.scene.Scene;

import modele.RechercheBooleen;

import java.net.URL;

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
		long startTime = System.nanoTime();
		Crawler cr = new Crawler();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println((double)totalTime / 1000000000.0 + " secondes");
		
		RechercheBooleen.documents = Crawler.getIndex().getDoc();
		
		launch(args);
		
		System.out.println(Crawler.getIndex().getDoc().stream().count()+" fichiers chargés au total");
	}
}
