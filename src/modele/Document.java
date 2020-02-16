package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements Comparable<Document>{
	
	private String nomFichier;
	private String titre;
	private String corps;
	private double score;

	private Map<String, Keyword> mapMots;

	public Document(String nomFichier, String t, String c) {
		this.mapMots = new HashMap<String, Keyword>();
		this.nomFichier = nomFichier;
		this.titre = t;
		this.corps = c;
		this.score = 0.0;
		ajouterMots();
	}

	public void ajouterMots() {
		List<String> titreMots = Outils.normalize(this.titre);
		List<String> corpsMots = Outils.normalize(this.corps);
		List<String> mots = new ArrayList<>();
		mots.addAll(titreMots);
		mots.addAll(corpsMots);
		
		for (String motCle : mots) {
			if(this.mapMots.keySet().contains(motCle)) {
				this.mapMots.get(motCle).incrementeOccurence();
				
			
			}
			else {
				Crawler.indexInv.ajouterTerme(motCle, this);
				this.mapMots.put(motCle, new Keyword());
			}
		}
		for (String motCle : this.mapMots.keySet()) {
			this.mapMots.get(motCle).calculerFrequence(this.mapMots.keySet().size());
		}
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(Document o) {
		if (this.score - o.getScore() > 0)
			return -1;
		else if(this.score - o.getScore() < 0)
			return 1;
		return 0;
	}
	
	public double getScore() {
		return score;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public String getCorps() {
		return corps;
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

}
