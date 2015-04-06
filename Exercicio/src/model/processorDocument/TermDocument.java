package model.processorDocument;

public class TermDocument {
	
	double weight;
	int frequency; 
	double idf;
	
	public void addFrequency() {
		frequency++;
	}
	
	public TermDocument(){
		frequency = 1;
		idf = 0;
	}

	public int getFrequency(){
		return frequency;
	}
	
	public double getWeight(){
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		
	}
	
	public double getIdf() {
		return idf;
	}

	public void setIdf(double idf) {
		this.idf = idf;
	}


}
