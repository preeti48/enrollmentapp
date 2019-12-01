package edu.umgc.cs.enrollmentapp;

import java.awt.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import edu.umgc.cs.enrollmentapp.enums.ActiveYears;
import edu.umgc.cs.enrollmentapp.enums.ResidencyStatus;
import edu.umgc.cs.enrollmentapp.enums.YearOfResidency;
import edu.umgc.cs.enrollmentapp.models.Applicant;

public class TabGui extends JFrame {
	private static JFrame frame = new JFrame();
	private JTabbedPane jtpTabPane;
	private OverviewTab overviewTab;
	private FinancialInfoTab financialInfoTab;
	private EligibilityFactorsTab eligibilityFactorsTab;
	private EnrollmentDecisionTab enrollmentDecisionTab;

	Applicant theApplicant = null;

	public TabGui(Applicant applicant) {

		super("Enrollment Scholarship Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// theApplicant = applicant;
		jtpTabPane = new JTabbedPane();
		overviewTab = new OverviewTab(applicant);
		financialInfoTab = new FinancialInfoTab(applicant);
		eligibilityFactorsTab = new EligibilityFactorsTab(applicant);
		if (applicant == null)// Olga
			enrollmentDecisionTab = new EnrollmentDecisionTab(applicant);

		jtpTabPane.addTab("Student Overview", overviewTab);
		jtpTabPane.addTab("Financial Information", financialInfoTab);
		jtpTabPane.addTab("Eligibility Factors", eligibilityFactorsTab);
		jtpTabPane.addTab("Enrollment Decision", enrollmentDecisionTab);
		getContentPane().add(jtpTabPane);

		setSize(800, 600);
		setVisible(true);
	}

	private class OverviewTab extends JPanel {
		private JPanel buttonPanel = new JPanel(new FlowLayout());
		private JPanel centerPanel = new JPanel(new GridLayout(8, 4, 35, 35));
		private JPanel sexRadioPanel = new JPanel(new FlowLayout());
		private JPanel resiRadioPanel = new JPanel(new FlowLayout());
		private JPanel leftPanel = new JPanel(new FlowLayout());
		private JPanel rightPanel = new JPanel(new FlowLayout());

		ButtonGroup buttongroup1 = new ButtonGroup();
		ButtonGroup buttongroup2 = new ButtonGroup();

		// private JPanel addressPanel = new JPanel(new FlowLayout());
		private JLabel studentIdLabel = new JLabel("Student ID:");
		private JTextField studentIDField = new JTextField(7);
		private JLabel resiAdrslabel = new JLabel("Residential Address");
		private JLabel resiAdrsEmpty = new JLabel("      ");
		private JLabel emptyLabel1 = new JLabel(" ");
		private JLabel emptyLabel2 = new JLabel(" ");

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
		private JLabel mobNumLabel = new JLabel("Mobile #: ");
		private JTextField mobNumField = new JTextField();

		private JButton update = new JButton("Update");
		private JButton reset = new JButton("Reset");
		private JButton cancel = new JButton("Cancel");

		private OverviewTab(Applicant applicant) {
			buttongroup1.add(birthSexRButtonM);
			buttongroup1.add(birthSexRButtonF);
			buttongroup1.add(birthSexRButtonO);
			buttongroup2.add(usaResiRButtonY);
			buttongroup2.add(usaResiRButtonN);

			setLayout(new BorderLayout());

			centerPanel.add(studentIdLabel);
			centerPanel.add(studentIDField);
			studentIDField.setEditable(false);
			studentIDField.setText(applicant.getStudentID());

			centerPanel.add(resiAdrslabel);
			centerPanel.add(resiAdrsEmpty);

			add(centerPanel, "Center");

			centerPanel.add(lastNameLabel);
			centerPanel.add(lastNameField);
			lastNameField.setText(applicant.getLname());
			centerPanel.add(streetLabel);
			centerPanel.add(streetField);
			streetField.setText(applicant.getStreet());
			centerPanel.add(firstName);
			centerPanel.add(firstNameField);
			firstNameField.setText(applicant.getFname());
			centerPanel.add(cityLabel);
			centerPanel.add(cityField);
			cityField.setText(applicant.getCity());
			centerPanel.add(ssnLabel);
			centerPanel.add(ssnField);

			ssnField.setText(Integer.toString(applicant.getSsn()));
			centerPanel.add(stateLabel);
			centerPanel.add(stateField);
			stateField.setText(applicant.getState());
			centerPanel.add(dobLabel);
			centerPanel.add(dobField);
			dobField.setText(dateToString(applicant.getDob()));
			centerPanel.add(zipLabel);
			centerPanel.add(zipField);
			zipField.setText(Integer.toString(applicant.getZip()));

			centerPanel.add(birthSexLabel);
			sexRadioPanel.add(birthSexRButtonM);
			sexRadioPanel.add(birthSexRButtonF);
			sexRadioPanel.add(birthSexRButtonO);
			centerPanel.add(sexRadioPanel);
			radioHandle(applicant.getGender());

			centerPanel.add(usaResiLabel);
			resiRadioPanel.add(usaResiRButtonY);
			resiRadioPanel.add(usaResiRButtonN);
			centerPanel.add(resiRadioPanel);
			resiRadioHandle(applicant.isUsaResident());

			centerPanel.add(e_contactLabel);
			centerPanel.add(e_contactField);
			e_contactField.setText(applicant.getEmergencyContact());
			centerPanel.add(e_phoneLabel);
			centerPanel.add(e_phoneField);
			e_phoneField.setText(applicant.getE_phone());
			centerPanel.add(phNumLAbel);
			centerPanel.add(phNumField);
			phNumField.setText(applicant.getPhone());
			centerPanel.add(mobNumLabel);
			centerPanel.add(mobNumField);

			add(buttonPanel, "South");
			buttonPanel.add(update);
			buttonPanel.add(reset);
			buttonPanel.add(cancel);

			add(rightPanel, "West");
			rightPanel.add(emptyLabel1);
			add(leftPanel, "East");
			leftPanel.add(emptyLabel2);
		}

		/**
		 * This method converts date to String
		 * 
		 * @param d is a date
		 * @return a date as a String
		 */
		private String dateToString(Date d) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
			String strDate = dateFormat.format(d);
			return strDate;
		}

		/**
		 * This method handles radio button
		 * 
		 * @param gen is gender selected by user
		 */
		private void radioHandle(String gen) {
			if (gen.equals("male")) {
				birthSexRButtonM.setSelected(true);
				return;
			} else if (gen.equals("female")) {
				birthSexRButtonF.setSelected(true);
				return;
			} else {
				birthSexRButtonO.setSelected(true);
				return;
			}
		}

		/**
		 * This method handles residential status
		 * 
		 * @param b is a boolean
		 */
		private void resiRadioHandle(Boolean b) {
			if (b) {
				usaResiRButtonY.setSelected(true);
				return;
			} else {
				usaResiRButtonN.setSelected(true);
				return;
			}

		}
	}

