package model.calcWeight;

import java.util.List;
import java.util.Map;

import model.processorDocument.Document;
import model.processorDocument.Vocabulary;

public abstract class CalcWeight {
	
	public abstract void calcWeight(Map<String, Document> documents, Vocabulary vocabulary);

	public abstract void calcWeightForSearch(Document document, List<String> search, Vocabulary vocabulary);
		
		
	

}
