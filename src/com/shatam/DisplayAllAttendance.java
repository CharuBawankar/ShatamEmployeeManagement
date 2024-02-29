package com.shatam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class DisplayAllAttendance extends JFrame {

    private JPanel contentPane;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JScrollPane scrollPane;
    private int daysInMonth;

    public static void main(String[] args) {
        DisplayAllAttendance frame = new DisplayAllAttendance();
        frame.setVisible(true);
    }

    public DisplayAllAttendance() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("All Attendance Details ");
        lblNewLabel_1.setBounds(408, 11, 385, 53);
        lblNewLabel_1.setBackground(new Color(250, 235, 215));
        lblNewLabel_1.setForeground(new Color(128, 0, 128));
        lblNewLabel_1.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        contentPane.add(lblNewLabel_1);

        JButton BackButton = new JButton("Back");
        BackButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        BackButton.setBackground(new Color(135, 206, 250));
        BackButton.setForeground(new Color(0, 0, 139));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setVisible(true);
                dispose();
            }
        });
        BackButton.setBounds(603, 461, 89, 23);
        contentPane.add(BackButton);

        JLabel lblNewLabel_2 = new JLabel("Enter Month :");
        lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        lblNewLabel_2.setBounds(278, 79, 95, 14);
        contentPane.add(lblNewLabel_2);

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        String[] years = new String[20];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 20; i++) {
            years[i] = String.valueOf(currentYear - 10 + i);
        }

        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(383, 75, 80, 20);
        contentPane.add(monthComboBox);

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(462, 75, 100, 20);
        contentPane.add(yearComboBox);
        yearComboBox.setSelectedItem("2024");//Default year set here

        // Initialize the table model with the "Name" column
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel) {
            @Override
            protected void configureEnclosingScrollPane() {
                super.configureEnclosingScrollPane();
                setTableHeader(createDefaultTableHeader());
            }
        };

          // // Set the header size
        
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new java.awt.Dimension(header.getPreferredSize().width, 25));
           // Set the header view to scroll with the table horizontally
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);

        // Create a scroll pane for the table
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 129, 1000, 320);
        contentPane.add(scrollPane);

        // Add the header to the scroll pane
        JScrollPane headerScrollPane = new JScrollPane(table.getTableHeader());
        headerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        headerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        headerScrollPane.setBounds(50, 104, 1000, 25); // Adjust the height as needed
        contentPane.add(headerScrollPane);

        // Disable auto-resizing for the table, as the header is in a separate scroll pane
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);

        // Set the header view to scroll with the table horizontally
        scrollPane.setColumnHeaderView(table.getTableHeader());

        // Add a listener to synchronize the scroll position of the table and header
        AdjustmentListener adjustmentListener = e -> {
            int value = e.getValue();
            headerScrollPane.getHorizontalScrollBar().setValue(value);
            scrollPane.getHorizontalScrollBar().setValue(value);
        };

        // Add the listener to both the table and header scroll panes
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);
        headerScrollPane.getHorizontalScrollBar().addAdjustmentListener(adjustmentListener);

//        System.out.println("Charuuuuuu...333");

        JButton showDateButton = new JButton("Show");
        showDateButton.setForeground(new Color(0, 0, 139));
        showDateButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
        showDateButton.setBackground(new Color(135, 206, 250));

        showDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String selectedYear = (String) yearComboBox.getSelectedItem();
//                System.out.println("Charuuuuuu...11");

                // Update the table header with the correct number of days
                int daysInMonth = getDaysInMonth(selectedMonth, Integer.parseInt(selectedYear));
