package model.calcWeight;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.processorDocument.Document;
import model.processorDocument.TermDocument;
import model.processorDocument.Vocabulary;
import model.processorDocument.*;

public class CalcWeightVetorial extends CalcWeight {

	public void calcWeight(Map<String, Document> documents, // idf*tf
			Vocabulary vocabulary) {
		Collection<String> documentName = documents.keySet();
		for (String document : documentName) {
			Map<String, TermDocument> termsDocument = documents.get(document)
					.getTerms();
			Collection<String> terms = termsDocument.keySet();
			for (String term : terms) {
				TermVocabulary termVocabulary = vocabulary.getVocabulary().get(term);
				TermDocument termDoc = documents.get(document).getTerms().get(term);
				documents.get(document).calcIdf(termDoc);
				vocabulary.calcIdf(termVocabulary, documents.size());
				termsDocument.get(term).setWeight(termDoc.getIdf() * vocabulary.getTf(termVocabulary));
			}
		}
	}	

	@Override
	public void calcWeightForSearch(Document document, List<String> search) { // parte da similaridade (vou fazer amanhã porque to com sono =*)
		int containTerms = 0;
		Map<String, TermDocument> termsDocument = document.getTerms();
		Collection<String> terms = termsDocument.keySet();
		for (String wordSearch : search) {
			for (String term : terms) {
				if (term.trim().equals(wordSearch.trim())) {
					containTerms++;
				}
			}
		}
		double weight = (double)containTerms / search.size();
		document.SetSearchWeight(weight);
	}
}
