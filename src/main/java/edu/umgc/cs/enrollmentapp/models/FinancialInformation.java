package edu.umgc.cs.enrollmentapp.models;

public class FinancialInformation {
	private String studentID;
	private boolean isFinanDepended;
	private double studentIncomeLast;
	private double parentIncomeLast;
	private boolean have529Acc;
	private boolean ownRealEst;
	private double ValueOfOtherProperties;
	
	public void setDependency(boolean b){
		this.isFinanDepended = b;
	}
	
	public boolean getDependency(){
		return isFinanDepended;
	}
	
	public void setStudentIncome(double i){
		this.studentIncomeLast = i;
	}
	
	public double getStudentIncome(){
		return studentIncomeLast;
	}
	
	public void setParentIncome(double i){
		this.parentIncomeLast = i;
	}
	
	public double getParentIncome(){
		return parentIncomeLast;
	}
	
	public void set529Status(boolean b){
		this.have529Acc = b;
	}
	
	public boolean get529Status(){
		return have529Acc;
	}
	
	public void setRealStatus(boolean b){
		this.ownRealEst = b;
	}
	
	public boolean getRealStatus(){
		return ownRealEst;
	}
	
	public void setPropValue(double i){
		this.ValueOfOtherProperties = i;
	}
	
	public double getPropValue(){
		return ValueOfOtherProperties;
	}
}
