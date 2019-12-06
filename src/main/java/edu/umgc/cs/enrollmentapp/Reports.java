
package edu.umgc.cs.enrollmentapp;

import java.io.*;
import java.sql.*;

import javax.swing.JFileChooser;

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
					fw.append(rs.getString(2));
					fw.append(',');
					fw.append(rs.getString(3));
					fw.append(',');
					fw.append(rs.getString(4));
					fw.append(',');
					fw.append(rs.getString(5));
					fw.append(',');
					fw.append('\n');
				}
				fw.flush();
				fw.close();
				conn.close();
				System.out.println("CSV File is created successfully.");
			}
		} catch (

		SQLException ex) {
			System.out.println("Reports for group 1 " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Reports for group 1 " + e.getMessage());
		}

	}

}
