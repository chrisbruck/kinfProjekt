package de.kinf.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFileTati {
	public void readFile(String csvFileToRead) {
		BufferedReader br = null;
		String line = "a";
		String splitBy = ";";
		try {
			br = new BufferedReader(new FileReader(csvFileToRead));
			while ((line = br.readLine()) != null) {
				String[] products = line.split(splitBy);
				System.out.println(line);
			}
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
		String csvFileToRead = "C:\\Users\\Tatjana\\Documents\\Masterstudium\\SoSe2015\\Kinf-Projekt\\example_data.csv";
		ReadCSVFileTati reader = new ReadCSVFileTati();
		reader.readFile(csvFileToRead);
	}
}