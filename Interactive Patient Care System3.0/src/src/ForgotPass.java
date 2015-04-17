package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;


public class ForgotPass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 * 
	 */
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPass frame = new ForgotPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	
	public ForgotPass() {
		connection = sqliteConnection.dbConnector();
		setTitle("Forgot Password?");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 100, 200, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterYourPatient = new JLabel("Enter your Patient ID:");
		lblEnterYourPatient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterYourPatient.setBounds(10, 50, 267, 50);
		contentPane.add(lblEnterYourPatient);
		
		btnNewButton = new JButton("Get Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				ResultSet rs = null;
				PreparedStatement pst = null;
				try{
					String query="select * from patientInfo where ID=?";
					pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					
					rs = pst.executeQuery();
					String password = rs.getString("password");
					
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, password);
					} else if(count > 1){
						JOptionPane.showMessageDialog(null, "ID not correct");
					} else{
						JOptionPane.showMessageDialog(null, "ID not correct");
					}
					
				} catch(Exception e){
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(12, 161, 200, 30);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClose.setBounds(12, 209, 200, 30);
		contentPane.add(btnClose);
		
	}
}
