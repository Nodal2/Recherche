package modele;

public class Keyword {
	private int occurences;
	private float frequence;
	private float tFIdf;

	public int getOccurences() {
		return occurences;
	}


	public float getFrequence() {
		return frequence;
	}



	public Keyword() {
		this.occurences = 1;
		this.tFIdf = 0;
		this.frequence = 0;
	}
	

	public void incrementeOccurence() {
		this.occurences ++;
	}


	public double getPoids() {
		return tFIdf;
	}
	
	public void calculerFrequence(int nbMots) {
		if(nbMots != 0)
			this.frequence = (float)this.occurences/nbMots;
	}
	
	
	
	public void calculerPoids(float idf) {
		this.tFIdf = (float) (this.frequence * idf);
	}




}
