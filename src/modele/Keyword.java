package modele;

public class Keyword {
	private int occurences;
	private double frequence;
	private double TFIDFfrequences;
	
	public Keyword(int occurences, double frequence, double tFIDFfrequences) {
		this.occurences = occurences;
		this.frequence = frequence;
		TFIDFfrequences = tFIDFfrequences;
	}
	
	public Keyword() {
		this.occurences = 0;
		this.frequence = 0;
		this.TFIDFfrequences =0;
	}
	
	public void incrementeOccurence() {
		this.occurences ++;
	}
	
	

}
