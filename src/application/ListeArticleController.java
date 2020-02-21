package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Document;

public class ListeArticleController implements Initializable {

	private Collection<Document> documents;
	
    @FXML
    private VBox listDocument;
    
    @FXML
    private Text nbDocs;
    
    @FXML
    private Text logo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
	}
	
	public void initiateListDocument(Collection<Document> documents) {
		this.documents = documents;
		
		nbDocs.setText(String.valueOf(this.documents.size()));
		
		for (Document document : documents) {
			VBox doc = new VBox();
			Text titre = new Text(), corps = new Text(), localisation = new Text();
			
			titre.setText(document.getTitre());
			
			if(document.getCorp().length() > 100) {
				corps.setText(document.getCorps().substring(0, 100)+"...");
			}
			else
				corps.setText(document.getCorps());
			
			localisation.setText(document.getNomFichier());
			
			doc.getChildren().addAll(titre, corps, localisation, createRectangle());
			VBox.setMargin(doc, new Insets(0, 0, 0, 40));
			
			doc.setOnMouseEntered(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					doc.setStyle("-fx-background-color:lightgrey;");
				}
			});
			
			doc.setOnMouseExited(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					doc.setStyle("-fx-background-color:white;");
				}

			});
			
			//Affichage du document si click sur le doc
			doc.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					afficheDoc(document.getTitre(), document.getCorps(), document.getNomFichier(), arg0);					
				}
				
			});
			
			listDocument.getChildren().add(doc);
		}
		
	}
	
	private void afficheDoc(String titre, String corps, String localisation, Event arg0) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vue/PageDocument.fxml"));
			Parent pageDocumentParent = loader.load();

			Scene pageDocumentScene = new Scene(pageDocumentParent);

			//acces au controller de la nouvelle view
			PageDocumentController controllerDocument = loader.getController();

			controllerDocument.loadDocument(titre, corps, localisation);;

			Stage pageDocumentStage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
			pageDocumentStage.setScene(pageDocumentScene);
			pageDocumentStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Rectangle createRectangle() {
		Rectangle rec = new Rectangle();
		rec.setArcWidth(5);
		rec.setArcHeight(5);
		rec.setHeight(5);
		rec.setWidth(500);
		rec.setFill(Color.valueOf("#eee5e5"));
		
		return rec;
	}

}
