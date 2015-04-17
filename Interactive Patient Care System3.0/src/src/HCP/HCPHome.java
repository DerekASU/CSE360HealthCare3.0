package src.HCP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import src.Login;
import src.patient.viewHCP;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HCPHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HCPHome frame = new HCPHome();
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
	public HCPHome() {
		setTitle("Health Care Provider Home Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPatient addPatient = new  AddPatient();
				addPatient.setVisible(true);
			}
		});
		btnAddPatient.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddPatient.setBounds(177, 11, 320, 50);
		contentPane.add(btnAddPatient);
		
		JButton btnPatientForms = new JButton("Patient Forms");
		btnPatientForms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientForms patientForms = new PatientForms();
				patientForms.setVisible(true);
			}
		});
		btnPatientForms.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPatientForms.setBounds(177, 197, 320, 50);
		contentPane.add(btnPatientForms);
		
		JButton btnDeletePatient = new JButton("Delete Patient");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePatient deletePatient = new DeletePatient();
				deletePatient.setVisible(true);
			}
		});
		btnDeletePatient.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDeletePatient.setBounds(177, 282, 320, 50);
		contentPane.add(btnDeletePatient);
		
		JButton btnViewPatients = new JButton("View Patients");
		btnViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPatient viewPatient = new viewPatient();
				viewPatient.setVisible(true);
			}
		});
		btnViewPatients.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewPatients.setBounds(177, 100, 320, 50);
		contentPane.add(btnViewPatients);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClose.setBounds(177, 369, 320, 50);
		contentPane.add(btnClose);
		
	}
}
