package model.processorDocument;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vocabulary {
	
	Map<String,TermVocabulary>vocabulary = new HashMap<String, TermVocabulary>();
	
	public void updateVocabulary(Map<String, TermDocument> termsDocument){
		TermVocabulary termVocabulary;
		Set<String> terms = termsDocument.keySet();
		for(String termDocument :terms){
	    	if(vocabulary.containsKey(termDocument)){
	    		termVocabulary = vocabulary.get(termDocument);
	    		termVocabulary.sumFrequency(termsDocument.get(termDocument).getFrequency());
	    		termVocabulary.addnDocument();
	    	}else{
	    		vocabulary.put(termDocument, new TermVocabulary(termsDocument.get(termDocument).getFrequency()));
	    	}
	    }
	}


	public Map<String, TermVocabulary> getVocabulary() {
       return vocabulary;
	}
	
	public int getFrequency(String term){
		return vocabulary.get(term).getFrequency();
	}
	
	public int getnDoc(String term){
		return vocabulary.get(term).getnDoc();
	}
	
	public void calcIdf (TermVocabulary term, int size) {
		term.setIdf(Math.log10((double)size/term.getnDoc()));
	}

}
