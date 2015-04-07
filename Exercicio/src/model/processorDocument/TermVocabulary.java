package model.processorDocument;

public class TermVocabulary {

	int frequency; 
	int nDoc;
	double idf;

	public int getFrequency() {
		return frequency;
	}


	public int getnDoc() {
		return nDoc;
	}

	public TermVocabulary(int frequency) {
		this.frequency += frequency;
		nDoc = 1;
		idf = 0;
	}


	public void addnDocument() {
		nDoc++;
	}

	public void sumFrequency(int frequency) {
		this.frequency += frequency;
	}


	public double getIdf() {
		return idf;
	}


	public void setIdf(double idf) {
		this.idf = idf;
	}
	
	
	
}
