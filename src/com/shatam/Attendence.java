package com.shatam;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

public class Attendence extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Attendence frame = new Attendence();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Attendence() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 120, 600, 300);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(0, 0, 139));
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Attendence Sheet");
        lblNewLabel.setBounds(200, 22, 175, 23);
        lblNewLabel.setForeground(new Color(0, 0, 128));
        lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Name :");
        lblNewLabel_1.setBounds(103, 77, 70, 14);
        lblNewLabel_1.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_1.setForeground(new Color(139, 0, 0));
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Date and Time:");
        lblNewLabel_1_1.setBounds(103, 119, 110, 14);
        lblNewLabel_1_1.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_1_1.setForeground(new Color(139, 0, 0));
        contentPane.add(lblNewLabel_1_1);
        
        SpinnerDateModel dateSpinnerModel = new SpinnerDateModel();
        final JSpinner dateSpinner = new JSpinner(dateSpinnerModel);
        dateSpinner.setBounds(214, 119, 151, 20);
        contentPane.add(dateSpinner);

        JLabel lblNewLabel_1_2 = new JLabel("Attendence :");
        lblNewLabel_1_2.setBounds(103, 167, 111, 14);
        lblNewLabel_1_2.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_1_2.setForeground(new Color(139, 0, 0));
        contentPane.add(lblNewLabel_1_2);

        final JComboBox<String> nameComboBox = new JComboBox<>();
        nameComboBox.setBounds(214, 74, 151, 22);
        contentPane.add(nameComboBox);

        ButtonGroup attendanceGroup = new ButtonGroup();

        final JRadioButton presentRadioButton = new JRadioButton("Present");
        presentRadioButton.setBounds(214, 167, 121, 23);
        presentRadioButton.setSelected(true);
        contentPane.add(presentRadioButton);
        attendanceGroup.add(presentRadioButton);

        JRadioButton absentRadioButton = new JRadioButton("Absent");
        absentRadioButton.setBounds(337, 167, 110, 23);
        contentPane.add(absentRadioButton);
        attendanceGroup.add(absentRadioButton);
        
        
        

        final JButton btnNewButton = new JButton("Submit");
        btnNewButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnNewButton.setBounds(252, 214, 101, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitAttendance();
            }
            private void submitAttendance() {
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

                    String selectedName = (String) nameComboBox.getSelectedItem();

                    Date selectedDate = (Date) dateSpinner.getValue();
                    Timestamp timestamp = new Timestamp(selectedDate.getTime());
                    String formattedDateTime = timestamp.toString(); // Convert Timestamp to String in a readable format

                    boolean isPresent = presentRadioButton.isSelected();
                    String attendanceStatus = isPresent ? "Present" : "Absent";

                    // Check if a record with the same date already exists (ignoring time)
//                    String checkSql = "SELECT COUNT(*) FROM attendancesheet WHERE strftime('%Y-%m-%d', date_time) = strftime('%Y-%m-%d', ?) AND name = ?";
                    String checkSql = "SELECT COUNT(*) FROM attendancesheet WHERE DATE(date_time) = DATE(?) AND name = ?";

                    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                        checkStmt.setString(1, formattedDateTime);
                        checkStmt.setString(2, selectedName);
                        ResultSet resultSet = checkStmt.executeQuery();

                        if (resultSet.next()) {
                            int count = resultSet.getInt(1);
                            if (count > 0) {
                                // Record with the same date and name already exists
                                JDialog dialog = new JDialog(Attendence.this, "Warning", true);
                                JLabel label = new JLabel("           Attendance for this date and name already recorded!");
                                label.setForeground(Color.RED);
                                dialog.getContentPane().add(label);
                                dialog.setSize(400, 150);
                                dialog.setLocationRelativeTo(Attendence.this);
                                dialog.setVisible(true);
                                return;
                            }
                        }
                    }

                    // If no record with the same date exists, insert a new record
                    String insertSql = "INSERT INTO attendancesheet (name, date_time, attendance) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                        pstmt.setString(1, selectedName);
                        pstmt.setString(2, formattedDateTime);
                        pstmt.setString(3, attendanceStatus);

                        int i = pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(btnNewButton, i + " Attendance recorded successfully!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });
        btnNewButton.setBackground(new Color(135, 206, 250));
        btnNewButton.setForeground(new Color(0, 0, 139));
        contentPane.add(btnNewButton);

        JButton btnGetData = new JButton("Get Data");
        btnGetData.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnGetData.setBounds(361, 74, 89, 23);
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

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnBack.setBounds(125, 214, 89, 23);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setVisible(true);
            }
        });
        btnBack.setForeground(new Color(0, 0, 139));
        btnBack.setBackground(new Color(135, 206, 250));
        contentPane.add(btnBack);
        
        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        btnView.setBounds(384, 214, 89, 23);
        btnView.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ViewAttendenceDetails view = new ViewAttendenceDetails();
                view.setVisible(true);
        		
        	}
        });
        btnView.setForeground(new Color(0, 0, 139));
        btnView.setBackground(new Color(135, 206, 250));
        contentPane.add(btnView);
    }
}
///Update
//

//
