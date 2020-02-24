package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Document;

public class PageDocumentController implements Initializable{
	
	private Collection<Document> docs;

	@FXML
    private Text docTitre;

    @FXML
    private Text docCorps;

    @FXML
    private Text docLocalisation;
    
    @FXML
    private Text logo;
    
    @FXML
    private Button btnRetour;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		logo.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				logo.setFill(Color.WHITE);
			}
		});
		
		logo.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				logo.setFill(Color.BLACK);
				
			}
		});
		
		logo.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/vue/vueRecherche.fxml"));
					Parent RechercheParent = loader.load();
	
					Scene pageDocumentScene = new Scene(RechercheParent);
	
					Stage RechercheStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
					RechercheStage.setScene(pageDocumentScene);
					RechercheStage.show();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnRetour.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event arg0) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/vue/vueListeArticle.fxml"));
					Parent listeArticleParent = loader.load();
	
					Scene listeArticleScene = new Scene(listeArticleParent);
	
					//acces au controller de la nouvelle view
					ListeArticleController controllerListe = loader.getController();
	
					controllerListe.initiateListDocument(docs);
	
					Stage listeArticleStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
					listeArticleStage.setScene(listeArticleScene);
					listeArticleStage.show();
	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void loadDocument(String titre, String corps, String localisation, Collection<Document> docs) {
		docTitre.setText(titre);
		docCorps.setText(corps);
		docLocalisation.setText(localisation);
		this.docs=docs;
	}
    
    
}
