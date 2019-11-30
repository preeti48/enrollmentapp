package edu.umgc.cs.enrollmentapp.models;

import java.util.Date;

public class Applicant {
	public String studentID;

	public String Fname;
	public String Lname;
	public int ssn;
	public Date dob;
	public String gender;
	public String emergencyContact;
	public String e_phone;
	public String ResiAddress;
	public String street;
	public String city;
	public String state;
	public int zip;
	public boolean isUsaResident;
	public String phone;
	public FinancialInformation finInfo;
	public EligibilityFactors eligInfo;
	public EnrollmentDecision enrollDecision;

	public boolean isFound;//Pooja
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getE_phone() {
		return e_phone;
	}

	public void setE_phone(String e_phone) {
		this.e_phone = e_phone;
	}

	public String getResiAddress() {
		return ResiAddress;
	}

	public void setResiAddress(String resiAddress) {
		ResiAddress = resiAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public boolean isUsaResident() {
		return isUsaResident;
	}

	public void setUsaResident(boolean isUsaResident) {
		this.isUsaResident = isUsaResident;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
//Phone    # Text Box Numeric 10
//Update   Button Selection
//Reset    Button Selection
//Cancel