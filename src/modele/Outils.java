package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	
	//application de la formule de similarite
	public static double similarite(Map<String,Keyword> documentA, Map<String,Keyword> documentB) {
		final Set<String> motsCommuns = getMotsCommuns(documentA, documentB);
        final double produitScalaire = produitScalaire(documentA, documentB, motsCommuns);
        double d1 = 0.0d;
        for (Keyword keyword : documentA.values()) {
            d1 += Math.pow(keyword.getPoids(), 2);
        }
        double d2 = 0.0d;
        for (Keyword keyword : documentB.values()) {
            d2 += Math.pow(keyword.getPoids(), 2);
        }
        double cosinus;
        if (d1 <= 0.0 || d2 <= 0.0) {
            cosinus = 0.0;
        } else {
            cosinus = (double) (produitScalaire / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
        }
        return cosinus;
	}
	
	
	//recupere les mots en commun dans les deux documents
	private static Set<String> getMotsCommuns(Map<String,Keyword> documentA, Map<String,Keyword> documentB) {
        final Set<String> intersection = new HashSet<>(documentA.keySet());
        //recupere juste ce qu'il y a en commun dans la collection specifiee
        intersection.retainAll(documentB.keySet());
        return intersection;
    }
	
	//produit scalaire entre les deux vecteurs
	private static double produitScalaire(Map<String,Keyword> documentA, Map<String,Keyword> documentB,
            Set<String> intersection) {
        double produitScalaire = 0;
        for (String key : intersection) {
            produitScalaire += documentA.get(key).getPoids() * documentB.get(key).getPoids();
        }
        return produitScalaire;
    }
}
