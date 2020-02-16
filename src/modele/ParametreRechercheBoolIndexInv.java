package modele;

public class ParametreRechercheBoolIndexInv {
	private String motRecherche;
	private String option;
	
	public ParametreRechercheBoolIndexInv(String m, String o) {
		this.motRecherche = m;
		this.option = o;
	}
	
	public String getMotRecherche() {
		return motRecherche;
	}
	
	public String getOption() {
		return option;
	}
}
