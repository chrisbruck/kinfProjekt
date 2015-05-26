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


	private Map<String, Integer> firstNameMap = new HashMap<>();
	int firstNameCounter = 0;
	private static final int VORNAME_HS_B = 7;
	private static final int VORNAME_AUB = 16;
	private static final int NACHNAME_HS_B = 19;
	private static final int NACHNAME_AUB = 28;
	private static final int ORT_HS_B = 31;
	private static final int ORT_AUB = 40;
	private static final int WIRTLAGE_HS_B = 53;
	private static final int WIRTLAGE_HS_J = 55;
	private static final int SEMINAR_HS_B = 58;
	private static final int SEMINAR_HS_J = 62;
	private static final int ZUSATZ_HS_B = 67;
	private static final int ZUSATZ_AUB = 76;
	
	private static final String VORNAME_SIMPLE_INSERT = "";
	private static final String VORNAME_CONNECTED_INSERT = "INSERT INTO personen_vornamen(personen_id, vornamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String NACHNAME_SIMPLE_INSERT = "";
	private static final String NACHNAME_CONNECTED_INSERT = "INSERT INTO personen_nachnamen(personen_id, nachnamen_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String ORT_SIMPLE_INSERT = "";
	private static final String ORT_CONNECTED_INSERT = "INSERT INTO personen_ort(personen_id, ort_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String WIRTLAGE_SIMPLE_INSERT = "";
	private static final String WIRTLAGE_CONNECTED_INSERT = "INSERT INTO personen_wirtlage(personen_id, wirtlage_id,quellen_id) "
			+ " VALUES (?, ?, ?)";
	private static final String SEMINAR_SIMPLE_INSERT = "";
	private static final String SEMINAR_CONNECTED_INSERT = "INSERT INTO personen_seminar(personen_id, wirtlage_id,quellen_id) "
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

				// firstName Stuff
				int nameKey;
				String vorname = splittedRow[5];
				// firstNameKey = writeFirstName(vorname, columnsMap.get(7),
				// pid);
				String vornameNormal = splittedRow[6];
				iterateOverColumns(splittedRow, pid, VORNAME_HS_B, VORNAME_AUB, 
						VORNAME_SIMPLE_INSERT, VORNAME_CONNECTED_INSERT);
				iterateOverColumns(splittedRow, pid, NACHNAME_HS_B, NACHNAME_AUB,
						NACHNAME_SIMPLE_INSERT, NACHNAME_CONNECTED_INSERT );
				iterateOverColumns(splittedRow, pid, ORT_HS_B, ORT_AUB,
						ORT_SIMPLE_INSERT, ORT_CONNECTED_INSERT);
				iterateOverColumns(splittedRow, pid, WIRTLAGE_HS_B, WIRTLAGE_HS_J, 
						WIRTLAGE_SIMPLE_INSERT, WIRTLAGE_CONNECTED_INSERT);
				iterateOverColumns(splittedRow, pid, SEMINAR_HS_B, SEMINAR_HS_J,
						SEMINAR_SIMPLE_INSERT, SEMINAR_CONNECTED_INSERT);
				// lastName Stuff

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

	private void iterateOverColumns(String[] splittedRow, int pid, int start, int end, String sQLSimple,
			String sQLConnected){
		for (int j = start; j <= end; j++) {
			if (!splittedRow[j].equals("")) {
				int nameKey = getKeyFromDatabase(sQLSimple);
				int[] values = { pid, nameKey, columnsMap.get(j)};
				writeToConnectingTable(values, sQLConnected);
			}
		}
	
	}
	
	private void writeToConnectingTable(int[] values, String sql) {
		PreparedStatement preparedStatement;
		String insertString=sql;
		try {
			preparedStatement = conn.prepareStatement(insertString);
			preparedStatement.setInt(1, values[0]);
			preparedStatement.setInt(2, values[1]);
			preparedStatement.setInt(3, values[2]);
			// prepareInsertFirstName.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int getKeyFromDatabase(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}



	private void generateColumnsMap(String splittedColumns[]) {
		// reading column titles to map, used for mapping to primary keys
		// hier vielleicht auch noch Kurzfassung wie VN_HS_B unterbringen
		for (int i = 0; i < splittedColumns.length; i++) {
			System.out.println(i + " " + splittedColumns[i]);
			if (splittedColumns[i].contains("HS B")) {
				columnsMap.put(i, SourcesList.HS_B);
			} else if (splittedColumns[i].contains("HS C")) {
				columnsMap.put(i, SourcesList.HS_C);
			}

			else if (splittedColumns[i].contains("HS D")) {
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
			}
		}
	}
	
	private void generateSQLStatementMap(){
		
	}

	private void writeFirstName(String firstName, int sourceId, int personId) {
		int firstNameKey = 0;
		if (!firstNameMap.containsKey(firstName)) {
			firstNameCounter++;
			firstNameMap.put(firstName, firstNameCounter);
			firstNameKey = firstNameCounter;

		} else {
			firstNameKey = firstNameMap.get(firstName);
		}
	
	}
}
