package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Crawler;
import modele.Document;
import modele.ParametreRechercheBoolIndexInv;
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
				String research = searchBar.getText();

				if(!research.equals("")) {
					if(modele.getSelectionModel().getSelectedItem() != null) {
						Collection<Document> docs = null;
						
						switch (modele.getSelectionModel().getSelectedItem()) {
						
						case "Booleen":
							
							break;
							
						case "Vectoriel":
							docs = modeleVectoriel(research);
							break;
							
						case "Probabiliste":
							
							break;
						}

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
		Document docRequete = new Document("requete", "", requete);
		
		Crawler.getIndexRequete().ajouterDocument(docRequete);
		Crawler.indexInvRequete.calculerIdf(1);
		System.out.println("Sauce");
		Crawler.ajouterPoids(Crawler.indexRequete);
		RechercheVectorielle.corpus = Crawler.getIndex().getDoc();
		RechercheVectorielle.rechercher(docRequete);
		
		
		return RechercheVectorielle.resultat;
	}
	
	private Set<Document> modeleBooleen() {
		ParametreRechercheBoolIndexInv p1 = new ParametreRechercheBoolIndexInv("korea", "and");
		
		List<ParametreRechercheBoolIndexInv> parametres = new ArrayList<ParametreRechercheBoolIndexInv>();
		parametres.add(p1);
		Set<Document> documents = RechercheBooleen.rechercheBooleen(parametres);
		
		return documents;
	}
	
	private void loadData() {
		list.removeAll(list);
		
		list.addAll("Booleen",
					"Vectoriel",
					"Probabiliste");
		
		modele.getItems().addAll(list);
	}

}
