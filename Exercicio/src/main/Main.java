package main;

import controller.Controller;

public class Main {
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.processor();
		controller.loadVocabulary();
		controller.calcWeight();
		/*controller.printVocabulary();
		System.out.println();
		controller.printDocuments();
		*/controller.search();
	}
}
