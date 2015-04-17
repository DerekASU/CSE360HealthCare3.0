package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldUN;
	private JTextField textFieldPW;
	private JTextField textFieldName;
	private JTextField textFieldLname;
	private JTextField textFieldAge;
	private JTextField textFieldGender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {
		connection = sqliteConnection.dbConnector();
		setTitle("Account Creation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String s = textFieldID.getText();
					String s2 = textFieldAge.getText();
					
					String query="insert into patientInfo (ID,Username,Password,Name,Surname,Age,Gender) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					try{
						int id = Integer.parseInt(s);
						int age = Integer.parseInt(s2);
						pst.setInt(1, id);
						pst.setString(2, textFieldUN.getText());
						pst.setString(3, textFieldPW.getText());
						pst.setString(4, textFieldName.getText());
						pst.setString(5, textFieldLname.getText());
						pst.setInt(6, age);
						pst.setString(7, textFieldGender.getText());
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "Integers only for ID and/or Age");
					}
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Account Created");
					
					pst.close();
					
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "ID or Username already exist");
				}
				
			}
		});
		btnCreate.setBounds(69, 674, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUN.setText("");
				textFieldPW.setText("");
				textFieldAge.setText("");
				textFieldName.setText("");
				textFieldLname.setText("");
				textFieldGender.setText("");
				textFieldID.setText("");
			}
		});
		btnReset.setBounds(187, 674, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		btnClose.setBounds(300, 674, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblID.setBounds(30, 24, 200, 34);
		contentPane.add(lblID);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(30, 80, 200, 34);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(30, 137, 200, 34);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("First Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(30, 251, 200, 34);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Last Name:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSurname.setBounds(30, 313, 200, 34);
		contentPane.add(lblSurname);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAge.setBounds(30, 379, 200, 34);
		contentPane.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGender.setBounds(30, 444, 200, 34);
		contentPane.add(lblGender);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(55, 34, 190, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldUN = new JTextField();
		textFieldUN.setColumns(10);
		textFieldUN.setBounds(115, 90, 190, 20);
		contentPane.add(textFieldUN);
		
		textFieldPW = new JTextField();
		textFieldPW.setColumns(10);
		textFieldPW.setBounds(115, 147, 190, 20);
		contentPane.add(textFieldPW);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(120, 261, 190, 20);
		contentPane.add(textFieldName);
		
		textFieldLname = new JTextField();
		textFieldLname.setColumns(10);
		textFieldLname.setBounds(120, 323, 190, 20);
		contentPane.add(textFieldLname);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(67, 389, 190, 20);
		contentPane.add(textFieldAge);
		
		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		textFieldGender.setBounds(92, 453, 190, 20);
		contentPane.add(textFieldGender);
		
		JLabel lblieMaleOr = new JLabel("(i.e: Male or Female)");
		lblieMaleOr.setBounds(292, 457, 124, 14);
		contentPane.add(lblieMaleOr);
		
	}
}
