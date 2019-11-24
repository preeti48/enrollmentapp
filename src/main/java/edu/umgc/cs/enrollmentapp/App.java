package edu.umgc.cs.enrollmentapp;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
       // System.out.println( "Hello World!" );
       // TabGui tabGui = new TabGui();
        
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
        createGUI();
        	  connect();
          }	
      });
        
    
    }

public static void connect() {
    Connection conn = null;
    try {
        // db parameters
        String url = "jdbc:sqlite:ESA.db";
        // create a connection to the database
        conn = DriverManager.getConnection(url);
        
        System.out.println("Connection to SQLite has been established.");
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
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
        		//error handling for username and password field
        		if(userIdField.getText().trim().isEmpty() && passwordField.getText().trim().isEmpty()){
        			userIdErrorLbl.setText("User ID is a required field");
        			pwErrorLbl.setText("Password is a required field");
        		}
        		else if(userIdField.getText().trim().isEmpty()){
        			userIdErrorLbl.setText("User ID is a required field");
        		}
        		else if(passwordField.getText().trim().isEmpty()){
        			pwErrorLbl.setText("Password is a required field");
        		}
        		else{
        			if(userIdField.getText().equals("Admin") && passwordField.getText().equals("123")){
                		SearchScreen searchPage = new SearchScreen();
                		frame.dispose();
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
}



