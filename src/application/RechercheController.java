package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
   

public class RechercheController implements Initializable{
	
	ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> modele;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private TextField searchBar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();		
	} 
	
	private void loadData() {
		list.removeAll(list);
		
		list.addAll("Booleen",
					"Vectoriel",
					"Probabiliste");
		
		modele.getItems().addAll(list);
	}

}
