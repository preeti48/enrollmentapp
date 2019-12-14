package edu.umgc.cs.enrollmentapp.models;

/**
 * File: ESAInterface.java
 * Date: December 15, 2019
 * @author Pooja Patel
 * @author Olga Chandran
 * @author Preethi Suresh
 * @author Ngoc Nguyen 
 * @author Richard Bonolis
 * Purpose: This class implements interface for application that handles button action associated with all the tabs.
 */
public interface ESAInterface {
	
	void performUpdate(Applicant s);
	void performCancel();
	void performReset();
}
