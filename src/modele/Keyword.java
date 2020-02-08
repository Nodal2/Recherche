package modele;

public class Keyword {
	private int occurences;
	private double frequence;
	private float tFIdf;

	public Keyword() {
		this.occurences = 0;
		this.tFIdf = 0;
		this.frequence = 0;
	}
	

	public void incrementeOccurence() {
		this.occurences ++;
	}
	
	public int getOccurrences() {
		return this.occurences;
	}

	public int getOccurences() {
		return occurences;
	}

	public double getPoids() {
		return tFIdf;
	}
	
	
	
	public void calculerPoids(float idf, int nbMots) {
		if(nbMots != 0)
			this.frequence = this.occurences/nbMots;
			System.out.println(this.occurences);
		this.tFIdf = (float) (this.frequence * idf);
	}




}
