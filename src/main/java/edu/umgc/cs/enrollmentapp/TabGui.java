package edu.umgc.cs.enrollmentapp;

import java.awt.*;
import javax.swing.*;

public class TabGui {
	private static JFrame frame = new JFrame();
	private JTabbedPane jtpTabPane = new JTabbedPane();
	private JPanel overviewTab = new JPanel();
	private JPanel financialInfoTab = new JPanel();
	private JPanel eligibilityFactorsTab = new JPanel();
	private JPanel enrollmentDecisionTab = new JPanel();

	public TabGui() {	

		createGUI();
	}



	private void createGUI() {
		frame = new JFrame("Enrollment Scholarship Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Display the frame
		//frame.pack();
		 frame.setSize(1000, 500);
		frame.setVisible(true);

		frame.add(jtpTabPane);
		jtpTabPane.add(overviewTab, "Student Overview");
		jtpTabPane.add(financialInfoTab, "Financial Information");
		jtpTabPane.add(eligibilityFactorsTab, "Eligibility Factors");
		jtpTabPane.add(enrollmentDecisionTab, "Enrollment Decision");

		jtpTabPane.setToolTipTextAt(0, "Overview Tab");
		jtpTabPane.setToolTipTextAt(1, "Financial Information Tab");
		jtpTabPane.setToolTipTextAt(2, "Eligibility Factors Tab");
		jtpTabPane.setToolTipTextAt(3, "Enrollment Decision Tab");
	}



//	/** Main method */
//	public static void main(String[] args) {
//	new TabGui();
//
//	}
}
