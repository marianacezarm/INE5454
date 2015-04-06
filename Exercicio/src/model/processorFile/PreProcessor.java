package model.processorFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreProcessor {

	public PreProcessor() {
	}

	private String normalize(String text) {
		text = text.replaceAll("[¡¿¬√]", "A");
		text = text.replaceAll("[…» ]", "E");
		text = text.replaceAll("[Õ]", "I");
		text = text.replaceAll("[”“‘’]", "O");
		text = text.replaceAll("[⁄Ÿ€‹]", "U");
		text = text.replaceAll("[«]", "C");
		text = text.replaceAll("[Á]", "c");
		text = text.replaceAll("[·‡‚„]", "a");
		text = text.replaceAll("[ÈËÍ]", "e");
		text = text.replaceAll("[Ì]", "i");
		text = text.replaceAll("[ÛÚÙı]", "o");
		text = text.replaceAll("[˙˘˚¸]", "u");
		text = text.replaceAll("[^0-9A-Za-z\\s]", "");
		
		return text;		
	}

	private List<String> toList(String text,String expression){
		String[] words = text.split(expression);
		List<String> wordsList = new ArrayList<String>(Arrays.asList(words));
		return wordsList;
	}

	private List<String> removeStopWords(List<String> wordsList) {
		String stopWords = Reader.returnText("src/stopwords.txt");
		stopWords = normalize (stopWords);
		List<String> stopWordsList = toList(stopWords, "\n");
		
		wordsList.removeAll(stopWordsList);
		return wordsList;
	}
	
	private List<String> removePronoums(List<String> wordsList) {
		String pronoums = Reader.returnText("src/adverbios.txt");
		pronoums = normalize (pronoums);
		List<String> pronounmsList = toList(pronoums, "\n");
		
		wordsList.removeAll(pronounmsList);
		return wordsList;
	}
	
	public List<String> preProcess(String text){
		text = normalize(text);
		List<String> words = toList(text, " ");
		words = removeStopWords(words);
		words = removePronoums(words);
		return words;
	}
}



