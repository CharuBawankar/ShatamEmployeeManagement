package com.shatam;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public class ViewAttendenceDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
 
    private DefaultTableModel tableModel; 
    private JTextField absent_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAttendenceDetails frame = new ViewAttendenceDetails();
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
	public ViewAttendenceDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 120, 600, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
        contentPane.setLayout(null);
		
        final JComboBox<String> nameComboBox = new JComboBox<>();
        nameComboBox.setBounds(156, 46, 194, 22);
        contentPane.add(nameComboBox);

        JButton btnGetData = new JButton("Get Data");
        btnGetData.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnGetData.setBounds(348, 46, 125, 23);
        btnGetData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection(
                            "jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery("Select name from EmpRegister");
                    while (rs.next()) {
                        String name = rs.getString("name");
                        nameComboBox.addItem(name);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnGetData.setForeground(new Color(0, 0, 139));
        btnGetData.setBackground(new Color(135, 206, 250));
        contentPane.add(btnGetData);
		
		JLabel lblNewLabel_1 = new JLabel("From :");
		lblNewLabel_1.setBounds(107, 89, 46, 14);
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Attendence Details");
		lblNewLabel.setBounds(214, 11, 190, 24);
		lblNewLabel.setBackground(new Color(248, 248, 255));
		lblNewLabel.setForeground(new Color(139, 0, 139));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("To :");
		lblNewLabel_1_1.setBounds(311, 89, 46, 14);
		lblNewLabel_1_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1_1);
		
	

        final JDateChooser dateChooser1 = new JDateChooser();
        dateChooser1.setBounds(156, 87, 145, 20);
        contentPane.add(dateChooser1);
        
        final JDateChooser dateChooser2 = new JDateChooser();
        dateChooser2.setBounds(335, 87, 138, 20);
        contentPane.add(dateChooser2);
        
        final JTextField present_textField = new JTextField();
        present_textField.setBounds(213, 288, 86, 20);
        contentPane.add(present_textField);
        present_textField.setColumns(10);
        
        absent_textField = new JTextField();
        absent_textField.setBounds(420, 288, 86, 20);
        absent_textField.setColumns(10);
        contentPane.add(absent_textField);
        
        JLabel lblNewLabel_2 = new JLabel("Absent");
        lblNewLabel_2.setBounds(348, 290, 56, 14);
        lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_2.setForeground(new Color(128, 0, 0));
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Present");
        lblNewLabel_2_1.setBounds(128, 290, 56, 14);
        lblNewLabel_2_1.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_2_1.setForeground(new Color(128, 0, 0));
        contentPane.add(lblNewLabel_2_1);


            // initialize JTable and DefaultTableModel
            tableModel = new DefaultTableModel();
            table = new JTable(tableModel);

            // add column headers
            tableModel.addColumn("Date and Time");
            tableModel.addColumn("Attendence Status");

       
            // Adding JScrollPane for the table
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(88, 167, 414, 99);
            contentPane.add(scrollPane);
            
            
            
            JButton btnNewButton = new JButton("Search");
            btnNewButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
            btnNewButton.setBounds(239, 119, 89, 23);
            btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		
                    searchAttendanceDetails();
            	}
            private void searchAttendanceDetails()	{			// TODO Auto-generated method stub
				 
	                try {
	                    // Get selected dates from JDateChoosers
	                	java.util.Date fromDate = dateChooser1.getDate();
	                    java.util.Date toDate = dateChooser2.getDate();

	                    // Format dates to match the database date format
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    String formattedFromDate = sdf.format(fromDate);
	                    String formattedToDate = sdf.format(toDate);
	                    

	                    // Get selected name from the JComboBox
	                    String selectedName = (String) nameComboBox.getSelectedItem();
	                    System.out.println("selectedName:::"+selectedName);
	                    System.out.println("FromDate :::"+formattedFromDate);
	                    System.out.println("ToDate ::"+formattedToDate);

	                    // Query the database using the selected name and date range
//	                    String query = "SELECT date_time, status FROM attendancesheet WHERE name = ? AND date_time BETWEEN formattedFromDate % AND formattedToDate";
//	                    String query = "SELECT date_time, status FROM attendancesheet WHERE name = ? AND date_time BETWEEN formattedFromDate % AND formattedToDate%";
	                    String query = "SELECT date_time, attendance FROM attendancesheet WHERE name = ? AND date_time BETWEEN ? AND ?";
	                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");
	                         PreparedStatement pstmt = conn.prepareStatement(query)) {

	                        pstmt.setString(1, selectedName);
	                        pstmt.setString(2, formattedFromDate + " 00:00:00"); // Start of the selected date
	                        pstmt.setString(3, formattedToDate + " 23:59:59"); // End of the selected date

	                        ResultSet rs = pstmt.executeQuery();

	                        // Clear existing table data
	                        tableModel.setRowCount(0);

	                        int presentCount = 0;
							int absentCount=0;
							// Process and display the results in the table
	                        while (rs.next()) {
	                            String attendanceDate = rs.getString("date_time");
	                            String attendanceStatus = rs.getString("attendance");

	                            // Add data to the table model
	                            tableModel.addRow(new Object[]{attendanceDate, attendanceStatus});
	                            //Find count absent and present 
	                            if ("Present".equalsIgnoreCase(attendanceStatus)) {
	                                presentCount++;
	                            } else if ("Absent".equalsIgnoreCase(attendanceStatus)) {
	                                absentCount++;
	                            }
	                            
	                        }
	                        System.out.println("Present Count ::" +presentCount);
	                        System.out.println("Absent Count ::" +absentCount);

	                        present_textField.setText(String.valueOf(presentCount));
	                        absent_textField.setText(String.valueOf(absentCount));

	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();	                }
	            }
        });
        btnNewButton.setForeground(new Color(0, 0, 139));
        btnNewButton.setBackground(new Color(135, 206, 250));
        contentPane.add(btnNewButton);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnBack.setBounds(117, 119, 89, 23);
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Attendence attendence = new Attendence();
        		attendence.setVisible(true);
		        dispose();

        	}
        });
        btnBack.setForeground(new Color(0, 0, 139));
        btnBack.setBackground(new Color(135, 206, 250));
        contentPane.add(btnBack);
        
        JLabel lblNewLabel_1_2 = new JLabel("Name :");
        lblNewLabel_1_2.setBounds(107, 50, 46, 14);
        lblNewLabel_1_2.setForeground(new Color(139, 0, 0));
        lblNewLabel_1_2.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        contentPane.add(lblNewLabel_1_2);
        
        JButton btnExportCsv = new JButton("Export CSV");
        btnExportCsv.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnExportCsv.setBounds(359, 119, 114, 23);
        btnExportCsv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  String selectedName = (String) nameComboBox.getSelectedItem();
        	        String fileName = "AttendanceSheet_" + selectedName + ".csv";
        	        exportToCSV(fileName);

        		
        	}

        	private void exportToCSV(String fileName) {
        	    try {
        	       
        	    	String filePath = "C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\" + fileName;

        	        System.out.println("Full file path: " + filePath); // Print the full file path

        	        FileWriter fileWriter = new FileWriter(filePath); // FileWriter object
        	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // Create a BufferedWriter to write data

        	        // Write the CSV header with five columns including Index
        	        bufferedWriter.write("Index,Name,Date,Time,Attendance_Status");
        	        bufferedWriter.newLine();
        	        int index = 1;
        	        // Iterate table data and write each row to the CSV file
        	        for (int row = 0; row < table.getRowCount(); row++) {
        	            String name = (String) nameComboBox.getSelectedItem();
        	            String dateTime = (String) table.getValueAt(row, 0);
        	            String attendanceStatus = (String) table.getValueAt(row, 1);

        	            // Parse date and time from the dateTime string
        	            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	            SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm:ss");

        	            try {
        	                java.util.Date parsedDateTime = inputDateFormat.parse(dateTime);
        	                String formattedTime = outputTimeFormat.format(parsedDateTime);

        	                // Split date and time
        	                String[] dateTimeParts = dateTime.split(" ");
        	                String date = dateTimeParts[0];

        	                // Write data to the CSV file with five column
        	                bufferedWriter.write(index + "," + name + "," + date + "," + formattedTime + "," + attendanceStatus);
        	                bufferedWriter.newLine();

        	                index++;
        	            } catch (Exception e) {
        	                e.printStackTrace();
        	            }
        	        }

        	        bufferedWriter.close();
                    System.out.println("Export to CSV file successfully.....!!!");
        	        System.out.println("CSV FILE PATH ::::::::C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip : " + fileName);
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	}

			
	
        });
        btnExportCsv.setForeground(new Color(0, 0, 139));
        btnExportCsv.setBackground(new Color(135, 206, 250));
        contentPane.add(btnExportCsv);
        
       
     

        
	}
}
