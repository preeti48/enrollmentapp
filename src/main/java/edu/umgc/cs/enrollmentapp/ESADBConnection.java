package edu.umgc.cs.enrollmentapp;

import javax.swing.*;
import java.sql.*;

public class ESADBConnection {
	Connection conn=null;
	public static Connection dbConnector ()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			// will need to path your own database location **this is example from me** 
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Senpai\\eclipse-workspace\\ESA.db");
			//Display Successful Connection Message Dialog
			JOptionPane.showMessageDialog(null, "ESA DataBase Connected Successfully");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}

