package readCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFileTati {
	public void readFile(String csvFileToRead) {
		BufferedReader br = null;
		String line = "";
		String splitBy = ";";
		try {
			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null) {
				String[] products = line.split(splitBy);
				System.out.println("Products [Code= " + products[0]
						+ " , Desc=" + products[1] + " , Quantity="
						+ products[2] + "]");
			}
			// aenderung damits was zu komitten gibr
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		String csvFileToRead = "C:/Users/Tatjana/Desktop/test.csv";
		ReadCSVFileTati reader = new ReadCSVFileTati();
		reader.readFile(csvFileToRead);
	}
}