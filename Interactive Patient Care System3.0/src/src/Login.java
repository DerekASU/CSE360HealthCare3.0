package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import src.Admin.AdminHome;
import src.HCP.HCPHome;
import src.patient.PatientHome;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;
public class Login {

	private JFrame frmInteractivePatientCare;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSystemAdminAccess;
	private JButton btnLogin;
	private JLabel lblHealthCareProvider;
	private JTextField textFieldUN;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmInteractivePatientCare.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private static String hcpID;
	private static String patientID;
	
	public static String getHcpID() {
		return hcpID;
	}


	public void setHcpID(String iD) {
		hcpID = iD;
	}

	public static String getPatientID() {
		return patientID;
	}


	public static void setPatientID(String patientID) {
		Login.patientID = patientID;
	}


	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnector();
		//connection1 = sqliteConnection.dbConnector1();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInteractivePatientCare = new JFrame();
		frmInteractivePatientCare.setTitle("Interactive Patient Care System Login");
		frmInteractivePatientCare.setBounds(100, 100, 650, 500);
		frmInteractivePatientCare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInteractivePatientCare.getContentPane().setLayout(null);
		
		JLabel lblInteractivePatiientCare = new JLabel("Interactive Patient Care System Login");
		lblInteractivePatiientCare.setForeground(Color.BLACK);
		lblInteractivePatiientCare.setFont(new Font("Calibri", Font.BOLD, 24));
		lblInteractivePatiientCare.setBounds(125, 34, 450, 71);
		frmInteractivePatientCare.getContentPane().add(lblInteractivePatiientCare);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(202, 288, 200, 50);
		frmInteractivePatientCare.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 210, 200, 50);
		frmInteractivePatientCare.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(90, 146, 139, 39);
		frmInteractivePatientCare.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel(" Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(90, 221, 139, 39);
		frmInteractivePatientCare.getContentPane().add(lblPassword);
		
		btnSystemAdminAccess = new JButton("System Admin Login");
		btnSystemAdminAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome adminHome = new AdminHome();
				ResultSet rs = null;
				PreparedStatement pst = null;
				try{
					String query="select * from AdminInfo where Username=? and password=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
					
					rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						adminHome.setVisible(true);
					} else if(count > 1){
						JOptionPane.showMessageDialog(null, "Username and/or password are not correct");
					} else{
						JOptionPane.showMessageDialog(null, "Username and/or password are not correct");
					}
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				finally{
					try{
						rs.close();
						pst.close();
					} catch(Exception e2){
						JOptionPane.showMessageDialog(null, e2);
					}
				}
			}
		});
		btnSystemAdminAccess.setBounds(412, 367, 180, 33);
		frmInteractivePatientCare.getContentPane().add(btnSystemAdminAccess);
		
		btnLogin = new JButton("Patient Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientHome patientHome = new PatientHome();
				ResultSet rs = null;
				PreparedStatement pst = null;
				PreparedStatement pst1 = null;
				try{
					String query="select * from patientInfo where ID=? and password=? and Username=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					pst.setString(3, textFieldUN.getText());
					
					
					rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						patientHome.setVisible(true);
						setPatientID(textField.getText());
					} else if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					} else{
						JOptionPane.showMessageDialog(null, "Username, Password, and/or ID are not correct");
					}
					
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				
				finally{
					try{
						rs.close();
						pst.close();
					} catch(Exception e2){
						JOptionPane.showMessageDialog(null, e2);
					}
				}
			}
		});
		btnLogin.setBounds(32, 367, 180, 33);
		frmInteractivePatientCare.getContentPane().add(btnLogin);
		
		lblHealthCareProvider = new JLabel("     ID:");
		lblHealthCareProvider.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHealthCareProvider.setBounds(138, 293, 139, 39);
		frmInteractivePatientCare.getContentPane().add(lblHealthCareProvider);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUN.setColumns(10);
		textFieldUN.setBounds(202, 135, 200, 50);
		frmInteractivePatientCare.getContentPane().add(textFieldUN);
		
		JButton btnHealthCareProvider_1 = new JButton("Health Care Provider Login\r\n");
		btnHealthCareProvider_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HCPHome hcpHome = new HCPHome();
				ResultSet rs = null;
				PreparedStatement pst = null;
				try{
					String query="select * from hcpInfo where ID=? and password=? and Username=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					pst.setString(3, textFieldUN.getText());
					
					rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						hcpHome.setVisible(true);
						setHcpID(textField.getText());
					} else if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicates of Username, password, or HCP ID");
					} else{
						JOptionPane.showMessageDialog(null, "Username, password, or HCP ID are not correct");
					}
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				finally{
					try{
						rs.close();
						pst.close();
					} catch(Exception e2){
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				
			}
		});
		btnHealthCareProvider_1.setBounds(222, 367, 180, 33);
		frmInteractivePatientCare.getContentPane().add(btnHealthCareProvider_1);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ForgotPass forgotPass = new ForgotPass();
				forgotPass.setVisible(true);
			}
		});
		btnForgotPassword.setBounds(125, 411, 180, 33);
		frmInteractivePatientCare.getContentPane().add(btnForgotPassword);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateAccount createAccount = new CreateAccount();
				createAccount.setVisible(true);
			}
		});
		btnCreateAccount.setBounds(329, 411, 180, 33);
		frmInteractivePatientCare.getContentPane().add(btnCreateAccount);
	}
}

