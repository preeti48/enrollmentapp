package edu.umgc.cs.enrollmentapp.models;

import java.util.Date;

public class EnrollmentDecision {

	private String studentID;
	private Date enrollmentDate;
	private int groupNumber;
	private String grpDiscription;
	
	public void setEnrollDate(Date d){
		this.enrollmentDate = d;
	}
	
	public Date getEnrollDate(){
		return this.enrollmentDate;
	}
	
	public void setGroup(int a){
		this.groupNumber = a;
	}
	
	public int getGroup(){
		return this.groupNumber;
	}
	
	public void serGrpDiscription(String s){
		this.grpDiscription = s;
	}
	
	public String getGrpDiscription(){
		return this.grpDiscription;
	}
}
