package src.patient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import java.sql.*;

import javax.swing.*;

import src.Login;
import src.sqliteConnection;

import java.lang.Math;

public class NewForm extends JFrame {

	private JPanel contentPane;
	
	private float avgHead;
	private float avgNumb;
	private float avgPain;
	private float avgDrow;
	private float avgBlur;
	private float columnSize;
	float variHead;
	float variNumb;
	float variPain;
	float variDrow;
	float variBlur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewForm frame = new NewForm();
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
	public NewForm() {
		connection = sqliteConnection.dbConnector1();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 899, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox headBox = new JComboBox();
		headBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		headBox.setBounds(210, 72, 129, 39);
		contentPane.add(headBox);
		
		final JComboBox numbBox = new JComboBox();
		numbBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		numbBox.setBounds(210, 130, 129, 39);
		contentPane.add(numbBox);
		
		final JComboBox painBox = new JComboBox();
		painBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		painBox.setBounds(210, 263, 129, 39);
		contentPane.add(painBox);
		
		final JComboBox drowBox = new JComboBox();
		drowBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		drowBox.setBounds(210, 198, 129, 39);
		contentPane.add(drowBox);
		
		final JComboBox blurBox = new JComboBox();
		blurBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		blurBox.setBounds(210, 328, 129, 39);
		contentPane.add(blurBox);
		
		final JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(374, 72, 420, 295);
		contentPane.add(textArea);
		
		JLabel lblHead = new JLabel("Headache");
		lblHead.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHead.setBounds(71, 70, 129, 39);
		contentPane.add(lblHead);
		
		JLabel lblNumb = new JLabel("Numbness");
		lblNumb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumb.setBounds(71, 128, 129, 39);
		contentPane.add(lblNumb);
		
		JLabel lblPain = new JLabel("Muscle Pain");
		lblPain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPain.setBounds(71, 196, 129, 39);
		contentPane.add(lblPain);
		
		JLabel lblDrow = new JLabel("Drowsiness");
		lblDrow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDrow.setBounds(71, 261, 129, 39);
		contentPane.add(lblDrow);
		
		JLabel lblBlur = new JLabel("Blurred Vision");
		lblBlur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBlur.setBounds(71, 326, 129, 39);
		contentPane.add(lblBlur);
		
		JLabel lblAddInfo = new JLabel("Additional Information");
		lblAddInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddInfo.setBounds(374, 30, 165, 39);
		contentPane.add(lblAddInfo);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Object s1 = headBox.getSelectedItem();
					Object s2 = numbBox.getSelectedItem();
					Object s3 = painBox.getSelectedItem();
					Object s4 = drowBox.getSelectedItem();
					Object s5 = blurBox.getSelectedItem();
					
					String query="insert into formInfo (PatientID,Headache,Numbness,MusclePain,Drowsiness,BlurredVision,AvgHealth,Notes) values (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					try{
						int id = Integer.parseInt(Login.getPatientID());
						int head = Integer.parseInt((String) s1);
						int numb = Integer.parseInt((String) s2);
						int pain = Integer.parseInt((String) s3);
						int drow = Integer.parseInt((String) s4);
						int blur = Integer.parseInt((String) s5);
						int avgHealth = (head+numb+pain+drow+blur)/5;
						pst.setInt(1, id);
						pst.setInt(2, head);
						pst.setInt(3, numb);
						pst.setInt(4, pain);
						pst.setInt(5, drow);
						pst.setInt(6, blur);
						pst.setInt(7, avgHealth);
						pst.setString(8, textArea.getText());
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, ex);
					}
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Form Submitted");
					
					pst.close();
					
					String query1="select AVG(Headache) from formInfo";
					pst = connection.prepareStatement(query1);
					ResultSet avgHead = pst.executeQuery();
					setAvgHead(avgHead.getFloat(1)); 
					
					String query2="select AVG(Numbness) from formInfo";
					pst = connection.prepareStatement(query2);
					ResultSet avgNumb = pst.executeQuery();
					setAvgNumb(avgNumb.getFloat(1));
					
					String query3="select AVG(MusclePain) from formInfo";
					pst = connection.prepareStatement(query3);
					ResultSet avgPain = pst.executeQuery();
					setAvgPain(avgPain.getFloat(1));
					
					String query4="select AVG(Drowsiness) from formInfo";
					pst = connection.prepareStatement(query4);
					ResultSet avgDrow = pst.executeQuery();
					setAvgDrow(avgDrow.getFloat(1));
					
					String query5="select AVG(BlurredVision) from formInfo";
					pst = connection.prepareStatement(query5);
					ResultSet avgBlur = pst.executeQuery();
					setAvgBlur(avgBlur.getFloat(1));
					
					String query6="select COUNT(Headache) FROM formInfo";
					pst = connection.prepareStatement(query6);
					ResultSet colSize = pst.executeQuery();
					setColumnSize(colSize.getFloat(1));
					
					variHead = (float) Math.sqrt( Math.pow((getAvgHead() - Integer.parseInt((String) s1)),2)/getColumnSize());
					System.out.println(variHead);
					variNumb = (float) Math.sqrt( Math.pow((getAvgNumb() - Integer.parseInt((String) s2)),2)/getColumnSize());
					System.out.println(variNumb);
					variPain = (float) Math.sqrt( Math.pow((getAvgPain() - Integer.parseInt((String) s3)),2)/getColumnSize());
					System.out.println(variPain);
					variDrow = (float) Math.sqrt( Math.pow((getAvgDrow() - Integer.parseInt((String) s4)),2)/getColumnSize());
					System.out.println(variDrow);
					variBlur = (float) Math.sqrt( Math.pow((getAvgBlur() - Integer.parseInt((String) s5)),2)/getColumnSize());
					System.out.println(variBlur);
					
					int counter = 0;
					int counter2 = 0;
					
					if(variHead >= 2 || Integer.parseInt((String) s1) >= 9 ){
						counter++;
					}else if(variHead >= 2 &&  Integer.parseInt((String) s1) <= 7){
						counter = 0;
					}else if(variBlur >= 1){
						counter2++;
					}else{
						counter = counter;
						counter2 = counter2;
					}
					
					if(variNumb >= 2 || Integer.parseInt((String) s2) >= 9 ){
						counter++;
					}else if(variBlur >= 1){
						counter2++;
					}else{
						counter = counter;
						counter2 = counter2;
					}
					
					if(variPain >= 2 || Integer.parseInt((String) s3) >= 9 ){
						counter++;
					}else if(variBlur >= 1){
						counter2++;
					}else{
						counter = counter;
						counter2 = counter2;
					}
					
					if(variDrow >= 2 || Integer.parseInt((String) s4) >= 9 ){
						counter++;
					}else if(variBlur >= 1){
						counter2++;
					}else{
						counter = counter;
						counter2 = counter2;
					}
					
					if(variBlur >= 2 || Integer.parseInt((String) s5) >= 9 ){
						counter++;
					}else if(variBlur >= 1){
						counter2++;
					}else{
						counter = counter;
						counter2 = counter2;
					}
					
					if(counter >= 3){
						JOptionPane.showMessageDialog(null, "9-1-1 was notified, your symptoms appear to be severe.");
					}else if(counter2 >=1){
						JOptionPane.showMessageDialog(null, "Schedule an appoitment with a health care provider.");
					}else{
						JOptionPane.showMessageDialog(null, "You appear to be healthy.\n"
								+ "If you are concerned you can still make an appointment with a health care provider.");
					}
					
					pst.close();
					avgHead.close();
				
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(520, 400, 156, 38);
		contentPane.add(btnSubmit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				headBox.setSelectedIndex(0);
				numbBox.setSelectedIndex(0);
				painBox.setSelectedIndex(0);
				drowBox.setSelectedIndex(0);
				blurBox.setSelectedIndex(0);
			}
		});
		btnReset.setBounds(125, 400, 156, 38);
		contentPane.add(btnReset);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnClose.setBounds(374, 464, 156, 38);
		contentPane.add(btnClose);
	}
	
	public float getAvgHead() {
		return avgHead;
	}
	public void setAvgHead(float f) {
		this.avgHead = f;
	}

	public float getAvgNumb() {
		return avgNumb;
	}

	public void setAvgNumb(float avgNumb) {
		this.avgNumb = avgNumb;
	}

	public float getAvgPain() {
		return avgPain;
	}

	public void setAvgPain(float avgPain) {
		this.avgPain = avgPain;
	}

	public float getAvgDrow() {
		return avgDrow;
	}

	public void setAvgDrow(float avgDrow) {
		this.avgDrow = avgDrow;
	}

	public float getAvgBlur() {
		return avgBlur;
	}

	public void setAvgBlur(float avgBlur) {
		this.avgBlur = avgBlur;
	}

	public float getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(float columnSize) {
		this.columnSize = columnSize;
	}
	
}
