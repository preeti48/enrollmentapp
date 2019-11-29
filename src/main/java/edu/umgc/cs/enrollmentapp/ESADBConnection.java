package edu.umgc.cs.enrollmentapp;

import javax.swing.*;

import edu.umgc.cs.enrollmentapp.enums.ActiveYears;
import edu.umgc.cs.enrollmentapp.enums.ResidencyStatus;
import edu.umgc.cs.enrollmentapp.enums.YearOfResidency;
import edu.umgc.cs.enrollmentapp.models.Applicant;
import edu.umgc.cs.enrollmentapp.models.EligibilityFactors;
import edu.umgc.cs.enrollmentapp.models.EnrollmentDecision;
import edu.umgc.cs.enrollmentapp.models.FinancialInformation;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String [] data = new String [columnsNumber];
			
			   while (rs.next()) {
			       for (int i = 1; i <= columnsNumber; i++) {
			       
			           data[i-1]= rs.getString(i);
			              
			       }
			     
			   }	
			
			 
			applicant = setStudent(applicant, data);
			setFinancialInfo(applicant);
			setEligibilityInfo(applicant);
			getEnrollmentDecision(applicant);
			return applicant;

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	private static void setFinancialInfo(Applicant student){
		student.finInfo = new FinancialInformation();
		  String  finquery = "Select * from FinancialInformation where Student_ID = " + student.getStudentID();
			try {

				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(finquery);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				String [] finData = new String [columnsNumber];
						
				   while (rs.next()) {
				       for (int i = 1; i <= columnsNumber; i++) {
				         
				           finData[i-1]= rs.getString(i);
								       }
				   }	
				
				  /* for (String s: finData)
						System.out.println(s);*/
				//  System.out.println(radioHandle(finData[2]));
				  student.finInfo.setDependency(radioHandle(finData[2]));
				  student.finInfo.setStudentIncome(Double.parseDouble(finData[3]));
				  student.finInfo.setParentIncome(Double.parseDouble(finData[4]));
				  student.finInfo.set529Status(radioHandle(finData[5]));
				  student.finInfo.setRealStatus(radioHandle(finData[6]));
				  student.finInfo.setPropValue(Double.parseDouble(finData[7]));
				 
				   

			} catch (SQLException ex) {
				System.out.println("Get Student exception " + ex.getMessage());
			}
	}
	
	private static void setEligibilityInfo(Applicant student){
		student.eligInfo = new EligibilityFactors();
		  String  eligquery = "Select * from EligibilityFactors where Student_ID = " + student.getStudentID();
			try {

				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(eligquery);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				String [] eligData = new String [columnsNumber];
						
				   while (rs.next()) {
				       for (int i = 1; i <= columnsNumber; i++) {
				         
				           eligData[i-1]= rs.getString(i);
								       }
				   }	
				
				 /*  for (String s: eligData)
						System.out.println(s);*/
				 // System.out.println(radioHandle(finData[2]));
				  student.eligInfo.setMiliServed(radioHandle(eligData[2]));
				  student.eligInfo.setMiliStatus(radioHandle(eligData[3]));
				  student.eligInfo.setActiveYears(getYears(eligData[4]));
				  student.eligInfo.setdisabilityStatus(radioHandle(eligData[5]));
				  student.eligInfo.setFinAidElig(radioHandle(eligData[6]));
				  student.eligInfo.setResidencystatus(getStatus(eligData[7]));
				  student.eligInfo.setResidencyYears(getResiYears(Integer.parseInt(eligData[8])));
				 
				//  student.eligInfo.isAgeOver55 = radioHandle(eligData[8]);
				  
				  student.eligInfo.areYouDepended = radioHandle(eligData[9]);
	
				   

			} catch (SQLException ex) {
				System.out.println("Get Student exception " + ex.getMessage());
			}
	}
	
	private static void getEnrollmentDecision(Applicant student){
		student.enrollDecision = new EnrollmentDecision();
		  String  desquery = "Select * from EnrollMentDecision where Student_ID = " + student.getStudentID();
			try {

				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(desquery);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				String [] enrollData = new String [columnsNumber];
						
				   while (rs.next()) {
				       for (int i = 1; i <= columnsNumber; i++) {
				         
				           enrollData[i-1]= rs.getString(i);
								       }
				   }	
				
				 /*  for (String s: enrollData)
						System.out.println(s);*/
				   student.enrollDecision.setEnrollDate(stringToDate(enrollData[2]));
				   student.enrollDecision.setGroup(Integer.parseInt(enrollData[3]));
				
				   

			} catch (SQLException ex) {
				System.out.println("Get Student exception " + ex.getMessage());
			}
	}
	
private static ResidencyStatus getStatus(String s){
		
		if (s.equals("In-State")){
			return ResidencyStatus.InState;
		}
		else  if(s.equals("Out of State")){
			return ResidencyStatus.OutOfState;
		}
		else if(s.equals("Abroad")){
			return ResidencyStatus.Abroad;
		}
		else{
			return null;
		}
			
	}
	
	
private static YearOfResidency getResiYears(int a){
		
		if (a < 1){
			return YearOfResidency.LessThanOneYears;
		}
		else if(a >=1 && a <=5){
			return YearOfResidency.BetweenOneAndFveYears;
		}
		else if (a > 5){
			return YearOfResidency.Over5Years;
		}
		else{
			return null;
		}
			
	}
	
	private static ActiveYears getYears(String s){
		
		if (s.equals("Less than 1")){
			return ActiveYears.LessThanOneYears;
		}
		else if(s.equals("Between 1 to 5")){
			return ActiveYears.BetweenOneAndFveYears;
		}
		else{
			return ActiveYears.Over5Years;
		}
			
	}
	
	private static Applicant setStudent(Applicant student, String[] record){
	/*	for (String s: record)
			System.out.println(s);*/
		student.setStudentID(record[0]);
		student.setSsn(Integer.parseInt(record[1]));
		student.setLname(record[2]);
		student.setFname(record[3]);
		student.setDob(stringToDate(record[4]));
		student.setGender(record[5]);
		student.setEmergencyContact(record[6]);
		student.setE_phone(record[7]);
		student.setStreet(record[8]);
		student.setCity(record[9]);
		student.setState(record[10]);
		student.setZip(Integer.parseInt(record[11]));
		student.setUsaResident(radioHandle(record[12]));
	   student.setPhone(record[13]);
		
		
	return student;
		
	}
	
	 private static boolean radioHandle(String gen){
  	   if(gen.equals("Yes") || gen.equals("1")){
  			   
  		   return true;
  	   }
  	   else {
  		     
  		   return false;
  	   }
  	  
     }

	private static Date stringToDate(String s){
		 Date date = null;
		try {
			date = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(s);
			return date;  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}
	
	private static String generateUniqueStudentID() {
		String newStudentID = "";
		return newStudentID;
		// generate student id 7 digits
		// check database if this student ID already exist
		// regenerate

	}
}
