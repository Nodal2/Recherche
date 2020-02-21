package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageDocumentController implements Initializable{

	@FXML
    private Text docTitre;

    @FXML
    private Text docCorps;

    @FXML
    private Text docLocalisation;
    
    @FXML
    private Text logo;

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
		
	}
	
	public void loadDocument(String titre, String corps, String localisation) {
		docTitre.setText(titre);
		docCorps.setText(corps);
		docLocalisation.setText(localisation);
	}
    
    
}