	private class FinancialInfoTab extends JPanel {

		private JPanel buttonPanel = new JPanel(new FlowLayout());
		private JPanel leftPanel = new JPanel(new FlowLayout());
		private JPanel rightPanel = new JPanel(new FlowLayout());
		private JPanel centerPanel = new JPanel(new GridLayout(8, 4, 35, 35));
		private JPanel financialRadioPanel = new JPanel(new FlowLayout());
		private JPanel hav529RadioPanel = new JPanel(new FlowLayout());
		private JPanel realEesRadioPanel = new JPanel(new FlowLayout());

		ButtonGroup buttongroup1 = new ButtonGroup();
		ButtonGroup buttongroup2 = new ButtonGroup();
		ButtonGroup buttongroup3 = new ButtonGroup();

		private JLabel studentIdLabel = new JLabel("Student ID:");
		private JTextField studentIDField = new JTextField(7);

		private JLabel finanDependLabel = new JLabel("Financially Depended? ");
		private JRadioButton finanDependRButtonY = new JRadioButton("Yes");
		private JRadioButton finanDependRButtonN = new JRadioButton("No");

		private JLabel studentLastYearIncomeLabel = new JLabel("Student Last Year Income: ");
		private JTextField studentLastYearIncomeField = new JTextField();
		private JLabel parentLastYearIncomeLabel = new JLabel("Parent Last Year Income: ");
		private JTextField parenttLastYearIncomeField = new JTextField();
		private JLabel hav529AcctLabel = new JLabel("Do you have 529 Account? ");
		private JRadioButton hav529AcctRButtonY = new JRadioButton("Yes");
		private JRadioButton hav529AcctRButtonN = new JRadioButton("No");

