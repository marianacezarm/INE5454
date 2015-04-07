package model.calcWeight;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.processorDocument.Document;
import model.processorDocument.TermDocument;
import model.processorDocument.Vocabulary;

public class CalcWeightBooleanExtends extends CalcWeight {

	public void calcWeight(Map<String, Document> documents,
			Vocabulary vocabulary) {
		Collection<String> documentName = documents.keySet();
		for (String document : documentName) {
			Map<String, TermDocument> termsDocument = documents.get(document)
					.getTerms();
			Collection<String> terms = termsDocument.keySet();
			for (String term : terms) {
				termsDocument.get(term).setWeight(1.0);

			}
		}
	}

	@Override
	public void calcWeightForSearch(Document document, List<String> search, Vocabulary vocabulary) {
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
