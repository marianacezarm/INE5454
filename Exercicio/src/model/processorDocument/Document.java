package model.processorDocument;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements Comparable<Document> {

	Map<String, TermDocument> terms = new HashMap<String, TermDocument>();
	double searchWeight;
	String name;

	public Document(List<String> textList, String name) {
		for (String termName : textList) {
			if (terms.containsKey(termName)) {
				terms.get(termName).addFrequency();
			} else {
				terms.put(termName, new TermDocument());
			}
		}
		this.name = name;
	}

	public Map<String, TermDocument> getTerms() {
		return terms;
	}

	public double getSearchWeight() {
		return searchWeight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Document o) {
		if (this.getSearchWeight() < o.getSearchWeight()) {
			return 1;
		} else if (this.getSearchWeight() > o.getSearchWeight()) {
			return -1;
		} else {
			return 0;
		}
	}

	public void SetSearchWeight(double searchWeight) {
		this.searchWeight = searchWeight;

	}

	public double getTf(TermDocument term) {
		return terms.get(term).getFrequency();
	}

	public void calcTf(TermDocument term) {
		int maxFreq = 0;
		Collection<String> termsC = terms.keySet();
		for (String termC : termsC) {
			if (terms.get(termC).getFrequency() > maxFreq) {
				maxFreq = terms.get(termC).getFrequency();
			}
		}
		double tf = (double)term.getFrequency() / maxFreq;
		term.setTf(tf);
	}
}