		private JLabel havRealEstLandLabel = new JLabel("Do you have Real Estate, Land, etc? ");
		private JRadioButton havRealEstRButtonY = new JRadioButton("Yes");
		private JRadioButton havRealEstRButtonN = new JRadioButton("No");

		private JLabel valOfOtherProptyLabel = new JLabel("Value of Other Properties: ");
		private JTextField valOfOtherProptyField = new JTextField();

		// Empty label for alignment purpose
		private JLabel empty1 = new JLabel("      ");
		private JLabel empty2 = new JLabel("      ");
		private JLabel empty3 = new JLabel("      ");
		private JLabel empty4 = new JLabel("      ");
		private JLabel empty5 = new JLabel("      ");
		private JLabel empty6 = new JLabel("      ");
		private JLabel empty7 = new JLabel("      ");
		private JLabel empty8 = new JLabel("      ");

		private JButton update = new JButton("Update");
		private JButton reset = new JButton("Reset");
		private JButton cancel = new JButton("Cancel");

		private FinancialInfoTab(Applicant applicant) {
			buttongroup1.add(finanDependRButtonY);
			buttongroup1.add(finanDependRButtonN);
			buttongroup2.add(hav529AcctRButtonY);
			buttongroup2.add(hav529AcctRButtonN);
			buttongroup3.add(havRealEstRButtonY);
			buttongroup3.add(havRealEstRButtonN);

			setLayout(new BorderLayout());

			add(centerPanel, "Center");
			centerPanel.add(studentIdLabel);
			centerPanel.add(studentIDField);
			studentIDField.setEditable(false);
			studentIDField.setText(applicant.getStudentID());
			centerPanel.add(finanDependLabel);
			financialRadioPanel.add(finanDependRButtonY);
			financialRadioPanel.add(finanDependRButtonN);
			centerPanel.add(financialRadioPanel);
			if (applicant.finInfo != null)
				finRadioHandle(applicant.finInfo.getDependency());

			centerPanel.add(studentLastYearIncomeLabel);
			centerPanel.add(studentLastYearIncomeField);
			if (applicant.finInfo != null)
				studentLastYearIncomeField.setText(Double.toString(applicant.finInfo.getStudentIncome()));
			centerPanel.add(parentLastYearIncomeLabel);
			centerPanel.add(parenttLastYearIncomeField);
			if (applicant.finInfo != null)
				parenttLastYearIncomeField.setText(Double.toString(applicant.finInfo.getParentIncome()));
			centerPanel.add(hav529AcctLabel);
			hav529RadioPanel.add(hav529AcctRButtonY);
			hav529RadioPanel.add(hav529AcctRButtonN);
			centerPanel.add(hav529RadioPanel);
			if (applicant.finInfo != null) {
				if (applicant.finInfo.get529Status()) {
					hav529AcctRButtonY.setSelected(true);
				} else {
					hav529AcctRButtonN.setSelected(true);
				}
			}
			centerPanel.add(havRealEstLandLabel);
			realEesRadioPanel.add(havRealEstRButtonY);
			realEesRadioPanel.add(havRealEstRButtonN);
			centerPanel.add(realEesRadioPanel);
			if (applicant.finInfo != null) {
				if (applicant.finInfo.getRealStatus()) {
					havRealEstRButtonY.setSelected(true);
				} else {
					havRealEstRButtonN.setSelected(true);
				}
			}
			centerPanel.add(valOfOtherProptyLabel);
			centerPanel.add(valOfOtherProptyField);
			if (applicant.finInfo != null)
				valOfOtherProptyField.setText(Double.toString(applicant.finInfo.getPropValue()));

			add(buttonPanel, "South");
			buttonPanel.add(update);
			buttonPanel.add(reset);
			buttonPanel.add(cancel);

			// Added empty spaces for alignment
			add(leftPanel, "East");
			leftPanel.add(empty1);
			leftPanel.add(empty2);
			leftPanel.add(empty3);
			leftPanel.add(empty4);
			add(rightPanel, "West");
			rightPanel.add(empty5);
			rightPanel.add(empty6);
			leftPanel.add(empty7);
			leftPanel.add(empty8);
		}

		private void finRadioHandle(Boolean b) {
			if (b) {
				finanDependRButtonY.setSelected(true);
				return;
			} else {
				finanDependRButtonN.setSelected(true);
				return;
			}

		}
	}

