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

	// HashMap for storing the column names
	private Map<Integer, Integer> columnsMap = new HashMap<>();

	//private Map<String, Integer> firstNameMap = new HashMap<>();
	//int firstNameCounter = 0;
	private static final int VORNAME = 5;
	private static final int VORNAME_AUB = 16;
	private static final int NACHNAME_HS_B = 18;
	private static final int NACHNAME_AUB = 28;
	private static final int ORT_HS_B = 30;
	// ORT abweichend??? normal
	private static final int ORT_NORMAL = 41;
	private static final int WIRTLAGE = 51;
	private static final int WIRTLAGE_HS_J = 55;
	private static final int SEMINAR = 56;
	private static final int SEMINAR_HS_J = 62;
	private static final int ZUSAETZE = 66;
	private static final int ZUSAETZE_AUB = 76;

	private static final String VORNAME_SIMPLE_INSERT = "INSERT INTO vornamen_liste(vorname_titel) VALUES (?)";
	private static final String VORNAME_QUERY = "SELECT vorname_id FROM vornamen_liste WHERE vorname_titel = ?";
	private static final String VORNAME_CONNECTED_INSERT = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String NACHNAME_SIMPLE_INSERT = "";
	private static final String NACHNAME_QUERY = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String NACHNAME_CONNECTED_INSERT = "INSERT INTO personen_nachnamen(personen_id, nachnamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String ORT_SIMPLE_INSERT = "";
	private static final String ORT_QUERY = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String ORT_CONNECTED_INSERT = "INSERT INTO personen_ort(personen_id, ort_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String WIRTLAGE_SIMPLE_INSERT = "";
	private static final String WIRTLAGE_QUERY = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String WIRTLAGE_CONNECTED_INSERT = "INSERT INTO personen_wirtlage(personen_id, wirtlage_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String SEMINAR_SIMPLE_INSERT = "";
	private static final String SEMINAR_QUERY = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String SEMINAR_CONNECTED_INSERT = "INSERT INTO personen_seminar(personen_id, wirtlage_id,quellen_id) "
			+ " VALUES (?, ?, ?)";

	private static final String ZUSATZ_SIMPLE_INSERT = "";
	private static final String ZUSATZ_QUERY = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String ZUSATZ_CONNECTED_INSERT = "INSERT INTO personen_seminar(personen_id, wirtlage_id,quellen_id) "
			+ " VALUES (?, ?, ?)";

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
		// URL url = Resource("example_data.csv");
		// String csvFile = url.getPath().toString();

		BufferedReader br = null;
		String[] splittedRow;
		String csvSplitBy = ",";
		int counter = 0;
		try {
			InputStream is = ReadCSVNew.class
					.getResourceAsStream("\\example_data.csv");
			br = new BufferedReader(new InputStreamReader(is, "UTF8"));
			CSVReader csvr = new CSVReader(br);
			String[] splittedColumns = csvr.readNext();

			generateColumnsMap(splittedColumns);

			System.out.println(columnsMap);

			/*
			 * int personId = 0; int countryOfBirthId; int denominationId; int
			 * professionalCategoryId;
			 */

			while ((splittedRow = csvr.readNext()) != null) {
				// System.out.println(line);
				counter++;

				int pid = Integer.parseInt(splittedRow[0]);
				int seite_original = Integer.parseInt(splittedRow[1]);
				String nummer_hess = (splittedRow[2]);
				String anrede = splittedRow[3];
				String anrede_normal = splittedRow[4];

				iterateOverColumns(splittedRow, pid, VORNAME, VORNAME_AUB,
						VORNAME_QUERY, VORNAME_SIMPLE_INSERT,
						VORNAME_CONNECTED_INSERT);
				
//				iterateOverColumns(splittedRow, pid, NACHNAME_HS_B,
//						NACHNAME_AUB, NACHNAME_QUERY, NACHNAME_SIMPLE_INSERT,
//						NACHNAME_CONNECTED_INSERT);
//				
//				iterateOverColumns(splittedRow, pid, ORT_HS_B, ORT_NORMAL,
//						ORT_QUERY, ORT_SIMPLE_INSERT, ORT_CONNECTED_INSERT);
//				
//				iterateOverColumns(splittedRow, pid, WIRTLAGE, WIRTLAGE_HS_J,
//						WIRTLAGE_QUERY, WIRTLAGE_SIMPLE_INSERT,
//						WIRTLAGE_CONNECTED_INSERT);
//				
//				iterateOverColumns(splittedRow, pid, SEMINAR, SEMINAR_HS_J,
//						SEMINAR_QUERY, SEMINAR_SIMPLE_INSERT,
//						SEMINAR_CONNECTED_INSERT);
//				// lastName Stuff
//				iterateOverColumns(splittedRow, pid, ZUSAETZE, ZUSAETZE_AUB,
//						ZUSATZ_QUERY, ZUSATZ_SIMPLE_INSERT,
//						ZUSATZ_CONNECTED_INSERT);

			}

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

	private void iterateOverColumns(String[] splittedRow, int pid, int start,
			int end, String sQLQuery, String sQLSimple, String sQLConnected) {
		for (int j = start; j <= end; j++) {
			if (!splittedRow[j].equals("")) {
				
				int nameKey=0;
				try {
					nameKey = getKeyFromDatabase(splittedRow[j], sQLQuery,
							sQLSimple);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int[] values = { pid, nameKey, columnsMap.get(j) };
				for (int i : values){
				//System.out.print(i +" ");
				}
				//System.out.println();
				writeToConnectingTable(values, sQLConnected);
			}
		}

	}

	private void writeToConnectingTable(int[] values, String sql) {
		PreparedStatement preparedStatement;
		String insertString = sql;
		try {
			preparedStatement = conn.prepareStatement(insertString);
			preparedStatement.setInt(1, values[0]);
			preparedStatement.setInt(2, values[1]);
			preparedStatement.setInt(3, values[2]);
			System.out.println("" + values[0]+ " " + values[1] + " " + values[2]);
			//preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int getKeyFromDatabase(String value, String sqlQuery,
			String sqlInsert) throws SQLException{
		
		  String queryCheckValue = sqlQuery;
		  PreparedStatement prepareCheckValue = conn.prepareStatement(queryCheckValue);
		  prepareCheckValue.setString(1, value);
		  		  
		  ResultSet resultCheckCountry = prepareCheckValue.executeQuery();
		  if(!resultCheckCountry.next()) {
			 insertIntoDatabase(sqlInsert, value);
			  String queryCheck = sqlQuery;
			  PreparedStatement prepareCheck = conn.prepareStatement(queryCheck);
			  prepareCheckValue.setString(1, value);
			   
			  ResultSet result = prepareCheck.executeQuery();
			 //System.out.println(getKeyFromDatabase(value, sqlQuery, sqlInsert));
			 return result.getInt(1);
		  } else {
			  return resultCheckCountry.getInt(1);
		  }
	
	}
	
	
	private void insertIntoDatabase  (String sqlQuery, String value) throws SQLException{
		String insertQuery = sqlQuery;
		PreparedStatement prepareCheckValue = conn.prepareStatement(insertQuery);
		prepareCheckValue.setString(1, value);
		prepareCheckValue.executeUpdate();
		
	}

	private void generateColumnsMap(String splittedColumns[]) {
		// reading column titles to map, used for mapping to primary keys
		// hier vielleicht auch noch Kurzfassung wie VN_HS_B unterbringen
		for (int i = 0; i < splittedColumns.length; i++) {
			System.out.println(i + " " + splittedColumns[i]);
			if (splittedColumns[i].contains("VORNAME")
					|| splittedColumns[i].contains("NACHNAME")
					|| splittedColumns[i].contains("ORT")
					|| splittedColumns[i].contains("ORT")
					|| splittedColumns[i].contains("WIRT_LAGE")
					|| splittedColumns[i].contains("SEMINAR")
					|| splittedColumns[i].contains("ZUSAETZE")) {

				if (splittedColumns[i].contains("HS B")) {
					columnsMap.put(i, SourcesList.HS_B);
				} else if (splittedColumns[i].contains("HS C")) {
					columnsMap.put(i, SourcesList.HS_C);
				} else if (splittedColumns[i].contains("HS D")) {
					columnsMap.put(i, SourcesList.HS_D);
				} else if (splittedColumns[i].contains("HS E")) {
					columnsMap.put(i, SourcesList.HS_E);
				} else if (splittedColumns[i].contains("HS F")) {
					columnsMap.put(i, SourcesList.HS_F);
				} else if (splittedColumns[i].contains("HS G")) {
					columnsMap.put(i, SourcesList.HS_G);
				} else if (splittedColumns[i].contains("HS H")) {
					columnsMap.put(i, SourcesList.HS_H);
				} else if (splittedColumns[i].contains("HS I")) {
					columnsMap.put(i, SourcesList.HS_I);
				} else if (splittedColumns[i].contains("HS J")) {
					columnsMap.put(i, SourcesList.HS_J);
				} else if (splittedColumns[i].contains("V E 38")) {
					columnsMap.put(i, SourcesList.AUB_V);
				} else if (splittedColumns[i].contains("NORMAL")) {
					if(splittedColumns[i].contains("ABWEICH")){
						columnsMap.put(i, SourcesList.ABWEICH_NORMAL);
					}else
					columnsMap.put(i, SourcesList.NORMAL);
				} else {
					columnsMap.put(i, SourcesList.OHNE_ZUSATZ);
				}

			}
		}

	}

	private void writeFirstName(String firstName, int sourceId, int personId) {
		int firstNameKey = 0;
//		if (!firstNameMap.containsKey(firstName)) {
//			firstNameCounter++;
//			firstNameMap.put(firstName, firstNameCounter);
//			firstNameKey = firstNameCounter;
//
//		} else {
//			firstNameKey = firstNameMap.get(firstName);
//		}

	}
}
