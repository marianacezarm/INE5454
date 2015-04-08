package model.calcWeight;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.processorDocument.Document;
import model.processorDocument.TermDocument;
import model.processorDocument.TermVocabulary;
import model.processorDocument.Vocabulary;

public class CalcWeightVetorial3 extends CalcWeight{

	@Override
	public void calcWeight(Map<String, Document> documents,
			Vocabulary vocabulary) {
		Collection<String> documentName = documents.keySet();
		double weightQuery = 0;
		for (String document : documentName) {
			Map<String, TermDocument> termsDocument = documents.get(document)
					.getTerms();
			Collection<String> terms = termsDocument.keySet();
			for (String term : terms) {
				TermVocabulary termVocabulary = vocabulary.getVocabulary().get(term);
				TermDocument termDoc = documents.get(document).getTerms().get(term);
				documents.get(document).calcTf(termDoc);
				vocabulary.calcIdf(termVocabulary, documents.size());
				termsDocument.get(term).setWeight((double)(1 + (double)Math.log10(termDoc.getFrequency()))* vocabulary.getVocabulary().get(term).getIdf());
			}
		}
		
	}

	@Override
	public void calcWeightForSearch(Document document, List<String> search, Vocabulary vocabulary) {
		calcSimilarity(document, search, vocabulary);
		
	}
	
	public void calcSimilarity(Document document, List<String> search, Vocabulary vocabulary){
		double nominator = 0;
		double denominator = 0;
		double denoSumTerms = 0;
		double denoSumQuerys = 0;
		Map<String, TermDocument> termsDocument = document.getTerms();
		Collection<String> terms = termsDocument.keySet();
		for (String term : terms) {
			denoSumTerms+= (double) Math.pow(document.getTerms().get(term).getWeight(), 2);
			denoSumQuerys+= (double) Math.pow(vocabulary.getVocabulary().get(term).getIdf(), 2);
		}
		denoSumTerms = (double) Math.sqrt(denoSumTerms);
		denoSumQuerys = (double) Math.sqrt(denoSumQuerys);
		denominator = (double) denoSumQuerys * denoSumTerms;
		
		for(String wordSearch : search){
			if (document.getTerms().containsKey(wordSearch)) {
				nominator+= (double) vocabulary.getVocabulary().get(wordSearch).getIdf() * document.getTerms().get(wordSearch).getWeight();
				
			}
		}
		document.SetSearchWeight((double)nominator/denominator);
	}
	

}
