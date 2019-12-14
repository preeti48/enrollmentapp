package edu.umgc.cs.enrollmentapp.models;
import edu.umgc.cs.enrollmentapp.enums.*;
/**
 * File: EligibilityFactors.java
 * Date: December 15, 2019
 * @author Pooja Patel
 * @author Olga Chandran
 * @author Preethi Suresh
 * @author Ngoc Nguyen 
 * @author Richard Bonolis
 * Purpose: This class is to represent the eligibility factors for applicant.
 */
public class EligibilityFactors {
	private String studentID;
	private boolean everServedMilitary;
	private boolean militaryStatus;
	private ActiveYears activeYears;
	private boolean disabilityStatus;
	private boolean financialAidEligibility;
	
	private ResidencyStatus residencyStatus;
	private YearOfResidency yearOfResidency;
	public boolean isAgeOver55;
	public boolean areYouDepended;
	
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	public void setMiliServed(boolean b){
		this.everServedMilitary = b;
	}
	
	public boolean getMiliServed(){
		return this.everServedMilitary;
	}
	
	public void setMiliStatus(boolean b){
		this.militaryStatus = b;
	}
	
	public boolean getMiliStatus(){
		return this.militaryStatus;
	}
		
		
	public void setdisabilityStatus(boolean b){
		this.disabilityStatus = b;
	}
	
	public boolean getdisabilityStatus(){
		return this.disabilityStatus;
	}
	
	public void setFinAidElig(boolean b){
		this.financialAidEligibility = b;
	}
	
	public boolean getFinAidElig(){
		return this.financialAidEligibility;
	}
	
	public void setActiveYears(ActiveYears s){
		this.activeYears = s;
	}
	
	public ActiveYears getActiveYears(){
		return this.activeYears;
	}
	
	public void setResidencyYears(YearOfResidency s){
		this.yearOfResidency = s;
	}
	
	public YearOfResidency getResidencyYears(){
		return this.yearOfResidency;
	}
	
	public void setResidencystatus(ResidencyStatus s){
		this.residencyStatus= s;
	}
	
	public ResidencyStatus getResidencyStatus(){
		return this.residencyStatus;
	}
		
	
}
