package edu.umgc.cs.enrollmentapp;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * File: App.java
 * Date: December 15, 2019
 * @author Pooja Patel
 * @author Olga Chandran
 * @author Preethi Suresh
 * @author Ngoc Nguyen 
 * @author Richard Bonolis
 * Purpose: This is the main class that launches the Enrollment Scholarship Application.
 */
public class App {
	private static JFrame frame;
	private static JTextField userIdField;
	private static JPasswordField passwordField;
	private static JLabel userIdErrorLbl;
	private static JLabel pwErrorLbl;
	
/**
 * This is the main method	
 * @param args
 */
public static void main( String[] args )
    {  
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
        createGUI();
          }	
      }); 
    }


/**
 * This method creates a login page
 */
private static void createGUI() {
        //Create and set up the window.
        frame = new JFrame("Enrollment Scholarship Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login to ESA");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(145, 32, 110, 27);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("User ID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(75, 100, 65, 17);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(75, 158, 65, 17);
        frame.getContentPane().add(lblPassword);
        
        userIdField = new JTextField();
        userIdField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		userIdErrorLbl.setText("");
        	}
        });
        userIdField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        userIdField.setBounds(168, 100, 158, 20);
        frame.getContentPane().add(userIdField);
        userIdField.setColumns(10);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		//removed deprecated method for password field and added error validation for password userID combination
        		if(userIdField.getText().trim().isEmpty() && passwordField.getPassword().length == 0){
        			userIdErrorLbl.setText("User ID is a required field");
        			pwErrorLbl.setText("Password is a required field");
        		}
        		else if(userIdField.getText().trim().isEmpty()){
        			userIdErrorLbl.setText("User ID is a required field");
        		}
        		else if(passwordField.getPassword().length == 0){
        			pwErrorLbl.setText("Password is a required field");
        		}
        		else{
        			char[] password = passwordField.getPassword();
        			if(userIdField.getText().equalsIgnoreCase("Admin")){
        				if(isValidPassword(password)){
                		SearchScreen searchPage = new SearchScreen();
                		frame.dispose();
                		}
        				else{
        					JOptionPane.showMessageDialog(frame, "Please Enter Correct Password", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
        				}
        			}
        			else{
        				JOptionPane.showMessageDialog(frame, "Please Enter Correct UserID", "Incorrect UserID", JOptionPane.ERROR_MESSAGE);
        			}
        		}
        		
        	}  	
        });
        
        loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        loginBtn.setBounds(100, 245, 85, 25);
        frame.getContentPane().add(loginBtn);
        
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userIdField.setText("");
        		passwordField.setText("");
        		pwErrorLbl.setText("");
        		userIdErrorLbl.setText("");
        	}
        });
        resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resetBtn.setBounds(215, 245, 85, 25);
        frame.getContentPane().add(resetBtn);
        
        passwordField = new JPasswordField();
        passwordField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		pwErrorLbl.setText("");
        	}
        });
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        passwordField.setBounds(168, 158, 157, 20);
        frame.getContentPane().add(passwordField);
        
        userIdErrorLbl = new JLabel("             ");
        userIdErrorLbl.setFont(new Font("Arial", Font.PLAIN, 11));
        userIdErrorLbl.setForeground(Color.RED);
        userIdErrorLbl.setBounds(168, 122, 158, 14);
        frame.getContentPane().add(userIdErrorLbl);
        
        pwErrorLbl = new JLabel("");
        pwErrorLbl.setFont(new Font("Arial", Font.PLAIN, 11));
        pwErrorLbl.setForeground(Color.RED);
        pwErrorLbl.setBounds(168, 180, 174, 14);
        frame.getContentPane().add(pwErrorLbl);

        frame.setBounds(500, 200, 400, 380);
        frame.setVisible(true);  
   }

/**
 * This method is to check if the user has entered a correct password
 * @param input is a character array from password field
 * @return true if password is correct false otherwise
 */
private static boolean isValidPassword(char [] input){
	String pw = "123";
	if(input.length == pw.length()){
	 int i =0;
	 while (i <input.length){	
		if(input[i] == pw.charAt(i)){
			i++;
		}
		else{
			return false;
		}
	 }
	 return true;
	}
	
	else {
		return false;
	}
	
	}
}



