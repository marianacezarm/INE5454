package model.processorFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public static String returnText (String path) {
		try {
			FileReader reader = new FileReader (path);
			BufferedReader fileReader = new BufferedReader (reader);
			String text = "";
			String line;
			line = fileReader.readLine();
			while (line != null) {
				text += line.toLowerCase() + "\n";
				line = fileReader.readLine();
			}			
			reader.close();
			return text;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

}
