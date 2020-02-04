package modele;

import java.util.ArrayList;
import java.util.List;

public class Outils {
	
	private static String[] PONCTUATION = {" ",".",",",":",";","?","!","\"","(",")","/","'","_","`","-"};
	private static String[] STOP_WORDS = {"a","about","above","after","again","against","all","am","an","and",
			"any","are","aren't","as","at","be","because","been","before","being","below","between","both","but",
			"by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down",
			"during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having",
			"he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's",
			"i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me",
			"more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ought",
			"our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should",
			"shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then",
			"there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to",
			"too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what",
			"what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't",
			"would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"
	};

	public static List<String> split(Document d) {
		List<String> liste = new ArrayList<>();
		/*
		String[] titre = d.getTitre().toLowerCase().split(" ");
		String[] corp = d.getCorp().toLowerCase().split(" ");
		for(int i=0; i<titre.length; i++) {
			liste.add(titre[i]);
		}
		for(int i=0; i<corp.length; i++) {
			liste.add(corp[i]);
		}
		
		for(int i=0; i<liste.size(); i++) {
			System.out.println(liste.get(i));
			for(String symbole : PONCTUATION) {
				liste.get(i).replace(symbole, "");
			}
			System.out.println(liste.get(i));
		}*/
		
		/*
		String[] texte = {};
		for(String symbole : PONCTUATION) {
			texte = d.getCorp().toLowerCase().split(symbole);
		}
		
		for(String mot : texte) {
			liste.add(mot);
			System.out.println(mot);
		}*/
		
		return liste;
	}
	
	public static List<String> removeStopWord(List<String> l){
		List<String> listSansStopWord = new ArrayList<>();
		for (int i=0; i<l.size();i++)
		{
			int j=0;
			while(j<STOP_WORDS.length && !l.get(i).equals(STOP_WORDS[j])) {
				j++;
			}
			if(j==STOP_WORDS.length) {
				listSansStopWord.add(l.get(i));
			}
		}
		return listSansStopWord;

	}
}
