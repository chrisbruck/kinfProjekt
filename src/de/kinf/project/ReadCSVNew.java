package de.kinf.project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class ReadCSVNew {

	Connection conn;
	
	//HashMap for storing the column names
	
	Map<Integer, Integer > columnsMap = new HashMap<>();
	Map<String, Integer > firstNameMap = new HashMap<>();
	int firstNameCounter = 0;

	public static void main(String[] args) throws ParseException,
			ClassNotFoundException, SQLException {

		ReadCSVNew obj = new ReadCSVNew();
		obj.connection();
		obj.run();
	}

	public void connection() throws ClassNotFoundException, SQLException {
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost:3306/kinf_project";
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myUrl, "root", "");
	}

	public void run() throws SQLException {

				
		// String csvFile =
		// "nik-2:/ christian$/Applications/XAMPP/xamppfiles/htdocs/miproject/migrationstreams/build/importCSV/data.csv";
		//URL url = Resource("example_data.csv");
		//String csvFile = url.getPath().toString();

		BufferedReader br = null;
		String[] splittedRow;
		String csvSplitBy = ",";
		int counter = 0;
		try {
  		    InputStream is = ReadCSVNew.class.getResourceAsStream("\\example_data.csv");
		   	br = new BufferedReader(new InputStreamReader(is, "UTF8"));
		   	CSVReader csvr = new CSVReader(br);
		   	String[] splittedColumns = csvr.readNext();
		 					
		   	//reading column titles to map, used for mapping to primary keys 
			for (int i= 0; i< splittedColumns.length; i++){
				System.out.println(i + " " + splittedColumns[i]);
				if (splittedColumns[i].contains("HS B")){
					columnsMap.put(i, 1);
				}
				else if (splittedColumns[i].contains("HS C")){
					columnsMap.put(i, 2);
				}
				
				else if (splittedColumns[i].contains("HS D")){
					columnsMap.put(i, 3);
				}
				else if (splittedColumns[i].contains("HS E")){
					columnsMap.put(i, 4);
				}
				else if (splittedColumns[i].contains("HS F")){
					columnsMap.put(i, 5);
				}
				else if (splittedColumns[i].contains("HS G")){
					columnsMap.put(i, 6);
				}
				else if (splittedColumns[i].contains("HS H")){
					columnsMap.put(i, 7);
				}
				else if (splittedColumns[i].contains("HS I")){
					columnsMap.put(i, 8);
				}
				else if (splittedColumns[i].contains("HS J")){
					columnsMap.put(i, 9);
				}
				else if (splittedColumns[i].contains("V E 38")){
					columnsMap.put(i, 10);
			}
			}
				
				
				
			System.out.println(columnsMap);
			/*
			 * int personId = 0; int countryOfBirthId; int denominationId; int
			 * professionalCategoryId;
			 */
		
			while ((splittedRow = csvr.readNext()) != null) {
				//System.out.println(line);
				counter++;

				int pid = Integer.parseInt(splittedRow[0]);
				int seite_original = Integer.parseInt(splittedRow[1]);
				String nummer_hess = (splittedRow[2]);
				String anrede = splittedRow[3];
				String anrede_normal = splittedRow[4];
								
				//firstName Stuff
				//probably firstNameKey not needed; just forward currendt person id to helper function
				//no first name field in person-table
				int firstNameKey;
				String vorname = splittedRow[5];
				firstNameKey = writeFirstName(vorname, columnsMap.get(7));
				String vornameNormal = splittedRow[6];
				firstNameKey = writeFirstName(vornameNormal, columnsMap.get(9));
				for (int j = 7; j <=16; j++){
					if (!splittedRow[j].equals("")){
						//System.out.println("check" + " " + counter +  " " + j);
						firstNameKey = writeFirstName(splittedRow[j], columnsMap.get(j));
					}
				}
				
				//lastName Stuff
				
				
				
			
				String insertPerson = "INSERT INTO personTest(pid, seite_original, nummer_hess)"
						+ " VALUES (?, ?, ?)";
				PreparedStatement prepareInsertPerson = conn
						.prepareStatement(insertPerson);
				prepareInsertPerson.setInt(1, pid);
				prepareInsertPerson.setInt(2, seite_original);
				prepareInsertPerson.setString(3, nummer_hess);

				
				 }
			System.out.println(firstNameMap);
			
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	
	private int writeFirstName(String firstName, int sourceId){
		int firstNameKey = 0;
		if (!firstNameMap.containsKey(firstName)){
			firstNameCounter++;
			firstNameMap.put(firstName, firstNameCounter);
			firstNameKey = firstNameCounter;
			//String insertFirstName = "INSERT INTO migrations(city, country_id, month, year, person_id)"
				//	 + " VALUES (?, ?, ?, ?, ?)";
		}else{
			firstNameKey = firstNameMap.get(firstName);
		}
	
		return firstNameKey;
	}
	

	public int getCountryId(String country) throws SQLException {
		String queryCheckCountry = "SELECT * FROM countries WHERE country = ?";
		PreparedStatement prepareCheckCountry = conn
				.prepareStatement(queryCheckCountry);
		prepareCheckCountry.setString(1, country);
		ResultSet resultCheckCountry = prepareCheckCountry.executeQuery();
		if (!resultCheckCountry.next()) {
			return -1;
		} else {
			return resultCheckCountry.getInt(1);
		}
	}

	public void createNewCountry(String country, String code,
			String countryOfBirthLatitude, String countryOfBirthLongitude)
			throws SQLException {
		String insertCountry = "INSERT INTO countries(country, code, latitude, longitude) VALUES (?, ?, ?, ?)";
		PreparedStatement prepareInsertCountry = conn
				.prepareStatement(insertCountry);
		prepareInsertCountry.setString(1, country);
		prepareInsertCountry.setString(2, code);
		prepareInsertCountry.setDouble(3,
				Double.parseDouble(countryOfBirthLatitude));
		prepareInsertCountry.setDouble(4,
				Double.parseDouble(countryOfBirthLongitude));
		prepareInsertCountry.executeUpdate();
	}

	public int getDenominationId(String denomination) throws SQLException {
		String queryCheckDenomination = "SELECT * FROM denominations WHERE denomination = ?";
		PreparedStatement prepareCheckDenomination = conn
				.prepareStatement(queryCheckDenomination);
		prepareCheckDenomination.setString(1, denomination);
		ResultSet resultCheckDenomination = prepareCheckDenomination
				.executeQuery();
		if (!resultCheckDenomination.next()) {
			return -1;
		} else {
			return resultCheckDenomination.getInt(1);
		}
	}

	public void createNewDenomination(String denomination) throws SQLException {
		String insertDenomination = "INSERT INTO denominations(denomination) VALUES (?)";
		PreparedStatement prepareInsertDenomination = conn
				.prepareStatement(insertDenomination);
		prepareInsertDenomination.setString(1, denomination);
		prepareInsertDenomination.executeUpdate();
	}

	public int getProfessionalCategoryId(String professionalCategory)
			throws SQLException {
		String queryCheckProfessionalCategory = "SELECT * FROM professional_categories WHERE professional_category = ?";
		PreparedStatement prepareCheckProfessionalCategory = conn
				.prepareStatement(queryCheckProfessionalCategory);
		prepareCheckProfessionalCategory.setString(1, professionalCategory);
		ResultSet resultCheckDenomination = prepareCheckProfessionalCategory
				.executeQuery();
		if (!resultCheckDenomination.next()) {
			return -1;
		} else {
			return resultCheckDenomination.getInt(1);
		}
	}

	public void createNewProfessionalCategory(String professionalCategory)
			throws SQLException {
		String insertProfessionalCategory = "INSERT INTO professional_categories(professional_category) VALUES (?)";
		PreparedStatement prepareInsertProfessionalCategory = conn
				.prepareStatement(insertProfessionalCategory);
		prepareInsertProfessionalCategory.setString(1, professionalCategory);
		prepareInsertProfessionalCategory.executeUpdate();
	}

}