	private class EligibilityFactorsTab extends JPanel {
		private JPanel buttonPanel = new JPanel(new FlowLayout());
		private JPanel leftPanel = new JPanel(new FlowLayout());
		private JPanel rightPanel = new JPanel(new FlowLayout());
		private JPanel centerPanel = new JPanel(new GridLayout(10, 2, 20, 20));
		private JPanel servedMilitaryPanel = new JPanel(new FlowLayout());
		private JPanel militaryStatusPanel = new JPanel(new FlowLayout());
		private JPanel activeYearPanel = new JPanel(new FlowLayout());
		private JPanel disabilityPanel = new JPanel(new FlowLayout());
		private JPanel financialAidPanel = new JPanel(new FlowLayout());
		private JPanel residencyStatusPanel = new JPanel(new FlowLayout());
		private JPanel yearsOfResidencyPanel = new JPanel(new FlowLayout());
		private JPanel overAge55Panel = new JPanel(new FlowLayout());
		private JPanel areYouDependedPanel = new JPanel(new FlowLayout());

		ButtonGroup buttonGroup1 = new ButtonGroup();
		ButtonGroup buttonGroup2 = new ButtonGroup();
		ButtonGroup buttonGroup3 = new ButtonGroup();
		ButtonGroup buttonGroup4 = new ButtonGroup();
		ButtonGroup buttonGroup5 = new ButtonGroup();
		ButtonGroup buttonGroup6 = new ButtonGroup();
		ButtonGroup buttonGroup7 = new ButtonGroup();
		ButtonGroup buttonGroup8 = new ButtonGroup();
		ButtonGroup buttonGroup9 = new ButtonGroup();

		private JLabel studentIdLabel = new JLabel("Student ID:");
		private JTextField studentIDField = new JTextField(7);
		private JLabel havServedMilitaryLabel = new JLabel("Have You Ever Served in the Military? ");
		private JRadioButton havServedMilitaryY = new JRadioButton("Yes");
		private JRadioButton havServedMilitaryN = new JRadioButton("No");

		private JLabel militaryStatusLabel = new JLabel("            Military Status: ");
		private JRadioButton militaryStatusActive = new JRadioButton("Active");
		private JRadioButton militaryStatusInactive = new JRadioButton("Inactive");

		private JLabel activeYearLabel = new JLabel("            Active Year: ");
		private JRadioButton activeYearlessThan1 = new JRadioButton("Less than 1");
		private JRadioButton activeYearBetween1_5 = new JRadioButton("Between 1 - 5");
		private JRadioButton activeYearMoreThan5 = new JRadioButton("More than 5");

		private JLabel disabilityStatusLabel = new JLabel("Disability Status: ");
		private JRadioButton disabilityY = new JRadioButton("Yes");
		private JRadioButton disabilityN = new JRadioButton("No");

		private JLabel financialAidLabel = new JLabel("Financial Aid Eligibility: ");
		private JRadioButton financialAidY = new JRadioButton("Yes");
		private JRadioButton financialAidN = new JRadioButton("No");

		private JLabel residenStatusLabel = new JLabel("Residencial Status: ");
		private JRadioButton residStatusIn = new JRadioButton("In-State");
		private JRadioButton residStatusOut = new JRadioButton("Out-of-State");
		private JRadioButton residStatuAbroad = new JRadioButton("Abroad");

		private JLabel yearsOfResiLabel = new JLabel("Years of Residency: ");
		private JRadioButton yearsOfResilessThan1 = new JRadioButton("Less than 1");
		private JRadioButton yearsOfResibetween1_5 = new JRadioButton("Between 1 - 5");
		private JRadioButton yearsOfResimoreThan5 = new JRadioButton("More than 5");

		private JLabel overAge55Label = new JLabel("Over the Age 55? ");
		private JRadioButton overAge55Y = new JRadioButton("Yes");
		private JRadioButton overAge55N = new JRadioButton("No");

		private JLabel areYouDependentLabel = new JLabel("Are you Dependent? ");
		private JRadioButton areYouDependentY = new JRadioButton("Yes");
		private JRadioButton areYouDependentN = new JRadioButton("No");

