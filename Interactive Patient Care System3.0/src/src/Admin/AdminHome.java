package src.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome frame = new AdminHome();
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
	public AdminHome() {
		setTitle("Admin Home Page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSystemUpdate = new JButton("System Update");
		btnSystemUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "System is already up to date.");
				//SystemUpdate sysUpdate = new SystemUpdate();
				//sysUpdate.setVisible(true);
			}
		});
		btnSystemUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSystemUpdate.setBounds(167, 110, 320, 50);
		contentPane.add(btnSystemUpdate);
		
		JButton btnAddHealthCare = new JButton("Add Health Care Provider");
		btnAddHealthCare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddHCP addHCP = new AddHCP();
				addHCP.setVisible(true);
			}
		});
		btnAddHealthCare.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddHealthCare.setBounds(167, 190, 320, 50);
		contentPane.add(btnAddHealthCare);
		
		JButton btnDeleteHealthCare = new JButton("Delete Health Care Provider");
		btnDeleteHealthCare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteHCP deleteHCP = new DeleteHCP();
				deleteHCP.setVisible(true);
			}
		});
		btnDeleteHealthCare.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDeleteHealthCare.setBounds(167, 277, 320, 50);
		contentPane.add(btnDeleteHealthCare);
		
	}

}
