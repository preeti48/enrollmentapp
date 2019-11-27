package edu.umgc.cs.enrollmentapp;

import java.awt.*;
import javax.swing.*;

import edu.umgc.cs.enrollmentapp.models.Applicant;

public class TabGui extends JFrame{
	private static JFrame frame = new JFrame();
    private JTabbedPane jtpTabPane;
    private OverviewTab overviewTab;
	private FinancialInfoTab financialInfoTab;
	private EligibilityFactorsTab eligibilityFactorsTab;
    private EnrollmentDecisionTab enrollmentDecisionTab;

    public TabGui(Applicant applicant){
    	
        super("Enrollment Scholarship Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtpTabPane = new JTabbedPane();
        overviewTab = new OverviewTab();
        financialInfoTab = new FinancialInfoTab();
        eligibilityFactorsTab = new EligibilityFactorsTab();
        enrollmentDecisionTab = new EnrollmentDecisionTab();

        jtpTabPane.addTab("Student Overview", overviewTab);
        jtpTabPane.addTab("Financial Information", financialInfoTab);
        jtpTabPane.addTab("Eligibility Factors", eligibilityFactorsTab);
        jtpTabPane.addTab("Enrollment Decision", enrollmentDecisionTab);
        getContentPane().add(jtpTabPane);
        
        setSize(800, 500);
		setVisible(true);

    }
    
    private class OverviewTab extends JPanel{
        private JPanel buttonPanel = new JPanel(new FlowLayout());
        private JPanel centerPanel = new JPanel(new GridLayout(8,4, 20, 20));
        private JPanel sexRadioPanel = new JPanel(new FlowLayout());
        private JPanel resiRadioPanel = new JPanel(new FlowLayout());

        ButtonGroup buttongroup1 = new ButtonGroup();
        ButtonGroup buttongroup2 = new ButtonGroup();
       
        //private JPanel addressPanel = new JPanel(new FlowLayout());
        private JLabel studentIdLabel = new JLabel("Student ID:");
        private JTextField studentIDField = new JTextField(7);
        private JLabel resiAdrslabel = new JLabel("Residential Address");
        private JLabel resiAdrsEmpty = new JLabel("      ");
        
        private JLabel lastNameLabel = new JLabel("Last Name: ");
        private JTextField lastNameField = new JTextField();
        private JLabel streetLabel = new JLabel("Street: ");
        private JTextField streetField = new JTextField();
        private JLabel firstName = new JLabel("First Name:");
        private JTextField firstNameField = new JTextField();
        private JLabel cityLabel = new JLabel("City: ");
        private JTextField cityField = new JTextField();
        private JLabel ssnLabel = new JLabel("SSN: ");
        private JTextField ssnField = new JTextField();
        private JLabel stateLabel = new JLabel("State: ");
        private JTextField stateField = new JTextField();
        private JLabel dobLabel = new JLabel("DOB: ");
        private JTextField dobField = new JTextField();
        private JLabel zipLabel = new JLabel("Zip Code: ");
        private JTextField zipField = new JTextField();
        private JLabel birthSexLabel = new JLabel("Birth Sex: ");
        private JRadioButton birthSexRButtonM = new JRadioButton("M");
        private JRadioButton birthSexRButtonF = new JRadioButton("F");
        private JRadioButton birthSexRButtonO = new JRadioButton("Other");
        private JLabel usaResiLabel = new JLabel("USA Resident? ");
        private JRadioButton usaResiRButtonY = new JRadioButton("Yes");
        private JRadioButton usaResiRButtonN = new JRadioButton("No");
        private JLabel e_contactLabel = new JLabel("E - Contact: ");
        private JTextField e_contactField = new JTextField();
        private JLabel e_phoneLabel = new JLabel("E - Phone #: ");
        private JTextField e_phoneField = new JTextField();
        private JLabel phNumLAbel = new JLabel("Phone #: ");
        private JTextField phNumField = new JTextField();
        private JLabel  mobNumLabel= new JLabel("Mobile #: ");
        private JTextField mobNumField = new JTextField();
        
        private JButton update = new JButton("Update");
        private JButton reset = new JButton("Reset");
        private JButton cancel = new JButton("Cancel");


        private OverviewTab(){
            buttongroup1.add(birthSexRButtonM);
            buttongroup1.add(birthSexRButtonF);
            buttongroup1.add(birthSexRButtonO);
            buttongroup2.add(usaResiRButtonY);
            buttongroup2.add(usaResiRButtonN);

            setLayout(new BorderLayout());

            centerPanel.add(studentIdLabel);
            centerPanel.add(studentIDField);
            centerPanel.add(resiAdrslabel);
            centerPanel.add(resiAdrsEmpty);

            add(centerPanel, "Center");
            
            centerPanel.add(lastNameLabel);
            centerPanel.add(lastNameField);
            centerPanel.add(streetLabel);
            centerPanel.add(streetField);
            centerPanel.add(firstName);
            centerPanel.add(firstNameField);
            centerPanel.add(cityLabel);
            centerPanel.add(cityField);
            centerPanel.add(ssnLabel);
            centerPanel.add(ssnField);
            centerPanel.add(stateLabel);
            centerPanel.add(stateField);
            centerPanel.add(dobLabel);
            centerPanel.add(dobField);
            centerPanel.add(zipLabel);
            centerPanel.add(zipField);
            
            centerPanel.add(birthSexLabel);
            sexRadioPanel.add(birthSexRButtonM);
            sexRadioPanel.add(birthSexRButtonF);
            sexRadioPanel.add(birthSexRButtonO);
            centerPanel.add(sexRadioPanel);
            
            centerPanel.add(usaResiLabel);
            resiRadioPanel.add(usaResiRButtonY);
            resiRadioPanel.add(usaResiRButtonN);
            centerPanel.add(resiRadioPanel);
           
            centerPanel.add(e_contactLabel);
            centerPanel.add(e_contactField);
            centerPanel.add(e_phoneLabel);
            centerPanel.add(e_phoneField);
            centerPanel.add(phNumLAbel);
            centerPanel.add(phNumField);
            centerPanel.add(mobNumLabel);
            centerPanel.add(mobNumField);
            
            add(buttonPanel, "South");
            buttonPanel.add(update);
            buttonPanel.add(reset);
            buttonPanel.add(cancel);
            
        }
    }

    private class FinancialInfoTab extends JPanel{
        
        private JButton update = new JButton("Update");
        private JButton reset = new JButton("Reset");
        private JButton cancel = new JButton("Cancel");
        private FinancialInfoTab(){
            setLayout(new FlowLayout());
            
            add(update);
            add(reset);
            add(cancel);
        }
    }
    private class EligibilityFactorsTab extends JPanel{
        private JButton update = new JButton("Update");
        private JButton reset = new JButton("Reset");
        private JButton cancel = new JButton("Cancel");
        private EligibilityFactorsTab(){
            setLayout(new FlowLayout());
            add(update);
            add(reset);
            add(cancel);
        }
    }
    private class EnrollmentDecisionTab extends JPanel{
        private JButton update = new JButton("Update");
        private JButton reset = new JButton("Reset");
        private JButton cancel = new JButton("Cancel");
        private EnrollmentDecisionTab(){
            setLayout(new FlowLayout());
            add(update);
            add(reset);
            add(cancel);
        }
    }
    
    
	
/*
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
	
	// me
	private JTextField studentLastYearIncome = new JTextField();

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
		
		
		financialInfoTab.add(studentLastYearIncome, "Student Last Year Income:");
		
		
		frame.setSize(1000, 500);
		frame.setVisible(true);

		frame.add(jtpTabPane);
	}
*/

}
