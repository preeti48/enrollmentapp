package edu.umgc.cs.enrollmentapp;

import javax.swing.*;

import edu.umgc.cs.enrollmentapp.models.Applicant;

import java.sql.*;
import java.util.ArrayList;
public class ESADBConnection {
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

	public static Applicant searchByStudentID(String studentID) {
		Applicant applicant = new Applicant();
		String query = "Select * from Student where student_ID = " + studentID;
		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String result2 = rs.getString(2);
			String result3 = rs.getString(3);
			applicant.setStudentID(studentID);
			;
			System.out.println("Get Student test " + studentID + " result 2 " + result2);

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	private static String generateUniqueStudentID() {
		String newStudentID = "";
		return newStudentID;
		// generate student id 7 digits
		// check database if this student ID already exist
		// regenerate

	}
}
