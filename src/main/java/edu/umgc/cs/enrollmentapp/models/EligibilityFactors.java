package edu.umgc.cs.enrollmentapp.models;
import edu.umgc.cs.enrollmentapp.enums.*;

public class EligibilityFactors {
	private String studentID;
	public boolean everServedMilitary;
	public boolean militaryStatus;
	public ActiveYears activeYears;
	public boolean disabilityStatus;
	public boolean financialAidEligibility;
	
	public ResidencyStatus residencyStatus;
	public YearOfResidency yearOfResidency;
	public boolean isAgeOver55;
	public boolean areYouDepended;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
		
}
