package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	
	public static List<String> removeStopWord(List<String> l){


		Iterator<String> iteratorList = l.iterator();
		while(iteratorList.hasNext()) {
			String mot = iteratorList.next();
			int j=0;
			while(j<STOP_WORDS.length && !mot.equals(STOP_WORDS[j])) {
				j++;
			}
			if(j!=STOP_WORDS.length) {
				iteratorList.remove();
			}
		}
		
		
		return l;

	}
}
