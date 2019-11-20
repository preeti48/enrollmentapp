package edu.umgc.cs.enrollmentapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SearchScreen {

	private JFrame frame;
	private JTextField stdentIDField;
	private JTextField ssnField;
	private JTextField lnField;
	private JTextField fnfield;
	private JDateChooser dateChooser;
         
	//connection
	Connection connection=null;
	
	/**
	 * Create the searchPage.
	 */
	public SearchScreen() {
		createSearchPage();
		//connection to ESADBConnection Class File
		connection=ESADBConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void createSearchPage() {
		frame = new JFrame("Enrollment Scholarship Application");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel esaLable = new JLabel("Enrollment Scholarship Application");
		esaLable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		esaLable.setBounds(70, 30, 310, 22);
		frame.getContentPane().add(esaLable);
		
		JButton reportBtn = new JButton("View Reports");
		reportBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		reportBtn.setBounds(70, 83, 110, 23);
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
		
		stdentIDField = new JTextField();
		stdentIDField.setBounds(210, 140, 170, 24);
		frame.getContentPane().add(stdentIDField);
		stdentIDField.setColumns(10);
		
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
	    frame.getContentPane().add(dateChooser);
	    
	    JButton searchBtn = new JButton("Search");
	    searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    searchBtn.setBounds(70, 373, 89, 23);
	    frame.getContentPane().add(searchBtn);
	    
	    JButton addBtn = new JButton("Add");
	    addBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    addBtn.setBounds(291, 373, 89, 23);
	    frame.getContentPane().add(addBtn);
	    
	    JButton resetBtn = new JButton("Reset");
	    resetBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		stdentIDField.setText("");
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
	

private void searchByID(){
	
}

private void searchBySSNandLN(){
	
}
	
}