		// Empty label for alignment purpose
		private JLabel empty1 = new JLabel("      ");
		private JLabel empty2 = new JLabel("      ");
		private JLabel empty3 = new JLabel("      ");
		private JLabel empty4 = new JLabel("      ");
		private JLabel empty5 = new JLabel("      ");
		private JLabel empty6 = new JLabel("      ");
		private JLabel empty7 = new JLabel("      ");
		private JLabel empty8 = new JLabel("      ");

		private JButton update = new JButton("Update");
		private JButton reset = new JButton("Reset");
		private JButton cancel = new JButton("Cancel");

		private EligibilityFactorsTab(Applicant applicant) {
			buttonGroup1.add(havServedMilitaryY);
			buttonGroup1.add(havServedMilitaryN);
			buttonGroup2.add(militaryStatusActive);
			buttonGroup2.add(militaryStatusInactive);
			buttonGroup3.add(activeYearlessThan1);
			buttonGroup3.add(activeYearBetween1_5);
			buttonGroup3.add(activeYearMoreThan5);
			buttonGroup4.add(disabilityY);
			buttonGroup4.add(disabilityN);
			buttonGroup5.add(financialAidY);
			buttonGroup5.add(financialAidN);
			buttonGroup6.add(residStatusIn);
			buttonGroup6.add(residStatusOut);
			buttonGroup6.add(residStatuAbroad);
			buttonGroup7.add(yearsOfResilessThan1);
			buttonGroup7.add(yearsOfResibetween1_5);
			buttonGroup7.add(yearsOfResimoreThan5);
			buttonGroup8.add(overAge55Y);
			buttonGroup8.add(overAge55N);
			buttonGroup9.add(areYouDependentY);
			buttonGroup9.add(areYouDependentN);

			setLayout(new BorderLayout());
			add(centerPanel, "Center");
			centerPanel.add(studentIdLabel);
			centerPanel.add(studentIDField);
			studentIDField.setEditable(false);
			studentIDField.setText(applicant.getStudentID());
			centerPanel.add(havServedMilitaryLabel);
			servedMilitaryPanel.add(havServedMilitaryY);
			servedMilitaryPanel.add(havServedMilitaryN);
			centerPanel.add(servedMilitaryPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getMiliServed()) {
					havServedMilitaryY.setSelected(true);
				} else {
					havServedMilitaryN.setSelected(true);
				}
			}
			centerPanel.add(militaryStatusLabel);
			militaryStatusPanel.add(militaryStatusActive);
			militaryStatusPanel.add(militaryStatusInactive);
			centerPanel.add(militaryStatusPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getMiliServed()) {
					if (applicant.eligInfo.getMiliStatus()) {
						militaryStatusActive.setSelected(true);
					} else {
						militaryStatusInactive.setSelected(true);
					}
				}
			}
			centerPanel.add(activeYearLabel);
			activeYearPanel.add(activeYearlessThan1);
			activeYearPanel.add(activeYearBetween1_5);
			activeYearPanel.add(activeYearMoreThan5);
			centerPanel.add(activeYearPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getMiliServed()) {
					if (applicant.eligInfo.getActiveYears() == ActiveYears.LessThanOneYears) {
						activeYearlessThan1.setSelected(true);
					} else if (applicant.eligInfo.getActiveYears() == ActiveYears.BetweenOneAndFveYears) {
						activeYearBetween1_5.setSelected(true);
					} else {
						activeYearMoreThan5.setSelected(true);
					}
				}
			}

			centerPanel.add(disabilityStatusLabel);
			disabilityPanel.add(disabilityY);
			disabilityPanel.add(disabilityN);
			centerPanel.add(disabilityPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getdisabilityStatus()) {
					disabilityY.setSelected(true);
				} else {
					disabilityN.setSelected(true);
				}

			}
			centerPanel.add(financialAidLabel);
			financialAidPanel.add(financialAidY);
			financialAidPanel.add(financialAidN);
			centerPanel.add(financialAidPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getFinAidElig()) {
					financialAidY.setSelected(true);
				} else {
					financialAidN.setSelected(true);
				}
			}
			centerPanel.add(residenStatusLabel);
			residencyStatusPanel.add(residStatusIn);
			residencyStatusPanel.add(residStatusOut);
			residencyStatusPanel.add(residStatuAbroad);
			centerPanel.add(residencyStatusPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getResidencyStatus() == ResidencyStatus.InState) {
					residStatusIn.setSelected(true);
				}
				if (applicant.eligInfo.getResidencyStatus() == ResidencyStatus.OutOfState) {
					residStatusOut.setSelected(true);
				}
				if (applicant.eligInfo.getResidencyStatus() == ResidencyStatus.Abroad) {
					residStatuAbroad.setSelected(true);
				}
			}
			centerPanel.add(yearsOfResiLabel);
			yearsOfResidencyPanel.add(yearsOfResilessThan1);
			yearsOfResidencyPanel.add(yearsOfResibetween1_5);
			yearsOfResidencyPanel.add(yearsOfResimoreThan5);
			centerPanel.add(yearsOfResidencyPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.getResidencyYears() == YearOfResidency.LessThanOneYears) {
					yearsOfResilessThan1.setSelected(true);
				}
				if (applicant.eligInfo.getResidencyYears() == YearOfResidency.BetweenOneAndFveYears) {
					activeYearBetween1_5.setSelected(true);
				}
				if (applicant.eligInfo.getResidencyYears() == YearOfResidency.Over5Years) {
					yearsOfResimoreThan5.setSelected(true);
				}
			}

			centerPanel.add(overAge55Label);
			overAge55Panel.add(overAge55Y);
			overAge55Panel.add(overAge55N);
			centerPanel.add(overAge55Panel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.isAgeOver55) {
					overAge55Y.setSelected(true);
				} else {
					overAge55N.setSelected(true);
				}
			}
			centerPanel.add(areYouDependentLabel);
			areYouDependedPanel.add(areYouDependentY);
			areYouDependedPanel.add(areYouDependentN);
			centerPanel.add(areYouDependedPanel);
			if (applicant.eligInfo != null) {
				if (applicant.eligInfo.areYouDepended) {
					areYouDependentY.setSelected(true);
				} else {
					areYouDependentY.setSelected(true);
				}
			}
			add(buttonPanel, "South");
			buttonPanel.add(update);
			buttonPanel.add(reset);
			buttonPanel.add(cancel);

			// Empty label for alignment purpose
			add(leftPanel, "East");
			leftPanel.add(empty1);
			leftPanel.add(empty2);
			leftPanel.add(empty3);
			leftPanel.add(empty4);
			add(rightPanel, "West");
			rightPanel.add(empty5);
			rightPanel.add(empty6);
			leftPanel.add(empty7);
			leftPanel.add(empty8);
		}
	}

	private class EnrollmentDecisionTab extends JPanel {
		private JPanel buttonPanel = new JPanel(new FlowLayout());
		private JPanel topPanel = new JPanel(new FlowLayout());
		private JPanel leftPanel = new JPanel(new FlowLayout());
		private JPanel rightPanel = new JPanel(new FlowLayout());
		private JPanel centerPanel = new JPanel(new GridLayout(5, 2, 40, 40));
		private JPanel emptyPanel1 = new JPanel(new GridLayout(8, 1, 20, 20));

		private JLabel studentIdLabel = new JLabel("Student ID:");
		private JTextField studentIDField = new JTextField(7);
		private JLabel entrollDateLabel = new JLabel("Entrollment Date: ");
		private JTextField entrollDateField = new JTextField();
		private JLabel groupNumLabel = new JLabel("Group Number:");
		private JTextField groupNumField = new JTextField();

		private JButton update = new JButton("Update");
		private JButton reset = new JButton("Reset");
		private JButton cancel = new JButton("Cancel");

		// Empty label for alignment purpose
		private JLabel empty1 = new JLabel("      ");
		private JLabel empty2 = new JLabel("      ");
		private JLabel empty3 = new JLabel("      ");
		private JLabel empty4 = new JLabel("      ");
		private JLabel empty5 = new JLabel("      ");
		private JLabel empty6 = new JLabel("      ");
		private JLabel empty7 = new JLabel("      ");
		private JLabel empty8 = new JLabel("      ");

		private EnrollmentDecisionTab(Applicant applicant) {
			setLayout(new BorderLayout());

			emptyPanel1.add(empty1, "Center");
			emptyPanel1.add(empty2, "Center");
			emptyPanel1.add(empty3, "Center");
			emptyPanel1.add(empty4, "Center");
			emptyPanel1.add(empty5, "Center");
			emptyPanel1.add(empty6, "Center");
			emptyPanel1.add(empty7, "Center");
			emptyPanel1.add(empty8, "Center");
			topPanel.add(emptyPanel1);
			add(topPanel, "North");

			add(centerPanel, "Center");

			centerPanel.add(studentIdLabel);
			centerPanel.add(studentIDField);
			studentIDField.setEditable(false);
			studentIDField.setText(applicant.getStudentID());
			centerPanel.add(entrollDateLabel);
			centerPanel.add(entrollDateField);
			if (applicant.enrollDecision != null && applicant.enrollDecision.getEnrollDate() != null)
				entrollDateField.setText(dateToString(applicant.enrollDecision.getEnrollDate()));

			centerPanel.add(groupNumLabel);
			centerPanel.add(groupNumField);
			// olga
			groupNumField.setText(Integer.toString(applicant.enrollDecision.getGroup()));
			centerPanel.add(empty1);
			centerPanel.add(empty2);

			add(buttonPanel, "South");
			buttonPanel.add(update);
			buttonPanel.add(reset);
			buttonPanel.add(cancel);

			// Empty label for alignment purpose
			add(leftPanel, "East");
			leftPanel.add(empty1);
			leftPanel.add(empty2);
			leftPanel.add(empty3);
			leftPanel.add(empty4);
			add(rightPanel, "West");
			rightPanel.add(empty5);
			rightPanel.add(empty6);
			rightPanel.add(empty7);
			rightPanel.add(empty8);
		}

		/**
		 * This method converts date to String
		 * 
		 * @param d is a date
		 * @return a date as a String
		 */
		private String dateToString(Date d) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
			String strDate = dateFormat.format(d);
			return strDate;
		}

	}

	/*
	 * private static JFrame frame = new JFrame(); private JTabbedPane jtpTabPane =
	 * new JTabbedPane(); private JPanel overviewTab = new JPanel(); private JPanel
	 * financialInfoTab = new JPanel(); private JPanel eligibilityFactorsTab = new
	 * JPanel(); private JPanel enrollmentDecisionTab = new JPanel(); private
	 * JTextField jtxStudentIDOverviewTab = new JTextField(); private JTextField
	 * jtxStudentIDFinancialInfoTab = new JTextField(); private JTextField
	 * jtxStudentIDEligibilityFactorsTab = new JTextField(); private JTextField
	 * jtxStudentIDEnrollmentDecisionTab = new JTextField(); private Applicant
	 * theApplicant = null;
	 * 
	 * // me private JTextField studentLastYearIncome = new JTextField();
	 * 
	 * public TabGui(Applicant applicant) {
	 * 
	 * 
	 * theApplicant = applicant; createGUI(); }
	 * 
	 * private void createGUI() { frame = new
	 * JFrame("Enrollment Scholarship Application");
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setLocationRelativeTo(null);
	 * 
	 * // Display the frame // frame.pack();
	 * 
	 * jtpTabPane.add(overviewTab, "Student Overview");
	 * jtpTabPane.add(financialInfoTab, "Financial Information");
	 * jtpTabPane.add(eligibilityFactorsTab, "Eligibility Factors");
	 * jtpTabPane.add(enrollmentDecisionTab, "Enrollment Decision");
	 * 
	 * jtpTabPane.setToolTipTextAt(0, "Overview Tab");
	 * jtpTabPane.setToolTipTextAt(1, "Financial Information Tab");
	 * jtpTabPane.setToolTipTextAt(2, "Eligibility Factors Tab");
	 * jtpTabPane.setToolTipTextAt(3, "Enrollment Decision Tab");
	 * jtxStudentIDOverviewTab.setText(theApplicant.getStudentID());
	 * jtxStudentIDFinancialInfoTab.setText(theApplicant.getStudentID());
	 * jtxStudentIDEligibilityFactorsTab.setText(theApplicant.getStudentID());
	 * jtxStudentIDEnrollmentDecisionTab.setText(theApplicant.getStudentID());
	 * overviewTab.add(jtxStudentIDOverviewTab);
	 * financialInfoTab.add(jtxStudentIDFinancialInfoTab);
	 * eligibilityFactorsTab.add(jtxStudentIDEligibilityFactorsTab);
	 * enrollmentDecisionTab.add(jtxStudentIDEnrollmentDecisionTab);
	 * 
	 * 
	 * financialInfoTab.add(studentLastYearIncome, "Student Last Year Income:");
	 * 
	 * 
	 * frame.setSize(1000, 500); frame.setVisible(true);
	 * 
	 * frame.add(jtpTabPane); }
	 */

}
