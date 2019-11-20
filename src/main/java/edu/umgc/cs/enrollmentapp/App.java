package edu.umgc.cs.enrollmentapp;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class App {
	private static JFrame frame;
	private static JTextField userIdField;
	private static JPasswordField passwordField;
	
/**
 * This is the main method	
 * @param args
 */
public static void main( String[] args )
    {
       // System.out.println( "Hello World!" );
       // TabGui tabGui = new TabGui();
        
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
        userIdField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        userIdField.setBounds(168, 100, 158, 20);
        frame.getContentPane().add(userIdField);
        userIdField.setColumns(10);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//if(userIdField.getText().equals("Admin") && passwordField.getText().equals("123")){
        		SearchScreen searchPage = new SearchScreen();
        		frame.dispose();
        		//}
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
        	}
        });
        resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resetBtn.setBounds(215, 245, 85, 25);
        frame.getContentPane().add(resetBtn);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        passwordField.setBounds(168, 158, 157, 20);
        frame.getContentPane().add(passwordField);

        frame.setBounds(500, 200, 400, 380);
        frame.setVisible(true);
       
   }
	
}



