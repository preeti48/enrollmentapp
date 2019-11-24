package edu.umgc.cs.enrollmentapp;

import java.awt.*;
import javax.swing.*;

import edu.umgc.cs.enrollmentapp.models.Applicant;

public class TabGui {
	private static JFrame frame = new JFrame();
	private JTabbedPane jtpTabPane = new JTabbedPane();
	private JPanel overviewTab = new JPanel();
	private JPanel financialInfoTab = new JPanel();
	private JPanel eligibilityFactorsTab = new JPanel();
	private JPanel enrollmentDecisionTab = new JPanel();
	private JTextField jtxStudentIDOverviewTab = new JTextField();
	private JTextField jtxStudentIDFinancialInfoTab = new JTextField();
	private JTextField jtxStudentIDEligibilityFactorsTab = new JTextField();
	private JTextField jtxStudentIDEnrollmentDecisionTab = new JTextField();
	private Applicant theApplicant  = null;

	public TabGui(Applicant applicant) {

		
		theApplicant = applicant;
		createGUI();
	}

	private void createGUI() {
		frame = new JFrame("Enrollment Scholarship Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Display the frame
		// frame.pack();

		jtpTabPane.add(overviewTab, "Student Overview");
		jtpTabPane.add(financialInfoTab, "Financial Information");
		jtpTabPane.add(eligibilityFactorsTab, "Eligibility Factors");
		jtpTabPane.add(enrollmentDecisionTab, "Enrollment Decision");

		jtpTabPane.setToolTipTextAt(0, "Overview Tab");
		jtpTabPane.setToolTipTextAt(1, "Financial Information Tab");
		jtpTabPane.setToolTipTextAt(2, "Eligibility Factors Tab");
		jtpTabPane.setToolTipTextAt(3, "Enrollment Decision Tab");
		jtxStudentIDOverviewTab.setText(theApplicant.getStudentID());
		jtxStudentIDFinancialInfoTab.setText(theApplicant.getStudentID());
		jtxStudentIDEligibilityFactorsTab.setText(theApplicant.getStudentID());
		jtxStudentIDEnrollmentDecisionTab.setText(theApplicant.getStudentID());
		overviewTab.add(jtxStudentIDOverviewTab);
		financialInfoTab.add(jtxStudentIDFinancialInfoTab);
		eligibilityFactorsTab.add(jtxStudentIDEligibilityFactorsTab);
		enrollmentDecisionTab.add(jtxStudentIDEnrollmentDecisionTab);
		frame.setSize(1000, 500);
		frame.setVisible(true);

		frame.add(jtpTabPane);
	}


}
