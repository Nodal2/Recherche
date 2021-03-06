package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tartarus.snowball.ext.PorterStemmer;

public class Outils {

	private static String[] PONCTUATION = {" ",".",",",":",";","?","!","\"","(",")","/","'","_","`","-", "\n","\0", "\t"};


	public static List<String> split(String texte) {
		List<String> liste = new ArrayList<>();
		String texteSansMaj = texte.toLowerCase();
		String[] texteSplite = texteSansMaj.split(" ");

		for(int i=0; i<texteSplite.length; i++) {
			liste.add(texteSplite[i]);
		}



		return liste;
	}

	public static List<String> removePonctuation(List<String> mots) {
		for(int i=0; i<mots.size(); i++) {
			for(String symbole : PONCTUATION) {
				String motSansPonctuation = mots.get(i).replace(symbole, "");
				mots.set(i, motSansPonctuation);
			}
		}
		return mots;
	}

	public static List<String> lemmatize(List<String> mots) {
		PorterStemmer stemmer = new PorterStemmer();
		List<String> motsLem = new ArrayList<String>();
		for (String mot : mots) {
			stemmer.setCurrent(mot);
			stemmer.stem(); 
			motsLem.add(stemmer.getCurrent());
		}
		return motsLem;


	}

	@SuppressWarnings("resource")
	public static List<String> removeStopWord(List<String> l){

		try {
			File file = new File("fichiers/stopwords.txt"); 

			BufferedReader br;
			br = new BufferedReader(new FileReader(file));		  
			String st; 
			ArrayList<String> stopwords = new ArrayList<>();
			while ((st = br.readLine()) != null) {
				stopwords.add(st);
			} 

			Iterator<String> iteratorList = l.iterator();
			while(iteratorList.hasNext()) {
				String mot = iteratorList.next();

				if(stopwords.contains(mot)) {
					iteratorList.remove();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return l;

	}

}
