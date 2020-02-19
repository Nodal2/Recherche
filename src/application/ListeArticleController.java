package application;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import modele.Document;

public class ListeArticleController implements Initializable {

	private Collection<Document> documents;
	
    @FXML
    private VBox listDocument;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void initiateListDocument(Collection<Document> documents) {
		this.documents = documents;
		
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
			
			listDocument.getChildren().add(doc);
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
