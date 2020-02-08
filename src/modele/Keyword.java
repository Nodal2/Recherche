package modele;

public class Keyword {
	private int occurences;
	private double frequence;
	private double TFIDfrequences;
	private int nbTot;

	public Keyword(int occurences, double frequence, double tFIDFfrequences) {
		this.occurences = occurences;
		this.frequence = frequence;
		TFIDfrequences = tFIDFfrequences;
	}

	public Keyword(int nbMots) {
		this.occurences = 0;
		this.frequence = 0;
		this.TFIDfrequences =0;
		this.nbTot = nbMots;
	}

	public void incrementeOccurence() {
		this.occurences ++;
		if(this.nbTot != 0)
			this.frequence = this.occurences/this.nbTot;
	}
	
	public int getOccurrences() {
		return this.occurences;
	}



}
