package modele;

public class Keyword {
	private int occurences;
	private double frequence;
	private double TFIDFfrequences;
	private int nbTot;
	
	public Keyword(int occurences, double frequence, double tFIDFfrequences) {
		this.occurences = occurences;
		this.frequence = frequence;
		TFIDFfrequences = tFIDFfrequences;
	}
	
	public Keyword(int nbMots) {
		this.occurences = 0;
		this.frequence = 0;
		this.TFIDFfrequences =0;
		this.nbTot = nbMots;
	}
	
	public void incrementeOccurence() {
		this.occurences ++;
		this.frequence = this.occurences/this.nbTot;
	}
	
	

}
