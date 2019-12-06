package edu.umgc.cs.enrollmentapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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

			if (rs.isBeforeFirst()) {

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

			else {
				applicant.isFound = false;
				return applicant;
			}

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}

		return applicant;
	}

	public static Applicant searchBySSNandLname(String ssn , String ln) {
		Applicant applicant = new Applicant();
		// first check if students exists

		String query = "Select * from Student where last_name = \'" + ln + "\' and ssn =\'" + ssn + "\'";

		try {

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.isBeforeFirst()) {

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

			else {
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
			if (finData[3] != null)
				student.finInfo.setStudentIncome(Double.parseDouble(finData[3]));
			if (finData[4] != null)
				student.finInfo.setParentIncome(Double.parseDouble(finData[4]));
			// else // finData[4] == null
			student.finInfo.set529Status(radioHandle(finData[5]));
			student.finInfo.setRealStatus(radioHandle(finData[6]));
			if (finData[7] != null)
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
				student.eligInfo.setResidencyYears(getResiYears(eligData[8]));
			else // eligData[8] == null
				student.eligInfo.setResidencyYears(null);

			// student.eligInfo.isAgeOver55 = radioHandle(eligData[8]);'
			
			//preethi
			LocalDate today = LocalDate.now(); //Today's date
			LocalDate birthday = student.getDob().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
			
			Period period = Period.between(birthday, today);//Finding age
			
			student.eligInfo.isAgeOver55 = period.getYears() > 55;
	
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
				if (enrollData[3] != null)
					student.enrollDecision.setGroup(Integer.parseInt(enrollData[3]));
				student.enrollDecision.serGrpDiscription(enrollData[4]);

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

	private static YearOfResidency getResiYears(String s) {

		YearOfResidency returnResult = YearOfResidency.NoYearsOfResidency;
		if (s != null) {
			if (s.equals("Less than 1")) {

				returnResult = YearOfResidency.LessThanOneYears;
			} else if (s.equals("Between 1 to 5")) {
				returnResult = YearOfResidency.BetweenOneAndFveYears;
			} else {
				returnResult = YearOfResidency.Over5Years;
			}
		}
		return returnResult;	

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
		if (record[1] != null)
			student.setSsn(Integer.parseInt(record[1]));
		student.setLname(record[2]);
		student.setFname(record[3]);
		if (record[4] != null)
			student.setDob(stringToDate(record[4]));
		student.setGender(record[5]);
		student.setEmergencyContact(record[6]);
		student.setE_phone(record[7]);
		student.setStreet(record[8]);
		student.setCity(record[9]);
		student.setState(record[10]);
		if (record[11] != null)
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
       // System.out.println(s);
		try {
			if (s != null)
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

			} else if (rs == null) {// student does not exist, create new student later

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
		String generatedStudentID = null;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = dateFormat.format(date);

		String insertStatement = null;

		try {

			conn = DriverManager.getConnection(url);
			
			generatedStudentID = generateStudentID(conn);
			insertStatement = "INSERT INTO Student (Student_ID,SSN,Last_Name,First_Name, DOB,Birth_Sex) VALUES (\'"
					+ generatedStudentID + "\', \'" + ssn + "\',\'" + lName + "\', \'" + fName + "\'," + " \'" + dateString
					+ " \', \'Other\');";

			Statement stmt = conn.createStatement();
			int resultCode = stmt.executeUpdate(insertStatement);
			// conn.commit();

			if (resultCode == 1) {
				populateEligibilityFactorsAddStudent(generatedStudentID, conn);
				populateEnrollmentDecision(generatedStudentID, conn);
				populateFinancialInformation(generatedStudentID, conn);
			}

			if (resultCode == 1) {
				// applicant.setStudentID(null);
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

	public static String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy");

		try {
			dateString = formatedDate.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}

	public static String generateStudentID(Connection conn) {
		String number = null;

		// create instance of Random class
		Random rand = new Random();

		int n = -1; // if number exist 
		// loop to check if new number should be generated 
		boolean regenerate = true;
		while (regenerate)
		{
			n = 1000000 + rand.nextInt(9000000);
			number = String.valueOf(n);

		// check if it existis , otherwise regenerate 
			// if student id does not exist, leave the loop 
			String query = "Select * from Student where student_id=\'" + n + "\'";

			try {

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if (rs != null && rs.next()) {

					regenerate  = true; 

				} 
				else // rs == null
					regenerate = false; 
		} catch (SQLException ex) {
			System.out.println("generate student id  exception " + ex.getMessage());
		}

		}
		return number;
	}

	private static int populateEligibilityFactorsAddStudent(String studentID, Connection conn) {

		String query = "Select max(Eligibility_Information_ID) from EligibilityFactors ";
		int tempIDint = 1;
		int count = -1;// if no records are inserted
		try {

			// conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// if there is a record in the EligibilityTable, get the maximum value for the
			// ID and add 1, otherwise 1 since there are no record
			if (rs != null) {

				String tempID = rs.getString(1);

				if (tempID != null) {
					tempIDint = Integer.parseInt(tempID);
					tempIDint = tempIDint + 1;
				}
			}

		} catch (SQLException ex) {
			System.out.println("select max value for eligibilityFactors ID  " + ex.getMessage());
		}

		// insert record to the database for studentID

		String enrollmentIDstr = Integer.toString(tempIDint);
		String insertStatement = "INSERT INTO EligibilityFactors  (Eligibility_Information_ID, Student_ID) VALUES (\'"
				+ enrollmentIDstr + "\', \'" + studentID + "\');";

		try {

			// conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			count = stmt.executeUpdate(insertStatement);
			// conn.commit();

		} catch (SQLException ex) {
			System.out.println("Insert new student to eligibility factors table  " + ex.getMessage());
		}
		return count;
	}

	private static int populateEnrollmentDecision(String studentID, Connection conn) {

		String query = "Select max(Enrollment_ID) from EnrollmentDecision ";
		int tempIDint = 1;
		int count = -1;// if no records are inserted
		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null) {

				String tempID = rs.getString(1);

				if (tempID != null) {
					tempIDint = Integer.parseInt(tempID);
					tempIDint = tempIDint + 1;
				}
			}

		} catch (SQLException ex) {
			System.out.println("select max value for Enrollment_ID  " + ex.getMessage());
		}

		// insert record to the database for studentID

		String enrollmentIDstr = Integer.toString(tempIDint);
		String insertStatement = "INSERT INTO EnrollmentDecision   (Enrollment_ID, Student_ID) VALUES (\'"
				+ enrollmentIDstr + "\', \'" + studentID + "\');";

		try {

			Statement stmt = conn.createStatement();
			count = stmt.executeUpdate(insertStatement);

		} catch (SQLException ex) {
			System.out.println("Insert new student to enrollment decision table  " + ex.getMessage());
		}
		return count;
	}

	private static int populateFinancialInformation(String studentID, Connection conn) {

		String query = "Select max(Financial_Information_ID) from FinancialInformation  ";
		int tempIDint = 1;
		int count = -1;// if no records are inserted
		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (rs != null) {

				String tempID = rs.getString(1);

				if (tempID != null) {
					tempIDint = Integer.parseInt(tempID);
					tempIDint = tempIDint + 1;
				}
			}

		} catch (SQLException ex) {
			System.out.println("select max value for Financial_Information_ID  " + ex.getMessage());
		}

		// insert record to the database for studentID

		String enrollmentIDstr = Integer.toString(tempIDint);
		String insertStatement = "INSERT INTO FinancialInformation  (Financial_Information_ID, Student_ID) VALUES (\'"
				+ enrollmentIDstr + "\', \'" + studentID + "\');";

		try {

			Statement stmt = conn.createStatement();
			count = stmt.executeUpdate(insertStatement);

		} catch (SQLException ex) {
			System.out.println("Insert new record to FinancialInformation  table  " + ex.getMessage());
		}
		return count;
	}
	
	/**
	 * This method updates the record in database.
	 * @param applicant is an applicant who's data needs to be updated
	 */
	public static void updateRecord(Applicant applicant){
		String query = "UPDATE student SET "
				       + " SSN =  '" + Integer.toString(applicant.getSsn())
				       +"', Last_Name = '" + applicant.getLname()
				       +"', First_Name = '" + applicant.getFname()
				       +"', DOB = '" + convertStringToDate(applicant.getDob())
				       +"', Birth_Sex = '" + applicant.getGender()
				       +"', Emergency_Contact = '" + applicant.getEmergencyContact()
				       +"', Emergency_Phone = '" + applicant.getE_phone()
				       +"', Student_Street = '" + applicant.getStreet()
				       +"', Student_City = '" + applicant.getCity()
				       +"', Student_State = '" + applicant.getState()
				       +"', Student_Zip = '" + Integer.toString(applicant.getZip())
				       +"', USA_Resident = '" + ((applicant.isUsaResident)? "yes" : "No") 
				       +"', Student_Phone = '" + applicant.getPhone()
				       +"' where student_id = " + applicant.getStudentID();
		
		try {

			conn = DriverManager.getConnection(url);
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("Database updated successfully ");
			
			String pquery = "Select * from Student where student_ID = " + applicant.getStudentID();

			

				conn = DriverManager.getConnection(url);
				Statement s = conn.createStatement();
				ResultSet resultSet = s.executeQuery(pquery);

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				//prints data to check update
				
				/*while (resultSet.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",\n");
				        String columnValue = resultSet.getString(i);
				        System.out.print(columnValue + ": " + rsmd.getColumnName(i));
				    }
				    System.out.println("");
				}
              		*/	
 
		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
		finally {
			try{
			  conn.close();
			  
			}
			catch(SQLException ex){
				System.out.println("Connection exception" + ex.getMessage());	
			}
		}
		

	}
	
	/**
	 * This method checks for a duplicate SSN in database
	 * @param ssn is SSN entered by user
	 * @return true is duplicate ssn exist, false otherwise
	 */
	public static boolean checkDuplicateSSN(String ssn){
		String query = "Select * from Student where SSN=\'" + ssn + "\'";
		boolean duplicate = false;
		try{
			conn = DriverManager.getConnection(url);
			Statement s = conn.createStatement();
			ResultSet resultSet = s.executeQuery(query);
			if(resultSet.isBeforeFirst()){
				//duplicate ssn exist
				duplicate = true;
			}
			else{
				duplicate = false;
			}
		}
		 catch (SQLException ex) {
				System.out.println("Get Student exception " + ex.getMessage());
			}
		return duplicate;
		
	}
	
	public static void updateFinancialRecord(Applicant applicant){
		String query = "UPDATE FinancialInformation SET "
				       + " Financially_Depended =  '" + ((applicant.finInfo.getDependency()) ? "1" : "0")
				       +"', Student_Last_Year_Income = '" + Double.toString(applicant.finInfo.getStudentIncome())
				       +"', [Parent_Last_ Year_Income] = '" + Double.toString(applicant.finInfo.getParentIncome())
				       +"', Own_529_Account = '" + (applicant.finInfo.get529Status()? "1" : "0")
				       +"', Own_Real_Estate_Land = '" + (applicant.finInfo.getRealStatus()? "1" : "0")
				       +"', Value_Of_Other_Properties = '" + Double.toString(applicant.finInfo.getPropValue())
				       +"' where student_id = " + applicant.getStudentID();
		
		try {

			conn = DriverManager.getConnection(url);
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("Database updated successfully ");
			
			String pquery = "Select * from FinancialInformation where student_ID = " + applicant.getStudentID();

			

				conn = DriverManager.getConnection(url);
				Statement s = conn.createStatement();
				ResultSet resultSet = s.executeQuery(pquery);

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				//prints data to check update
				
			/*	while (resultSet.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",\n");
				        String columnValue = resultSet.getString(i);
				        System.out.print(columnValue + ": " + rsmd.getColumnName(i));
				    }
				    System.out.println("");
				}*/
			

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
		finally {
			try{
			  conn.close();
			  
			}
			catch(SQLException ex){
				System.out.println("Connection exception" + ex.getMessage());	
			}
		}

	}
	
	public static void updateEligibilityRecord(Applicant applicant){
		String query = "UPDATE EligibilityFactors SET "
				       + " Served_In_Military =  '" + ((applicant.eligInfo.getMiliServed()) ? "1" : "0")
				       +"', Military_Status = '" + ((applicant.eligInfo.getMiliStatus()) ? "1" : "0")
				       +"', Active_Years = '" + applicant.eligInfo.getActiveYears()
				       +"', Disability_Status = '" + (applicant.eligInfo.getdisabilityStatus()? "1" : "0")
				       +"', Financial_Aid_Eligibility = '" + (applicant.eligInfo.getFinAidElig()? "1" : "0")
				       +"', Residency_Status = '" + applicant.eligInfo.getResidencyStatus()
				       +"', Years_of_Residency = '" + applicant.eligInfo.getResidencyYears()
				       +"', Dependent_Status = '" + ((applicant.eligInfo.getFinAidElig()) ? "1" : "0")
				       +"' where student_id = " + applicant.getStudentID();
		
		try {

			conn = DriverManager.getConnection(url);
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("Database updated successfully ");
			
			String pquery = "Select * from EligibilityFactors where student_ID = " + applicant.getStudentID();

			

				conn = DriverManager.getConnection(url);
				Statement s = conn.createStatement();
				ResultSet resultSet = s.executeQuery(pquery);

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				//prints data to check update
				
			/*	while (resultSet.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",\n");
				        String columnValue = resultSet.getString(i);
				        System.out.print(columnValue + ": " + rsmd.getColumnName(i));
				    }
				    System.out.println("");
			   }
			*/

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
		finally {
			try{
			  conn.close();
			  
			}
			catch(SQLException ex){
				System.out.println("Connection exception" + ex.getMessage());	
			}
		}

	}
	
	public static void updateEnrollmentRecord(Applicant applicant){
		String query = "UPDATE EnrollmentDecision SET "
				       + " Enrollment_Date =  '" + convertStringToDate(applicant.enrollDecision.getEnrollDate())
				       +"' where student_id = " + applicant.getStudentID();
		
		try {

			conn = DriverManager.getConnection(url);
			//Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("Database updated successfully ");
			
			String pquery = "Select * from EnrollmentDecision where student_ID = " + applicant.getStudentID();

			

				conn = DriverManager.getConnection(url);
				Statement s = conn.createStatement();
				ResultSet resultSet = s.executeQuery(pquery);

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				//prints data to check update
				
				while (resultSet.next()) {
				    for (int i = 1; i <= columnsNumber; i++) {
				        if (i > 1) System.out.print(",\n");
				        String columnValue = resultSet.getString(i);
				        System.out.print(columnValue + ": " + rsmd.getColumnName(i));
				    }
				    System.out.println("");
			   }
			

		} catch (SQLException ex) {
			System.out.println("Get Student exception " + ex.getMessage());
		}
		finally {
			try{
			  conn.close();
			  
			}
			catch(SQLException ex){
				System.out.println("Connection exception" + ex.getMessage());	
			}
		}

	}
	
	

}
