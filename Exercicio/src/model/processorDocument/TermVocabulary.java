package model.processorDocument;

public class TermVocabulary {

	int frequency; 
	int nDoc;
	double tf;

	public int getFrequency() {
		return frequency;
	}


	public int getnDoc() {
		return nDoc;
	}

	public TermVocabulary(int frequency) {
		this.frequency += frequency;
		nDoc = 1;
		tf = 0;
	}


	public void addnDocument() {
		nDoc++;
	}

	public void sumFrequency(int frequency) {
		this.frequency += frequency;
	}


	public double getIdf() {
		return tf;
	}


	public void setIdf(double idf) {
		this.tf = idf;
	}
	
	
	
}
