package model.calcWeight;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.processorDocument.*;

public class CalcWeightTfIdf extends CalcWeight { // tf = term frequency = TermDocument
	// idf = TermVocabulary	

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
				documents.get(document).calcTf(termDoc);
				vocabulary.calcIdf(termVocabulary, documents.size());
				termsDocument.get(term).setWeight(termDoc.getTf() * termVocabulary.getIdf());
			}
		}
	}	

	@Override
	public void calcWeightForSearch(Document document, List<String> search, Vocabulary vocabulary) {  // criamos uma fórmula para demonstração
		double sum = 0;
		
		for (String wordSearch : search) {
			if (document.getTerms().containsKey(wordSearch)) {
				sum+= document.getTerms().get(wordSearch).getWeight();
			}
		}
		document.SetSearchWeight(sum/search.size());
	}
}


