
package edu.umgc.cs.enrollmentapp;

import java.io.*;
import java.sql.*;

import javax.swing.JFileChooser;
/**
 * File: Reports.java
 * Date: December 15, 2019
 * @author Pooja Patel
 * @author Olga Chandran
 * @author Preethi Suresh
 * @author Ngoc Nguyen 
 * @author Richard Bonolis
 * Purpose: This class generates the report for individual group and handles all the activity associated with View Reports button.
 */
public class Reports {
	static Connection conn = null;
	static String url = "jdbc:sqlite:ESA.db";

	public static Connection dbConnector() throws ClassNotFoundException {

		try {

			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return conn;
	}

	public static void generateGroup1Report() {
		// Select students form two tables for Group1
		String query = "Select Student.Student_ID, Student.First_Name, Student.Last_Name, Student.Birth_Sex, EnrollmentDecision.Group_Description\r\n"
				+ "FROM EnrollmentDecision\r\n" + "JOIN Student\r\n"
				+ "ON EnrollmentDecision.Student_ID = Student.Student_ID\r\n"
				+ "WHERE EnrollmentDecision.Group_Number = '1'; ";

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("Group1Enrollment.csv"));
			fileChooser.setDialogTitle("Specify a file to save");

			createReport(query, fileChooser);
		} catch (

		SQLException ex) {
			System.out.println("Report Group 1 SQL error: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report Group 1 File error: " + e.getMessage());
		}

	}
	public static void generateGroup2Report() {
		// Select students form two tables for Group2
		String query = "Select Student.Student_ID, Student.First_Name, Student.Last_Name, Student.Birth_Sex, EnrollmentDecision.Group_Description\r\n"
				+ "FROM EnrollmentDecision\r\n" + "JOIN Student\r\n"
				+ "ON EnrollmentDecision.Student_ID = Student.Student_ID\r\n"
				+ "WHERE EnrollmentDecision.Group_Number = '2'; ";

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("Group2Enrollment.csv"));
			fileChooser.setDialogTitle("Specify a file to save");

			createReport(query, fileChooser);
		} catch (

		SQLException ex) {
			System.out.println("Report Group 2 SQL error: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report Group 2 File error: " + e.getMessage());
		}

	}
	public static void generateGroup3Report() {
		// Select students form two tables for Group3
		String query = "Select Student.Student_ID, Student.First_Name, Student.Last_Name, Student.Birth_Sex, EnrollmentDecision.Group_Description\r\n"
				+ "FROM EnrollmentDecision\r\n" + "JOIN Student\r\n"
				+ "ON EnrollmentDecision.Student_ID = Student.Student_ID\r\n"
				+ "WHERE EnrollmentDecision.Group_Number = '3'; ";

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("Group3Enrollment.csv"));
			fileChooser.setDialogTitle("Specify a file to save");

			createReport(query, fileChooser);
		} catch (

		SQLException ex) {
			System.out.println("Report Group 3 SQL error: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report Group 3 File error: " + e.getMessage());
		}

	}
	public static void generateGroup4Report() {
		// Select students form two tables for Group4
		String query = "Select Student.Student_ID, Student.First_Name, Student.Last_Name, Student.Birth_Sex, EnrollmentDecision.Group_Description\r\n"
				+ "FROM EnrollmentDecision\r\n" + "JOIN Student\r\n"
				+ "ON EnrollmentDecision.Student_ID = Student.Student_ID\r\n"
				+ "WHERE EnrollmentDecision.Group_Number = '4'; ";

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("Group4Enrollment.csv"));
			fileChooser.setDialogTitle("Specify a file to save");

			createReport(query, fileChooser);
		} catch (

		SQLException ex) {
			System.out.println("Report Group 4 SQL error: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report Group 4 File error: " + e.getMessage());
		}

	}
	public static void generateGroup5Report() {
		// Select students form two tables for Group5
		String query = "Select Student.Student_ID, Student.First_Name, Student.Last_Name, Student.Birth_Sex, EnrollmentDecision.Group_Description\r\n"
				+ "FROM EnrollmentDecision\r\n" + "JOIN Student\r\n"
				+ "ON EnrollmentDecision.Student_ID = Student.Student_ID\r\n"
				+ "WHERE EnrollmentDecision.Group_Number = '5'; ";

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("Group5Enrollment.csv"));
			fileChooser.setDialogTitle("Specify a file to save");

			createReport(query, fileChooser);
		} catch (

		SQLException ex) {
			System.out.println("Report Group 5 SQL error: " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Report Group 5 File error: " + e.getMessage());
		}

	}
	// This method generates the report
	private static void createReport(String query, JFileChooser fileChooser) throws IOException, SQLException {
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {

			File csvOutputFile = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + csvOutputFile.getAbsolutePath());
			FileWriter fw = new FileWriter(csvOutputFile, false);
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String[] columns = new String[] { "Student ID", "First Name", "Last Name", "Birth Sex",
						"Group Description" };
				for (String mapping : columns) {
					fw.write(mapping + ',');
				}
				fw.append("\n");
				fw.append(rs.getString(1));
				fw.append(',');
				fw.append(rs.getString(2).toUpperCase());
				fw.append(',');
				fw.append(rs.getString(3).toUpperCase());
				fw.append(',');
				fw.append(rs.getString(4).toUpperCase());
				fw.append(',');
				fw.append(rs.getString(5));
				fw.append('\n');
			}
			fw.flush();
			fw.close();
			conn.close();
			System.out.println("CSV File is created successfully.");
		}
	}

}
