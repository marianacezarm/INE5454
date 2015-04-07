package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.calcWeight.CalcWeight;
import model.calcWeight.CalcWeightBoolean;
import model.calcWeight.CalcWeightBooleanExtends;
import model.calcWeight.CalcWeightTfIdf;
import model.calcWeight.CalcWeightVetorial;
import model.processorDocument.Document;
import model.processorDocument.Search;
import model.processorDocument.TermDocument;
import model.processorDocument.TermVocabulary;
import model.processorDocument.Vocabulary;
import model.processorFile.PreProcessor;
import model.processorFile.Reader;

public class Controller {

	Vocabulary vocabulary = new Vocabulary();
	Map<String,Document> documents = new HashMap<String, Document>();
	CalcWeight calcWeight;
	
	public Controller(){
		calcWeight = new CalcWeightTfIdf();
	}
	
	public void processor(){
		PreProcessor processor = new PreProcessor();
		File folder = new File("src/Documents");
		File[] files = folder.listFiles();
		Document document;
		for (File file : files) {
			String text = Reader.returnText(file.getPath());
			text = text.replaceAll("\n", " ");
			List<String> textList = processor.preProcess(text);
			document = new Document(textList, file.getName());
			documents.put(document.getName(), document);
		}
	}

	public void loadVocabulary() {
		Set<String> documentsName = documents.keySet();
		for(String document :documentsName){
			vocabulary.updateVocabulary(documents.get(document).getTerms());
		}
		
	}

	public void calcWeight() {
		calcWeight.calcWeight(documents, vocabulary);		
	}

	public void search() {
		Search search = new Search();
		List<Document> documentsSearch = search.getSearch(documents, calcWeight, vocabulary);
		for(Document document: documentsSearch){
			System.out.println(document.getName()+ ": "+document.getSearchWeight());
		}
	}
	
    public void printVocabulary(){
    	Map<String,TermVocabulary>vocabulary = this.vocabulary.getVocabulary();
    	TermVocabulary termVocabulary;
    	Set<String> terms = vocabulary.keySet();
    	System.out.println("Vocabulary");
		for(String term :terms){
			termVocabulary = vocabulary.get(term);
			System.out.println("Term: "+term+ " Frequency "+termVocabulary.getFrequency()+ " nDoc "+termVocabulary.getnDoc()+" idf:"+ termVocabulary.getIdf());
		}
    }

	public void printDocuments() {
    	TermDocument termDocument;
    	Map<String,TermDocument>terms;
    	Document document;
    	Set<String> documents = this.documents.keySet();
    	System.out.println("Documents");
		for(String documentName :documents){
			document = this.documents.get(documentName);
			terms = document.getTerms();
			System.out.println("Document: "+ documentName);
			Set<String> termsName = terms.keySet();
			for(String term :termsName){
				termDocument = terms.get(term);
				System.out.println("Term: "+term+ " Frequency "+termDocument.getFrequency()+ " weight "+termDocument.getWeight()+" tf: "+ termDocument.getTf());
			}	
		}
		
	}
}
