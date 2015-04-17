package src.HCP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;
import src.Login;
import src.sqliteConnection;
import java.awt.Font;

public class PatientForms extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientForms frame = new PatientForms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	Connection connection1 = null;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public PatientForms() {
		connection1 = sqliteConnection.dbConnector();
		connection = sqliteConnection.dbConnector1();
		setTitle("Previous Forms");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 791, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 167, 756, 413);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("View Patient Forms");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query1="select * from viewPatientList where hcpID=?";
					PreparedStatement pst1 = connection1.prepareStatement(query1);
					pst1.setString(1, Login.getHcpID());
					ResultSet rs1 = pst1.executeQuery();
					
					int count=0;
					while(rs1.next()){
						count = count + 1;
					}
					if(count >= 1){
					String query="select * from formInfo where patientID=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					
					ResultSet rs = pst.executeQuery();
						
					table.setModel(DbUtils.resultSetToTableModel(rs));	
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(275, 67, 206, 43);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(281, 11, 200, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPatientID = new JLabel("Patient ID");
		lblPatientID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPatientID.setBounds(197, 23, 200, 33);
		contentPane.add(lblPatientID);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		btnClose.setBounds(275, 121, 206, 43);
		contentPane.add(btnClose);
	}
}