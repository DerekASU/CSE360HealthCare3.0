package src.patient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.*;

import javax.swing.*;

import src.Login;
import src.sqliteConnection;
import src.patient.PatientHome;

public class AddHCP extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private int patientID = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHCP frame = new AddHCP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public AddHCP() {
		connection = sqliteConnection.dbConnector();
		setTitle("Add HCP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(192, 46, 240, 42);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnAddHCP = new JButton("Add HCP");
		btnAddHCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = null;
				ResultSet rs1 = null;
				PreparedStatement pst = null;
				try{
					String query="select * from hcpInfo where ID=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					
					rs = pst.executeQuery();
					String Fname = rs.getString(4);
					String Lname = rs.getString(5);
					int Age = rs.getInt(6);
					String Gender = rs.getString(7);
					
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						pst = connection.prepareStatement("CREATE TABLE IF NOT EXISTS viewHCPList(hcpID int NOT NULL, patientID int NOT NULL, FirstName TEXT NOT NULL, Surname TEXT NOT NULL, Age INT NOT NULL, Gender TEXT NOT NULL)");
						pst.executeUpdate();
						
						String s = Login.getPatientID();
						String s2 = textFieldID.getText();
						
						String query2="Insert into viewHCPList (hcpID,patientID,FirstName,Surname,Age,Gender) values (?,?,?,?,?,?)";
						pst = connection.prepareStatement(query2);
						try{
							int hcpID = Integer.parseInt(s2);
							int patID = Integer.parseInt(s);
							
							pst.setInt(1, hcpID);
							pst.setInt(2, patID);
							pst.setString(3, Fname);
							pst.setString(4, Lname);
							pst.setInt(5, Age);
							pst.setString(6, Gender);
						}catch(Exception e3){
							JOptionPane.showConfirmDialog(null, "ID is not an Integer Value");
						}
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Health care provider Added");
						
					}else if(count > 1){
						JOptionPane.showMessageDialog(null, "ID already added");
					}else{
						JOptionPane.showMessageDialog(null, "ID does not exist");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ID does not exist");
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
		btnAddHCP.setBounds(246, 99, 137, 35);
		contentPane.add(btnAddHCP);
		
		JLabel lblID = new JLabel("HCP ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(111, 47, 112, 37);
		contentPane.add(lblID);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		btnClose.setBounds(246, 181, 137, 35);
		contentPane.add(btnClose);
	}
}
