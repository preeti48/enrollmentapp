package edu.umgc.cs.enrollmentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import edu.umgc.cs.enrollmentapp.enums.ActiveYears;
import edu.umgc.cs.enrollmentapp.enums.ResidencyStatus;
import edu.umgc.cs.enrollmentapp.enums.YearOfResidency;
import edu.umgc.cs.enrollmentapp.models.Applicant;
import edu.umgc.cs.enrollmentapp.models.EligibilityFactors;
import edu.umgc.cs.enrollmentapp.models.EnrollmentDecision;
import edu.umgc.cs.enrollmentapp.models.FinancialInformation;

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
			

			if (rs.isBeforeFirst()){

				
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String[] data = new String[columnsNumber];

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {

					data[i - 1] = rs.getString(i);

				}

			}

			applicant = setStudent(applicant, data);
			setFinancialInfo(applicant);
			setEligibilityInfo(applicant);
			getEnrollmentDecision(applicant);

			applicant.isFound = true;
			return applicant;
			}
			
			else{
				applicant.isFound = false;
				return applicant;
			}

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	private static void setFinancialInfo(Applicant student) {
		student.finInfo = new FinancialInformation();
		String finquery = "Select * from FinancialInformation where Student_ID = " + student.getStudentID();
		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(finquery);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String[] finData = new String[columnsNumber];

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {

					finData[i - 1] = rs.getString(i);
				}
			}

			/*
			 * for (String s: finData) System.out.println(s);
			 */
			// System.out.println(radioHandle(finData[2]));
			student.finInfo.setDependency(radioHandle(finData[2]));
			if(finData[3]!= null)
				student.finInfo.setStudentIncome(Double.parseDouble(finData[3]));
			if (finData[4] != null)
				student.finInfo.setParentIncome(Double.parseDouble(finData[4]));
			//else // finData[4] == null
			student.finInfo.set529Status(radioHandle(finData[5]));
			student.finInfo.setRealStatus(radioHandle(finData[6]));
			if(finData[7]!=null)
				student.finInfo.setPropValue(Double.parseDouble(finData[7]));

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
	}

	private static void setEligibilityInfo(Applicant student) {
		student.eligInfo = new EligibilityFactors();
		String eligquery = "Select * from EligibilityFactors where Student_ID = " + student.getStudentID();
		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(eligquery);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String[] eligData = new String[columnsNumber];

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {

					eligData[i - 1] = rs.getString(i);
				}
			}

			/*
			 * for (String s: eligData) System.out.println(s);
			 */
			// System.out.println(radioHandle(finData[2]));
			student.eligInfo.setMiliServed(radioHandle(eligData[2]));
			student.eligInfo.setMiliStatus(radioHandle(eligData[3]));
			student.eligInfo.setActiveYears(getYears(eligData[4]));
			student.eligInfo.setdisabilityStatus(radioHandle(eligData[5]));
			student.eligInfo.setFinAidElig(radioHandle(eligData[6]));
			student.eligInfo.setResidencystatus(getStatus(eligData[7]));
			if (eligData[8] != null)
				student.eligInfo.setResidencyYears(getResiYears(Integer.parseInt(eligData[8])));
			else // eligData[8] == null
				student.eligInfo.setResidencyYears(null);

			// student.eligInfo.isAgeOver55 = radioHandle(eligData[8]);

			student.eligInfo.areYouDepended = radioHandle(eligData[9]);

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
	}

	private static void getEnrollmentDecision(Applicant student) {
		if (student != null) {
			student.enrollDecision = new EnrollmentDecision();
			String desquery = "Select * from EnrollMentDecision where Student_ID = " + student.getStudentID();
			try {

				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(desquery);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				String[] enrollData = new String[columnsNumber];

				while (rs.next()) {
					for (int i = 1; i <= columnsNumber; i++) {

						enrollData[i - 1] = rs.getString(i);
					}
				}

				/*
				 * for (String s: enrollData) System.out.println(s);
				 */
				student.enrollDecision.setEnrollDate(stringToDate(enrollData[2]));
				if(enrollData[3] !=null)
					student.enrollDecision.setGroup(Integer.parseInt(enrollData[3]));

			} catch (SQLException ex) {
				System.out.println("Get Student exception " + ex.getMessage());
			}
		}

	}

	private static ResidencyStatus getStatus(String s) {

		if (s != null) {
			if (s.equals("In-State")) {
				return ResidencyStatus.InState;
			} else if (s.equals("Out of State")) {
				return ResidencyStatus.OutOfState;
			} else if (s.equals("Abroad")) {
				return ResidencyStatus.Abroad;
			} else {
				return ResidencyStatus.NoStatus;
			}
		} else
			return ResidencyStatus.NoStatus;// if s is null

	}

	private static YearOfResidency getResiYears(int a) {

		if (a < 1) {
			return YearOfResidency.LessThanOneYears;
		} else if (a >= 1 && a <= 5) {
			return YearOfResidency.BetweenOneAndFveYears;
		} else if (a > 5) {
			return YearOfResidency.Over5Years;
		} else {
			return null;
		}

	}

	private static ActiveYears getYears(String s) {

		ActiveYears returnResult = ActiveYears.NoActiveYears;
		if (s != null) {
			if (s.equals("Less than 1")) {

				returnResult = ActiveYears.LessThanOneYears;
			} else if (s.equals("Between 1 to 5")) {
				returnResult = ActiveYears.BetweenOneAndFveYears;
			} else {
				returnResult = ActiveYears.Over5Years;
			}
		}
		return returnResult; // checks if s is not null; if null, database field years of service is null and
								// default value is NoActiveYears

	}

	private static Applicant setStudent(Applicant student, String[] record) {
		/*
		 * for (String s: record) System.out.println(s);
		 */
		student.setStudentID(record[0]);
		if(record[1] !=null)
			student.setSsn(Integer.parseInt(record[1]));
		student.setLname(record[2]);
		student.setFname(record[3]);
		if(record[4] !=null)
			student.setDob(stringToDate(record[4]));
		student.setGender(record[5]);
		student.setEmergencyContact(record[6]);
		student.setE_phone(record[7]);
		student.setStreet(record[8]);
		student.setCity(record[9]);
		student.setState(record[10]);
		if(record[11] !=null)
			student.setZip(Integer.parseInt(record[11]));
		student.setUsaResident(radioHandle(record[12]));
		student.setPhone(record[13]);

		return student;

	}

	private static boolean radioHandle(String gen) {
		boolean returnBool = false;
		if (gen != null) {

			if (gen.equals("Yes") || gen.equals("1")) {

				returnBool = true;
			} else {

				returnBool = false;
			}

		}
		return returnBool;// if gen is null, returnBool will be false;

	}// end of radioHandle

	private static Date stringToDate(String s) {
		Date date = null;
		
		try {
			if(s!=null)
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

	public static Applicant checkIfstudentExists(String ssn, String lName, String fName, String dOB) {

		Applicant applicant = new Applicant();
		// first check if students exists

		String query = "Select * from Student where last_name = \'" + lName + "\' and ssn =\'" + ssn + "\'";

		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {

				applicant.setStudentID(rs.getString("student_id"));

			} else if (rs == null)// student does not exist, create new student later
			{
				applicant.setStudentID(null);
				int ssnInt = Integer.parseInt(ssn);
				applicant.setSsn(ssnInt);
				applicant.setLname(lName);
				applicant.setFname(fName);
				// Olga applicant.setDob(dOB));
				applicant.setGender("Other");
			}

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	public static Applicant addStudent(String ssn, String lName, String fName, java.util.Date date) {

		Applicant applicant = new Applicant();
		// first check if students exists

		String generatedStudentID = generateStudentID();
		//String dobStr = "12/12/1990";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = dateFormat.format(date);
		
		//java.sql.Date db = new java.sql.Date
		
		String insertStatement = "INSERT INTO Student (Student_ID,SSN,Last_Name,First_Name, DOB,Birth_Sex) VALUES (\'"+ generatedStudentID + "\', \'"+ ssn + "\',\'"+lName +"\', \'"+fName+"\'," +" \'"+dateString + " \', \'Other\');";

		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			int resultCode = stmt.executeUpdate(insertStatement);
			//conn.commit();
			
			if(resultCode == 1)
			{
				applicant.setStudentID(null);
				int ssnInt = Integer.parseInt(ssn);
				applicant.setStudentID(generatedStudentID);
				applicant.setSsn(ssnInt);
				applicant.setLname(lName);
				applicant.setFname(fName);
				applicant.setDob(date);
				applicant.setGender("Other");
								
			}

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	public String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat formatedDate = new SimpleDateFormat("mm/dd/yyyy");

		try {
			dateString = formatedDate.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
	
	public static String generateStudentID()
	{
		String number = null;
		
		// create instance of Random class 
        Random rand = new Random(); 
  
        
        int n = 1000000 + rand.nextInt(9000000);
        number = String.valueOf(n);
		
		return number; 
	}
	
}