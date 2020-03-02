package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Crawler;
import modele.Document;
import modele.Index;
import modele.IndexInverse;
import modele.RechercheBooleen;
import modele.RechercheVectorielle;
import javafx.scene.control.Button;
   

public class RechercheController implements Initializable{
	
	ObservableList<String> list = FXCollections.observableArrayList();

	@FXML
    private Pane pane;
	
    @FXML
    private ChoiceBox<String> modele;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private TextField searchBar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();		
		
		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				long startTime = System.nanoTime();
				
				String research = searchBar.getText();

				if(!research.equals("")) {
					if(modele.getSelectionModel().getSelectedItem() != null) {
						Collection<Document> docs = null;
						
						switch (modele.getSelectionModel().getSelectedItem()) {
						
						case "Booleen":
							docs = modeleBooleen(research);
							break;
							
						case "Vectoriel":
							docs = modeleVectoriel(research);
							break;
							
						case "Probabiliste":
							
							break;
						}
						
						long endTime   = System.nanoTime();
						long totalTime = endTime - startTime;
						System.out.println((double)totalTime / 1000000000.0 + " secondes");
						
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
				}
			}
			
		});
	} 
	
	private Collection<Document> modeleVectoriel(String requete) {
		Document docRequete = new Document(0L,"requete", "", requete);
		Crawler.indexRequete = new Index();
		Crawler.indexInvRequete = new IndexInverse();
		RechercheVectorielle.resultatMap = new HashMap<>();
		Crawler.getIndexRequete().ajouterDocument(docRequete.getId(),docRequete);
		Crawler.indexInvRequete.calculerIdf(1);
		Crawler.ajouterPoids(Crawler.indexRequete);
		RechercheVectorielle.rechercher(docRequete);
		TreeSet<Document> resultatDocs = new TreeSet<>();
		for(Long id : RechercheVectorielle.resultatMap.keySet()) {
			resultatDocs.add(Crawler.index.getMap().get(id));
		}
		return resultatDocs;
	}
	
	private Set<Document> modeleBooleen(String requete) {
		return RechercheBooleen.rechercheBooleen(requete);
	}
	
	private void loadData() {
		list.removeAll(list);
		
		list.addAll("Booleen",
					"Vectoriel",
					"Probabiliste");
		
		modele.getItems().addAll(list);
	}

}
