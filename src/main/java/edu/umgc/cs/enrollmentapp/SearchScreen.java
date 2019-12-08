package edu.umgc.cs.enrollmentapp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import edu.umgc.cs.enrollmentapp.models.Applicant;

public class SearchScreen {

	private JFrame frame;
	private JTextField studentIDField;
	private JTextField ssnField;
	private JTextField lnField;
	private JTextField fnfield;
	private JDateChooser dateChooser;

	// connection
	Connection connection = null;

	/**
	 * Create the searchPage.
	 */
	public SearchScreen() {
		createSearchPage();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void createSearchPage() {
		frame = new JFrame("Enrollment Scholarship Application");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel esaLable = new JLabel("Enrollment Scholarship Application");
		esaLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		esaLable.setBounds(70, 30, 310, 22);
		frame.getContentPane().add(esaLable);

		// Reports menu
		JButton reportBtn = new JButton("View Reports");
		reportBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		reportBtn.setBounds(70, 83, 110, 23);
		final JPopupMenu popup = new JPopupMenu();

		JMenuItem jmiGroup1 = new JMenuItem("Enrollment in Group#1");
		jmiGroup1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" Group 1 report is selected.");
				try {
					Reports.generateGroup1Report();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		popup.add(jmiGroup1);
		popup.addSeparator();

		JMenuItem jmiGroup2 = new JMenuItem("Enrollment in Group#2");
		jmiGroup2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" Group 2 report is selected.");
				try {
					Reports.generateGroup2Report();

				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		popup.add(jmiGroup2);
		popup.addSeparator();

		JMenuItem jmiGroup3 = new JMenuItem("Enrollment in Group#3");
		jmiGroup3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" Group 3 report is selected.");
				try {
					Reports.generateGroup3Report();

				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		popup.add(jmiGroup3);
		popup.addSeparator();
		JMenuItem jmiGroup4 = new JMenuItem("Enrollment in Group#4");
		jmiGroup4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" Group 4 report is selected.");
				try {
					Reports.generateGroup4Report();

				} catch (Exception e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
			}
		});
		popup.add(jmiGroup4);
		popup.addSeparator();

		JMenuItem jmiGroup5 = new JMenuItem("Enrollment in Group#5");
		jmiGroup5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(" Group 5 report is selected.");
				try {
					Reports.generateGroup5Report();

				} catch (Exception e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
			}
		});
		popup.add(jmiGroup5);
		reportBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		frame.getContentPane().add(reportBtn);

		JLabel studentIDLbl = new JLabel("Student ID");
		studentIDLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentIDLbl.setBounds(70, 140, 100, 25);
		frame.getContentPane().add(studentIDLbl);

		JLabel ssnLbl = new JLabel("SSN");
		ssnLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ssnLbl.setBounds(70, 180, 100, 25);
		frame.getContentPane().add(ssnLbl);

		JLabel lnLbl = new JLabel("Last Name");
		lnLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lnLbl.setBounds(70, 220, 100, 25);
		frame.getContentPane().add(lnLbl);

		JLabel fnLbl = new JLabel("First Name");
		fnLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fnLbl.setBounds(70, 260, 100, 25);
		frame.getContentPane().add(fnLbl);

		JLabel dobLbl = new JLabel("DOB");
		dobLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dobLbl.setBounds(70, 300, 100, 25);
		frame.getContentPane().add(dobLbl);

		studentIDField = new JTextField();
		studentIDField.setBounds(210, 140, 170, 24);
		frame.getContentPane().add(studentIDField);
		studentIDField.setColumns(7);

		ssnField = new JTextField();
		ssnField.setBounds(210, 180, 170, 24);
		frame.getContentPane().add(ssnField);
		ssnField.setColumns(10);

		lnField = new JTextField();
		lnField.setBounds(210, 220, 170, 24);
		frame.getContentPane().add(lnField);
		lnField.setColumns(10);

		fnfield = new JTextField();
		fnfield.setBounds(210, 260, 170, 24);
		frame.getContentPane().add(fnfield);
		fnfield.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(210, 300, 170, 24);
		dateChooser.setDateFormatString("MM/dd/yyyy");
		frame.getContentPane().add(dateChooser);

		JButton searchBtn = new JButton("Search");
		// search button handling
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSearch();
			}
		});
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		searchBtn.setBounds(70, 373, 89, 23);
		frame.getContentPane().add(searchBtn);

		JButton addBtn = new JButton("Add");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addBtn.setBounds(291, 373, 89, 23);
		frame.getContentPane().add(addBtn);
		// ActionListener for Add button
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date date = dateChooser.getDate();

				Applicant student = ESADBConnection.checkIfstudentExists(ssnField.getText(), lnField.getText(),
						fnfield.getText(), null);// fName, String dOB)
				if (student.getStudentID() != null){
					System.out.println(
							"Student exists , studentID = " + student.getStudentID() + ". Please click Search to edit");
					JOptionPane.showMessageDialog(frame, "Please try again entering correct SSN ", "Student Already Exist",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (student.getStudentID() == null) {
					// generate new student Id
					// save student to the database
					Applicant newStudent = ESADBConnection.addStudent(ssnField.getText(), lnField.getText(),
							fnfield.getText(), date);// fName, String dOB)

					// open tabGUI with saved student:

				TabGui tabGui = new TabGui(newStudent);
				}
			}
		});

		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentIDField.setText("");
				ssnField.setText("");
				lnField.setText("");
				fnfield.setText("");
				dateChooser.setCalendar(null);

			}
		});
		resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		resetBtn.setBounds(181, 373, 89, 23);
		frame.getContentPane().add(resetBtn);

		frame.setBounds(500, 100, 450, 500);
		frame.setVisible(true);

	}

	/**
	 * This method validates entered StudentId
	 * 
	 * @param id is a User Input
	 * @return true if entered studentId is valid false otherwise
	 */
	private boolean validateID(String id) {
		if (id.matches("[0-9]+") && id.length() == 7) {
			return true;
		}

		return false;
	}

	/**
	 * This method validates SSN
	 * 
	 * @param ssn is a user input
	 * @return true if SSN is valid false otherwise
	 */
	private boolean validateSSN(String ssn) {
		if (ssn.length() == 9 && ssn.matches("[0-9]+")) {
			return true;
		}

		return false;
	}

	/**
	 * This method is to perform search operation
	 */
	private void performSearch() {

		if (studentIDField.getText().isEmpty() && ssnField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frame,
					" Please enter Student ID or SSN and Last Name to Search for Student Record ");
		}
		
		// if multiple fields are filled out
		else if (!studentIDField.getText().isEmpty() && !ssnField.getText().isEmpty() ||
				 !studentIDField.getText().isEmpty() && !lnField.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(frame,
					" Please search using only Student ID, or only SSN and Last Name ");
		}

		// if search by studentID
		else if (!studentIDField.getText().isEmpty()) {
			// if studentID is valid
			if (validateID(studentIDField.getText())) {
				// search for the student in database by ID
				String studentID = studentIDField.getText();
				studentID = studentID.trim();
				Applicant applicant = ESADBConnection.searchByStudentID(studentID);

				if (applicant.isFound) {
					TabGui tabGui = new TabGui(applicant);
				} else {
					JOptionPane.showMessageDialog(frame, "Are you trying to add a new Applicant ?",
							"The Applicant is Not Found", JOptionPane.CLOSED_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please enter correct Student ID", "Incorrect StudentID",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (!ssnField.getText().isEmpty()) {
			// validate ssn
			if (validateSSN(ssnField.getText())) {

				if (!lnField.getText().isEmpty()) {
					// search by SSN and Last Name
					String ssnNum = ssnField.getText();
					ssnNum = ssnNum.trim();
					String lName = lnField.getText();
					lName = lName.trim();
					Applicant applicant = ESADBConnection.searchBySSNandLname(ssnNum, lName);

					if (applicant.isFound) {
						TabGui tabGui = new TabGui(applicant);
					} else {
						JOptionPane.showMessageDialog(frame, "Are you trying to add a new Applicant ?",
								"The Applicant is Not Found", JOptionPane.CLOSED_OPTION);
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Please enter Last Name to search by SSN", "Enter LastName",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

			else {
				JOptionPane.showMessageDialog(frame, "Please enter correct SSN ", "Incorrect SSN",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void searchByID() {

	}

	private void searchBySSNandLN() {

	}

	public void connection() {
		// TODO Auto-generated method stub

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

}
