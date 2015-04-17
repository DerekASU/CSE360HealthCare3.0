package src.HCP;

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

public class DeletePatient extends JFrame {

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
					DeletePatient frame = new DeletePatient();
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
	public DeletePatient() {
		connection = sqliteConnection.dbConnector();
		setTitle("Remove Patient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(195, 103, 240, 42);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnDeletePatient = new JButton("Remove Patient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = null;
				ResultSet rs1 = null;
				PreparedStatement pst = null;
				try{
					String query="select * from patientInfo where ID=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					
					rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						String s = Login.getHcpID();
						String s2 = textFieldID.getText();
						
						String query2="DELETE FROM viewPatientList WHERE hcpID=? and patientID=?";
						pst = connection.prepareStatement(query2);
						try{
							int hcpID = Integer.parseInt(s);
							int patID = Integer.parseInt(s2);
							
							pst.setInt(1, hcpID);
							pst.setInt(2, patID);
						}catch(Exception e3){
							JOptionPane.showConfirmDialog(null, "ID is not an Integer Value");
						}
						
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Patient Removed");
						
					}else{
						JOptionPane.showMessageDialog(null, "ID does not exsist");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
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
		btnDeletePatient.setBounds(249, 156, 137, 35);
		contentPane.add(btnDeletePatient);
		
		JLabel lblID = new JLabel("Patient ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(114, 104, 112, 37);
		contentPane.add(lblID);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
			}
		});
		btnClose.setBounds(249, 202, 137, 35);
		contentPane.add(btnClose);
	}
}

