package model.processorDocument;

public class TermDocument {
	
	double weight;
	int frequency; 
	double tf;
	
	public void addFrequency() {
		frequency++;
	}
	
	public TermDocument(){
		frequency = 1;
		tf = 0;
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
	
	public double getTf() {
		return tf;
	}

	public void setTf(double tf) {
		this.tf = tf;
	}


}
