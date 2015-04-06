package model.processorDocument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.calcWeight.CalcWeight;
import model.processorFile.PreProcessor;
import model.processorFile.Reader;

public class Search {

	public List<Document> getSearch(Map<String, Document> documents, CalcWeight calcWeight) {
		String text = Reader.returnText("src/search.txt");
		PreProcessor preProcessor = new PreProcessor();
		text = text.replaceAll("\n", "");
		List<String> search = preProcessor.preProcess(text);
		List<Document> documentSearch = new ArrayList<Document>();
		Set<String> documentsName = documents.keySet();
		for(String document :documentsName){
		    calcWeight.calcWeightForSearch(documents.get(document), search);
		    documentSearch.add(documents.get(document));
		}
		Collections.sort(documentSearch);
		return documentSearch;
		
		
	}

}
