package com.shatam;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Color;

public class Increment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JTextField enterEid;
	private JTextField textincrement;
	private JLabel lable_increment;
	private JLabel lblNewLabel;
	private JLabel lable_increment_1;
	private double incSal;
//	private JDateChooser dateChooser;
	private int incPer;
	private int incAmt;
	private JTextField textField_incr_amt;
	private JLabel lable_increment_3;
	private JButton btnHome;
	private JButton btnBack;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Increment frame = new Increment();
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
	public Increment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 120, 600, 300);
//	setBounds(30, 30, 300, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(135, 206, 250));
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Increment Employee Salary");
		lblNewLabel.setBounds(166, 33, 346, 32);
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBackground(new Color(224, 255, 255));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel leid = new JLabel("ENTER EID   :");
		leid.setBounds(88, 92, 190, 20);
		leid.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(leid);
		
		enterEid = new JTextField();
		enterEid.setBounds(206, 90, 294, 20);
		contentPane.add(enterEid);
		enterEid.setColumns(10);
		

	
		
		lable_increment = new JLabel("INCREMENT   :");
		lable_increment.setBounds(88, 138, 152, 14);
		lable_increment.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lable_increment);
		textincrement = new JTextField();
		textincrement.setBounds(328, 133, 172, 20);
		contentPane.add(textincrement);
		textincrement.setColumns(10);
		
		
		final JComboBox comboBoxincrement = new JComboBox();
		comboBoxincrement.setBounds(206, 132, 122, 22);
		comboBoxincrement.setEditable(true);
		contentPane.add(comboBoxincrement);
		comboBoxincrement.addItem("   ---Select---");
		comboBoxincrement.addItem("By Percentage");
		comboBoxincrement.addItem("By Amount");

		

		/*
		 * lable_increment_1 = new JLabel("DATE    :"); lable_increment_1.setFont(new
		 * Font("Constantia", Font.BOLD, 12)); lable_increment_1.setBounds(62, 240, 152,
		 * 14); contentPane.add(lable_increment_1);
		 * 
		 * final JDateChooser dateChooser = new JDateChooser();
		 * dateChooser.setBounds(278, 234, 196, 20); contentPane.add(dateChooser);
		 */
		btnNewButton = new JButton("Salary Increment");
		btnNewButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnNewButton.setBounds(214, 200, 152, 23);
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("org.sqlite.JDBC");
					Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

//					Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Shatam\\EmployeeGenerateSalarySlip\\shatamdb.db");

					PreparedStatement ps = null;		
					
					// Fetching eid from salaryHistory table
					ps = conn.prepareStatement("SELECT Salary FROM EmpRegister WHERE eid = ? ");
//					String empId = enterEid.getText();
//					ps.setString(1, empId);
					int empId = Integer.parseInt(enterEid.getText());
					ps.setInt(1, empId);
					
					ResultSet rs = ps.executeQuery();

					if (!rs.next()) {
						JOptionPane.showMessageDialog(btnNewButton, "Please insert correct employee id");
						
						
					}
					else {
							
							System.out.println("Able to fetch details");
							//Getting salary of Employee
//							double salary = Integer.parseInt(rs.getString(1));
							
							//incPer and incSal need to add in salaryHistory table
							
							if ("By Percentage".equals(comboBoxincrement.getSelectedItem()))
							{

								double salary = Double.parseDouble(rs.getString(1));
//								System.out.println("salary "+salary);

								incPer=Integer.parseInt(textincrement.getText());	
								
								double incPerAmt=salary*(incPer/100.0);	 //2500		
								System.out.println("incPerAmt  "  +incPerAmt);
								incSal = salary+incPerAmt; //25000+2500
								System.out.println("incSal  "+ incSal);
								
								
								Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
						        System.out.println(timestamp);
						        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
								
								String query = "INSERT INTO SalaryHistory values (?,?,?,?)";
								ps =conn.prepareStatement(query);
								
								ps.setString(1, dateFormat.format(timestamp));
								ps.setDouble(2, (int)incPerAmt);
								ps.setDouble(3,incSal);
//								ps.setString(4, empId);
								ps.setInt(4, empId);
								int i=ps.executeUpdate();
								 System.out.print(i+" Account is updated");
								
								String queryUpdate = "UPDATE EmpRegister SET Salary = ? WHERE Eid = ?";
							    ps = conn.prepareStatement(queryUpdate);
							    ps.setDouble(1, incSal);
							    ps.setInt(2, empId);
							    int j=ps.executeUpdate();
								 System.out.print(j+" Account is updated");

								ps.executeUpdate();
								
								JOptionPane.showMessageDialog(btnNewButton, "Incremented successfully");
							}
							else if ("By Amount".equals(comboBoxincrement.getSelectedItem()))
							{

//								int salary = Integer.parseInt(rs.getString(1));
								double salary = Double.parseDouble(rs.getString(1));


								incAmt=Integer.parseInt(textincrement.getText());							
								
								System.out.println("incPerAmt  "  +incAmt);
								incSal = salary+incAmt;
								System.out.println("incSal  "+ incSal);
								
								
								Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
						        System.out.println(timestamp);
						        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
								
								String query = "INSERT INTO SalaryHistory values (?,?,?,?)";
								ps =conn.prepareStatement(query);
								
								ps.setString(1, dateFormat.format(timestamp));
								ps.setInt(2, (int)incAmt);
								ps.setDouble(3, incSal);
//								ps.setString(4, empId);
								ps.setInt(4, empId);
								
								int i=ps.executeUpdate();
								 System.out.print(i+" Account is updated");
								
								String queryUpdate = "UPDATE EmpRegister SET Salary = ? WHERE Eid = ?";
							    ps = conn.prepareStatement(queryUpdate);
							    ps.setDouble(1, incSal);
							    ps.setInt(2, empId);
							    int j=ps.executeUpdate();
								 System.out.print(j+" Account is updated");
								
							

								ps.executeUpdate();
								
								JOptionPane.showMessageDialog(btnNewButton, "Incremented successfully");
								
							}
							else {
								JOptionPane.showMessageDialog(btnNewButton, "Error Message : Select One option");

								
							}				
							
					}

					
					rs.close();
					ps.close();
					conn.close();

				}
				catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
			contentPane.add(btnNewButton);
			
			btnHome = new JButton("Home");
			btnHome.setFont(new Font("Book Antiqua", Font.BOLD, 11));
			btnHome.setBounds(410, 200, 105, 23);
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Home home = new Home();
					home.setVisible(true);
			        dispose();

				}
			});
			btnHome.setForeground(new Color(0, 0, 139));
			btnHome.setBackground(new Color(135, 206, 250));
			contentPane.add(btnHome);
			
			btnBack = new JButton("Back");
			btnBack.setFont(new Font("Book Antiqua", Font.BOLD, 11));
			btnBack.setBounds(73, 200, 105, 23);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Register register = new Register();
					register.setVisible(true);	
			        dispose();

				}
			});
			btnBack.setForeground(new Color(0, 0, 139));
			btnBack.setBackground(new Color(135, 206, 250));
			contentPane.add(btnBack);
			
			
			
			
	}
}
