package src;

import java.sql.*;

import javax.swing.*;
public class sqliteConnection {
		
	Connection conn = null;
	public static Connection dbConnector(){
			try{
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:userDB.sqlite");
				//JOptionPane.showMessageDialog(null, "good");
				return conn;
				
				
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}
	
	public static Connection dbConnector1(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:formDB.sqlite");
			return conn;
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
