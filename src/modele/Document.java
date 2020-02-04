package modele;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Document {
	
	private String titre;
	private String corps;
	
	private Map<String, Keyword> frequences;
	
	public Document(String t, String c) {
		this.frequences = new HashMap<String, Keyword>();
		this.titre = t;
		this.corps = c;
		ajouterMots();
	}
	
	public void ajouterFrequence(String mot, Keyword motCle) {
		this.frequences.put(mot, motCle);
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public String getCorp() {
		return this.corps;
	}
	
	public void ajouterMots() {
		Set<String> motsCle = new HashSet<>();
		List<String> mots = Outils.split(this.titre);
		mots.addAll(Outils.split(this.corps)); 
		mots = Outils.removePonctuation(mots);
		mots = Outils.removeStopWord(mots);
		motsCle.addAll(mots);
		for (String motCle : motsCle) {
			if(this.frequences.keySet().contains(motCle))
				this.frequences.get(motCle).incrementeOccurence();
			else
				this.frequences.put(motCle, new Keyword());
		}
	}
	
}
