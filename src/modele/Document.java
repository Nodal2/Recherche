package modele;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Document {

	private String titre;
	private String corps;

	private Map<String, Keyword> mapMots;

	public Document(String t, String c) {
		this.mapMots = new HashMap<String, Keyword>();
		this.titre = t;
		this.corps = c;
		ajouterMots();
	}

	public void ajouterFrequence(String mot, Keyword motCle) {
		this.mapMots.put(mot, motCle);
	}

	public String getTitre() {
		return this.titre;
	}

	public String getCorp() {
		return this.corps;
	}

	public Map<String, Keyword> getMapMots() {
		return mapMots;
	}

	public void ajouterMots() {
		Set<String> motsCle = new HashSet<>();
		List<String> mots = Outils.split(this.titre);
		mots.addAll(Outils.split(this.corps)); 
		mots = Outils.removePonctuation(mots);
		mots = Outils.removeStopWord(mots);
		mots = Outils.lemmatize(mots);
		
		for (String motCle : mots) {
			if(this.mapMots.keySet().contains(motCle))
				this.mapMots.get(motCle).incrementeOccurence();
			else
				this.mapMots.put(motCle, new Keyword(motsCle.size()));
		}
		System.out.println();
		System.out.println("DOCUMENT -------------------------");
		System.out.println();
		for(String mot : this.mapMots.keySet()) {
			System.out.println("mot : "+mot+" | occurences : "+this.mapMots.get(mot).getOccurrences());
		}
	}

}