//                System.out.println("Charuuuuuu...22");
                updateTableHeader(daysInMonth);//method call

                // Fetch and update the table data
                fetchDataAndUpdateTable(selectedMonth, selectedYear);

                // Set the header view to scroll with the table horizontally
                scrollPane.setColumnHeaderView(table.getTableHeader());

                // Reset the scroll position to the beginning
                scrollPane.getHorizontalScrollBar().setValue(0);
            }
        });
        showDateButton.setBounds(603, 75, 89, 23);
        contentPane.add(showDateButton);
    }

    // Method to get the number of days in a month
    private int getDaysInMonth(String selectedMonth, int selectedYear) {
        String date = "01 " + selectedMonth + " " + selectedYear;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
//        System.out.println("Charuuuuuu...44");

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            System.out.println("calendar getDaysInMonth :"+calendar.getActualMaximum(Calendar.DAY_OF_MONTH));///
        // Chat gpt take one for loop for getDaysInMonth to iterate date one by one and match date with attendencesheet table from database. if date is match with getDaysinMonth then check attendence status and if present write p if absent write a
             return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    
    private void updateTableHeader(int daysInMonth) {
        // Clear existing columns
        tableModel.setColumnCount(0);
        System.out.println("Charuuuuuu...55");

        // Add "Name" column
        tableModel.addColumn(" Employee Name");

        // Add date columns
        for (int i = 1; i <= daysInMonth; i++) {
            tableModel.addColumn(String.valueOf(i));
        }
        tableModel.addColumn("Total Present");
        tableModel.addColumn("Total Leaves");
        // Set up the TableColumnModel for the table
        TableColumnModel columnModel = table.getColumnModel();

        // Adjust the width of the "Employee Name"
        TableColumn nameColumn = columnModel.getColumn(0);
        nameColumn.setPreferredWidth(150);

        // Adjust width of date columns
        for (int i = 1; i <= daysInMonth; i++) {
            TableColumn dateColumn = columnModel.getColumn(i);
            dateColumn.setPreferredWidth(21);
        }

        // Adjust width of the "Total Present" column
        TableColumn presentColumn = columnModel.getColumn(daysInMonth + 1);
        presentColumn.setPreferredWidth(100);

        // Adjust the width of the "Total Leaves" column
        TableColumn leavesColumn = columnModel.getColumn(daysInMonth + 2);
        leavesColumn.setPreferredWidth(100);

    }

    private void fetchDataAndUpdateTable(String selectedMonth, String selectedYear) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");
          
            String query = "SELECT name, date_time FROM attendancesheet WHERE strftime('%m', date(date_time)) = ? AND strftime('%Y', date(date_time)) = ?";
            
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, String.format("%02d", monthComboBox.getSelectedIndex() + 1));
                preparedStatement.setString(2, selectedYear);

                ResultSet resultSet = preparedStatement.executeQuery();
                tableModel.setRowCount(0);

                // Create a map to store attendance data for each employee
                Map<String, Map<Integer, String>> attendanceMap = new HashMap<>();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");

                    // Get date and extract day, month, and year
                    String dateString = resultSet.getString("date_time");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sdf.parse(dateString));
                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                    // Get attendance status for the day
                    String attendanceStatus = getAttendanceStatus(name, calendar, dayOfMonth, selectedMonth, selectedYear);
                    System.out.println("attendanceStatus  " + attendanceStatus);
                    // Update the attendance map
                    attendanceMap.computeIfAbsent(name, k -> new HashMap<>()).put(dayOfMonth, attendanceStatus);
                }

                // Populate the table model with the data from the attendance map
                for (Map.Entry<String, Map<Integer, String>> entry : attendanceMap.entrySet()) {
                    String name = entry.getKey();
                    Map<Integer, String> attendanceData = entry.getValue();

                    Object[] rowData = new Object[tableModel.getColumnCount()];
                    rowData[0] = name;

                    // Populate attendance status for each day
                    int totalAbsent = 0; 
                    System.out.println("attendanceData" + attendanceData);

                    for (int i = 1; i < tableModel.getColumnCount() - 2; i++) {
                        rowData[i] = attendanceData.getOrDefault(i, "A");

                        // Check if attendance status 
                        if (rowData[i].equals("A")) {
                            totalAbsent++;
                        }
                    }

                    // Calculate Total Present and Total Absent
                    int totalPresent = (int) attendanceData.values().stream().filter(status -> status.equals("P")).count();
                    rowData[tableModel.getColumnCount() - 2] = totalPresent;  // From last second tableModel update
                    rowData[tableModel.getColumnCount() - 1] = totalAbsent;  // Total Absent for the employee

                   
                    tableModel.addRow(rowData); // Add the row to the table model
                }
            }
            conn.close();
        } catch (SQLException | java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }

    // Method to get attendance status (Present or Absent) for a specific day
    private String getAttendanceStatus(String name, Calendar calendar, int dayOfMonth, String selectedMonth, String selectedYear) {
        String dateString = selectedYear + "-" + String.format("%02d", monthComboBox.getSelectedIndex() + 1) + "-" + String.format("%02d", dayOfMonth);

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");
            String query = "SELECT attendance FROM attendancesheet WHERE name = ? AND date(date_time) = ?";

            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, dateString);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // If there is a record for the specified name and date, mark as Present or Absent based on the 'attendance' column
                    String attendanceStatus = resultSet.getString("attendance");
                    return attendanceStatus.equalsIgnoreCase("Present") ? "P" : "A";
                } else {
                	
                    return "A";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "A";
    }

}
