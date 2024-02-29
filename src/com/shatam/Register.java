package com.shatam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.toedter.calendar.JDateChooser;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textEid;
    private JTextField textSal;
    private JTextField textDesg;
    private JTextField textMobNo;
    private JTextField textpan_No;
    private JTextField textadhar_No;
    private JTextField textAddr;
	private JButton btnPanView;
	private JButton btnAadharView;
	public File panDocFile;
	private File aadharDocFile;

    private final ButtonGroup buttonGroup = new ButtonGroup();
  
	protected String file;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 70, 650, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Register New Employee Detail");
        lblNewLabel.setBounds(148, 11, 690, 33);
        lblNewLabel.setForeground(new Color(75, 0, 130));
        lblNewLabel.setBackground(new Color(255, 0, 0));
        lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        contentPane.add(lblNewLabel);
			
		JLabel lblNewLabel_4 = new JLabel("NAME   :");
		lblNewLabel_4.setBounds(92, 60, 156, 17);
		lblNewLabel_4.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("EMAIL    :");
		lblNewLabel_5.setBounds(92, 90, 156, 17);
		lblNewLabel_5.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_5);		
		JLabel lblNewLabel_3 = new JLabel("EID   :");
		lblNewLabel_3.setBounds(92, 120, 156, 17);
		lblNewLabel_3.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_3);
		
		final JLabel textGender = new JLabel("GENDER    :");
		textGender.setBounds(91, 150, 156, 17);
		textGender.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(textGender);
		
		JLabel lblNewLabel_2 = new JLabel("SALARY    :");
		lblNewLabel_2.setBounds(92, 180, 156, 17);
		lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("DESIGNATION   :");
		lblNewLabel_6.setBounds(92, 210, 156, 17);
		lblNewLabel_6.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("MOBILE NO   :");
		lblNewLabel_7.setBounds(92, 240, 156, 17);
		lblNewLabel_7.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("PAN NO  :");
		lblNewLabel_9.setBounds(92, 270, 156, 17);
		lblNewLabel_9.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_1 = new JLabel("AADHAR NO   :");
		lblNewLabel_1.setBounds(92, 328, 150, 14);
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("ADDRESS   :");
		lblNewLabel_8.setBounds(92, 401, 156, 17);
		lblNewLabel_8.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_8);
		
		
		JLabel lblNewLabel_8_1 = new JLabel("DOJ   :");
		lblNewLabel_8_1.setBounds(92, 439, 156, 14);
		lblNewLabel_8_1.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		contentPane.add(lblNewLabel_8_1);
		
		  JLabel panStatus = new JLabel("");
		  panStatus.setBounds(527, 287, 100, 14);
	        panStatus.setForeground(Color.RED);
	        panStatus.setFont(new Font("Book Antiqua", Font.PLAIN, 10));
	        contentPane.add(panStatus);
	        
	        JLabel aadharStatus = new JLabel("");
	        aadharStatus.setBounds(527, 348, 100, 14);
	        aadharStatus.setForeground(Color.RED);
	        aadharStatus.setFont(new Font("Book Antiqua", Font.PLAIN, 10));
	        contentPane.add(aadharStatus);
		
		
		textName = new JTextField();
		textName.setBounds(242, 55, 219, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(242, 85, 219, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textEid = new JTextField();
		textEid.setBounds(242, 115, 219, 20);
		contentPane.add(textEid);
		textEid.setColumns(10);
		
		textSal = new JTextField();
		textSal.setBounds(242, 175, 219, 20);
		contentPane.add(textSal);
		textSal.setColumns(10);
		
		textDesg = new JTextField();
		textDesg.setBounds(242, 205, 219, 20);
		contentPane.add(textDesg);
		textDesg.setColumns(10);
		
		textMobNo = new JTextField();
		textMobNo.setBounds(242, 235, 219, 20);
		contentPane.add(textMobNo);
		textMobNo.setColumns(10);
		
		textpan_No = new JTextField();
		textpan_No.setBounds(242, 265, 219, 20);
		contentPane.add(textpan_No);
		textpan_No.setColumns(10);
		
		textadhar_No = new JTextField();
		textadhar_No.setBounds(242, 328, 219, 20);
		contentPane.add(textadhar_No);
		textadhar_No.setColumns(10);
		
		textAddr = new JTextField();
		textAddr.setBounds(242, 393, 219, 33);
		contentPane.add(textAddr);
		textAddr.setColumns(10);
		
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(242, 437, 219, 20);
		contentPane.add(dateChooser);
		
		final JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setBounds(242, 145, 109, 23);
		buttonGroup.add(rbMale);
		contentPane.add(rbMale);
		
		final JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setBounds(352, 145, 109, 23);
		buttonGroup.add(rbFemale);
		contentPane.add(rbFemale);
		
		
		
	
		
		JLabel lblNewLabel_10 = new JLabel("Choose");
		lblNewLabel_10.setBounds(471, 287, 80, 14);
		lblNewLabel_10.setFont(new Font("Book Antiqua", Font.PLAIN, 10));
		lblNewLabel_10.setForeground(new Color(124, 252, 0));
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Choose");
		lblNewLabel_10_1.setBounds(471, 348, 80, 14);
		lblNewLabel_10_1.setFont(new Font("Book Antiqua", Font.PLAIN, 10));
		lblNewLabel_10_1.setForeground(new Color(173, 255, 47));
		contentPane.add(lblNewLabel_10_1);
		
		
	
		// Inside the constructor, create these buttons
		btnPanView = new JButton("View PAN");
		btnPanView.setBounds(492, 302, 80, 23);
		btnPanView.setEnabled(false);
		contentPane.add(btnPanView);

		btnAadharView = new JButton("View Aadhar");
		btnAadharView.setBounds(492, 368, 80, 23);
		btnAadharView.setEnabled(false);
		contentPane.add(btnAadharView);
		
		JButton chooseSelectPanCard = new JButton("PAN Card");
		chooseSelectPanCard.setForeground(new Color(0, 0, 139));
		chooseSelectPanCard.setBackground(new Color(135, 206, 250));
		chooseSelectPanCard.setBounds(471, 264, 120, 23);
		chooseSelectPanCard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.showOpenDialog(null);
		        panDocFile = fileChooser.getSelectedFile();

		        if (panDocFile != null) {
		            panStatus.setText(panDocFile.getName()); //
		            btnPanView.setEnabled(true);//
		            
		            
		        } else {
		            panStatus.setText("");
		            btnPanView.setEnabled(false);
		        }
		    }
		});
		contentPane.add(chooseSelectPanCard);

		// ChooseSelectAdharCard button
		JButton chooseSelectAdharCard = new JButton("Aadhar Card");
		chooseSelectAdharCard.setForeground(new Color(0, 0, 139));
		chooseSelectAdharCard.setBackground(new Color(135, 206, 250));
		chooseSelectAdharCard.setBounds(471, 328, 120, 23);
		chooseSelectAdharCard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.showOpenDialog(null);
		        aadharDocFile = fileChooser.getSelectedFile();

		        if (aadharDocFile != null) {
		            aadharStatus.setText(aadharDocFile.getName());
		            btnAadharView.setEnabled(true);
		   
		           
		    	        
		        } else {
		            aadharStatus.setText("");
		            btnAadharView.setEnabled(false);
		        }
		    }
		});
		contentPane.add(chooseSelectAdharCard);
		
		
		btnPanView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (panDocFile != null) {
		            displayImageOrPdf("View PAN Document", panDocFile);
		        }
		    }
		});
		
		 btnAadharView.addActionListener(new ActionListener() {
 		    public void actionPerformed(ActionEvent e) {
 		        if (aadharDocFile != null) {
 		            displayImageOrPdf("View Aadhar Document", aadharDocFile);
 		        }
 		    }
 		});

		
	        
		
		
	//Register-----------------------------------------------------------------------	
	        final JButton btnRegisterButton = new JButton("Register");
	        btnRegisterButton.setBounds(163, 486, 89, 23);
	        btnRegisterButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
	        btnRegisterButton.setForeground(new Color(0, 0, 139));
	        btnRegisterButton.setBackground(new Color(135, 206, 250));
	        btnRegisterButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (panDocFile == null || aadharDocFile == null) {
	                    JOptionPane.showMessageDialog(btnRegisterButton, "Please select both PAN and Aadhar images.");
	                    return;
	                }

	                try {
	                    Class.forName("org.sqlite.JDBC");
	                    Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");
	                    String query = "INSERT INTO EmpRegister (Name, Email, Gender, Salary, Desg, mob_no, pan_no, adhar_no, addr, date) VALUES (?,?,?,?,?,?,?,?,?,?)";

	                    PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1, textName.getText());
	                    ps.setString(2, textEmail.getText());

	                    if (rbMale.isSelected())
	                        ps.setString(3, rbMale.getText());
	                    else
	                        ps.setString(3, rbFemale.getText());

	                    ps.setInt(4, Integer.parseInt(textSal.getText()));
	                    ps.setString(5, textDesg.getText());
	                    ps.setLong(6, Long.parseLong(textMobNo.getText()));
	                    ps.setString(7, textpan_No.getText());
	                    ps.setLong(8, Long.parseLong(textadhar_No.getText()));
	                    ps.setString(9, textAddr.getText());
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    ps.setString(10, sdf.format(dateChooser.getDate()));

	                    int registerResult = ps.executeUpdate();

	                    if (registerResult > 0) {
	                        ResultSet generatedKeys = ps.getGeneratedKeys();
	                        if (generatedKeys.next()) {
	                            int generatedEID = generatedKeys.getInt(1);

	                            // Use generatedEID when inserting into Documents table
	                            String insertDocumentsQuery = "INSERT INTO Documents (EID, PAN_DOCUMENT, ADHAR_DOCUMENT, PAN_DOCUMENT_NAME, ADHAR_DOCUMENT_NAME) VALUES (?, ?, ?, ?, ?)";
	                            PreparedStatement documentsPs = conn.prepareStatement(insertDocumentsQuery);
	                            documentsPs.setInt(1, generatedEID); // inserting into Documents table

	                            if (panDocFile != null) {
	                                byte[] panImageData = readFileToByteArray(panDocFile);
	                                documentsPs.setBytes(2, panImageData); // inserting panImageData
	                                documentsPs.setString(4, panDocFile.getName()); // inserting PAN_DOCUMENT_NAME
	                                System.out.println("Pan image added in Documents table\n");
	                            } else {
	                                documentsPs.setBytes(2, null);
	                                documentsPs.setString(4, null);
	                            }

	                            if (aadharDocFile != null) {
	                                byte[] adharImageData = readFileToByteArray(aadharDocFile);
	                                documentsPs.setBytes(3, adharImageData); // inserting adharImageData
	                                documentsPs.setString(5, aadharDocFile.getName()); // inserting ADHAR_DOCUMENT_NAME
	                                System.out.println("Aadhar Card image added in Documents table\n");
	                            } else {
	                                documentsPs.setBytes(3, null);
	                                documentsPs.setString(5, null);
	                            }

	                            documentsPs.executeUpdate();
	                        }

	                        JOptionPane.showMessageDialog(btnRegisterButton, "Record added Successfully");
	                    }
	                    ps.close();
	                    conn.close();

	                } catch (ClassNotFoundException | SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }

	            private byte[] readFileToByteArray(File file) {
	                byte[] byteArray = null;
	                try (InputStream inputStream = new FileInputStream(file)) {
	                    // Use ByteArrayOutputStream for reading the file content into bytes
	                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	                    byte[] buffer = new byte[1024];
	                    int bytesRead;
	                    while ((bytesRead = inputStream.read(buffer)) != -1) {
	                        byteStream.write(buffer, 0, bytesRead);
	                    }
	                    byteArray = byteStream.toByteArray();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                return byteArray;
	            }
	        });

	        contentPane.add(btnRegisterButton);

		//Clear-----------------------------------------------------------------------	

		JButton btnClearButton = new JButton("Clear");
		btnClearButton.setBounds(383, 486, 89, 23);
		btnClearButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnClearButton.setForeground(new Color(0, 0, 139));
		btnClearButton.setBackground(new Color(135, 206, 250));
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textEmail.setText("");
				textEid.setText("");
				textSal.setText("");
				textDesg.setText("");
				textMobNo.setText("");
				textpan_No.setText("");
				textadhar_No.setText("");
				textAddr.setText("");
		        dateChooser.setDate(null);
			//	dateChooser.cleanup();
		        // Clear status labels
		        panStatus.setText("");
		        aadharStatus.setText("");
		        
		        // Clear file paths and disable view buttons
		        panDocFile = null;
		        aadharDocFile = null;
		        btnPanView.setEnabled(false);
		        btnAadharView.setEnabled(false);
				buttonGroup.clearSelection();
			


			}
		});
		contentPane.add(btnClearButton);
		//Login-----------------------------------------------------------------------	

		final JButton btnLoginButton = new JButton("Increment");
		btnLoginButton.setBounds(493, 486, 109, 23);
		btnLoginButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnLoginButton.setForeground(new Color(0, 0, 139));
		btnLoginButton.setBackground(new Color(135, 206, 250));
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String eid = textEid.getText();
//				if(eid.equals(textEid))
//				int eid = Integer.parseInt(textEid.getText());			
				//if (eid == Integer.parseInt(textEid.getText()))				
//				JOptionPane.showMessageDialog(btnLoginButton, "Login");
				Increment innc = new Increment();
				innc.setVisible(true);		
		        dispose();

					
				
			}
		});
		contentPane.add(btnLoginButton);
		//Back-----------------------------------------------------------------------	

		JButton btnBackPage = new JButton("Back");
		btnBackPage.setBounds(45, 486, 89, 23);
		btnBackPage.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnBackPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
		        dispose();

				
			}
			
			
		});
		btnBackPage.setForeground(new Color(0, 0, 139));
		btnBackPage.setBackground(new Color(135, 206, 250));
		contentPane.add(btnBackPage);
		
		//Update-----------------------------------------------------------------------	

		JButton btnUpdateButton = new JButton("Update");
		btnUpdateButton.setBounds(276, 486, 89, 23);
		btnUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                updateEmployeeDetails();

			}

			private void updateEmployeeDetails() {
				// TODO Auto-generated method stub
				 try {
			            Class.forName("org.sqlite.JDBC");

			            Connection conn = DriverManager.getConnection(
			                    "jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

			            String query = "UPDATE EmpRegister SET NAME=?, EMAIL=?, GENDER=?, SALARY=?, DESG=?, MOB_NO=?, PAN_NO=?, ADHAR_NO=?, ADDR=?, Date=? WHERE EID=?";
			            PreparedStatement ps = conn.prepareStatement(query);
			            ps.setString(1, textName.getText());
			            ps.setString(2, textEmail.getText());
			            if (rbMale.isSelected())
			                ps.setString(3, rbMale.getText());
			            else
			                ps.setString(3, rbFemale.getText());
			            ps.setInt(4, Integer.parseInt(textSal.getText()));
			            ps.setString(5, textDesg.getText());
			            ps.setLong(6, Long.parseLong(textMobNo.getText()));
			            ps.setString(7, textpan_No.getText());
			            ps.setLong(8, Long.parseLong(textadhar_No.getText()));
			            ps.setString(9, textAddr.getText());
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			            ps.setString(10, sdf.format(dateChooser.getDate()));
			            ps.setString(11, textEid.getText());

			         // Update images
			            if (panDocFile != null || aadharDocFile != null) {
			                updateImages(conn, textEid.getText());
			            }

			            
			            int i = ps.executeUpdate();
			            JOptionPane.showMessageDialog(contentPane, i + " Record updated Successfully");

			         // Clear all fields
			            textName.setText("");
			            textEmail.setText("");
			            textEid.setText("");
			            buttonGroup.clearSelection();
			            textSal.setText("");
			            textDesg.setText("");
			            textMobNo.setText("");
			            textpan_No.setText("");
			            textadhar_No.setText("");
			            textAddr.setText("");
			            panStatus.setText("");
				        aadharStatus.setText("");
				        // Clear file paths and disable view buttons
				        panDocFile = null;
				        aadharDocFile = null;
				        btnPanView.setEnabled(false);
				        btnAadharView.setEnabled(false);
			            dateChooser.setDate(null);

			            ps.close();
			            conn.close();

			        } catch (ClassNotFoundException | SQLException e1) {
			            e1.printStackTrace();
			        }
			}

			private void updateImages(Connection conn, String eid) throws SQLException {
			    // Update images in Documents table
				   // Update images in Documents table
		        String updateImagesQuery = "UPDATE Documents SET PAN_DOCUMENT=?, ADHAR_DOCUMENT=?, PAN_DOCUMENT_NAME=?, ADHAR_DOCUMENT_NAME=? WHERE EID=?";
		        try (PreparedStatement imagesPs = conn.prepareStatement(updateImagesQuery)) {
		            if (panDocFile != null) {
		                byte[] panImageData = readFileToByteArray(panDocFile);
		                imagesPs.setBytes(1, panImageData);
		                imagesPs.setString(3, panDocFile.getName());
		            } else {
		                imagesPs.setBytes(1, null);
		                imagesPs.setString(3, null);
		            }

		            if (aadharDocFile != null) {
		                byte[] adharImageData = readFileToByteArray(aadharDocFile);
		                imagesPs.setBytes(2, adharImageData);
		                imagesPs.setString(4, aadharDocFile.getName());
		            } else {
		                imagesPs.setBytes(2, null);
		                imagesPs.setString(4, null);
		            }

		            imagesPs.setString(5, eid);
		            imagesPs.executeUpdate();
			    }
			}
		});
		btnUpdateButton.setForeground(new Color(0, 0, 139));
		btnUpdateButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnUpdateButton.setBackground(new Color(135, 206, 250));
		contentPane.add(btnUpdateButton);
		
		//Edit-----------------------------------------------------------------------	

		
		JButton btnEditButton = new JButton("Edit");
		btnEditButton.setBounds(471, 114, 120, 23);
		btnEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String eid = textEid.getText();
	                if (!eid.isEmpty()) {
	                    fetchDataForEditing(eid);
	                    
	                } else {
	                	JOptionPane.showMessageDialog(btnEditButton.getParent(), "Please enter Employee ID to edit.");
	                }
				
			}

			private void fetchDataForEditing(String eid) {
			    try {
			        Class.forName("org.sqlite.JDBC");
			        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

			        // Fetch data from EmpRegister table
			        String empQuery = "SELECT * FROM EmpRegister WHERE EID = ?";
			        PreparedStatement empPs = conn.prepareStatement(empQuery);
			        empPs.setString(1, eid);

			        ResultSet empResultSet = empPs.executeQuery();
			        if (empResultSet.next()) {
			            // Update text fields with employee details
			            textName.setText(empResultSet.getString("NAME"));
			            textEmail.setText(empResultSet.getString("EMAIL"));
			            textEid.setText(eid);
			            if (empResultSet.getString("GENDER").equals("Male")) {
			                rbMale.setSelected(true);
			            } else {
			                rbFemale.setSelected(true);
			            }
			            textSal.setText(String.valueOf(empResultSet.getInt("SALARY")));
			            textDesg.setText(empResultSet.getString("DESG"));
			            textMobNo.setText(String.valueOf(empResultSet.getLong("MOB_NO")));
			            textpan_No.setText(empResultSet.getString("PAN_NO"));
			            textadhar_No.setText(String.valueOf(empResultSet.getLong("ADHAR_NO")));
			            textAddr.setText(empResultSet.getString("ADDR"));
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			            java.util.Date date = sdf.parse(empResultSet.getString("Date"));
			            dateChooser.setDate(date);
			        } else {
			            JOptionPane.showMessageDialog(btnEditButton, "Employee not found with EID: " + eid);
			            System.out.println("Employee not found with EID: " + eid);
			        }

			        empResultSet.close();
			        empPs.close();

			        // Fetch data from Documents table
			        String docQuery = "SELECT PAN_DOCUMENT, ADHAR_DOCUMENT FROM Documents WHERE EID = ?";
			        PreparedStatement docPs = conn.prepareStatement(docQuery);
			        docPs.setString(1, eid);

			        ResultSet docResultSet = docPs.executeQuery();
			        if (docResultSet.next()) {
			            // Enable PAN and Aadhar buttons
			        	chooseSelectPanCard.setEnabled(true);
			            chooseSelectAdharCard.setEnabled(true);

			            // Set status labels with file names
			            byte[] panImageBytes = docResultSet.getBytes("PAN_DOCUMENT");
			            byte[] adharImageBytes = docResultSet.getBytes("ADHAR_DOCUMENT");

			            if (panImageBytes != null) {
			                panStatus.setText("PAN Image Loaded");
			                btnPanView.setEnabled(true);
			            } else {
			                panStatus.setText("PAN Image Not Found");
			                btnPanView.setEnabled(false);
			            }

			            if (adharImageBytes != null) {
			                aadharStatus.setText("Aadhar Image Loaded");
			                btnAadharView.setEnabled(true);
			            } else {
			                aadharStatus.setText("Aadhar Image Not Found");
			                btnAadharView.setEnabled(false);
			            }


			        } else {
			            // Disable PAN and Aadhar buttons
			            chooseSelectPanCard.setEnabled(false);//
			            chooseSelectAdharCard.setEnabled(false);

			            panStatus.setText("");
			            aadharStatus.setText("");
			            btnPanView.setEnabled(false);
			            btnAadharView.setEnabled(false);//

			            JOptionPane.showMessageDialog(btnEditButton, "Documents not found for EID: " + eid);
			            System.out.println("Documents not found for EID: " + eid);
			        }

			        docResultSet.close();
			        docPs.close();
			        conn.close();

			    } catch (ClassNotFoundException | SQLException | ParseException ex) {
			        ex.printStackTrace();
			    }
			    btnPanView.setEnabled(true);
			    btnAadharView.setEnabled(true);
			    btnPanView.setVisible(true);
			    btnAadharView.setVisible(true);
			}
			
			
			
		});
	
	        
		btnPanView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Call a method to handle PAN document view
		        viewDocument("PAN_DOCUMENT");
		    }
		});

		btnAadharView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Call a method to handle Aadhar document view
		        viewDocument("ADHAR_DOCUMENT");
		    }
		});

		// Method to handle document view
		
		btnEditButton.setForeground(new Color(0, 0, 139));
		btnEditButton.setFont(new Font("Book Antiqua", Font.BOLD, 11));
		btnEditButton.setBackground(new Color(135, 206, 250));
		contentPane.add(btnEditButton);
		
		
				
	}
    protected void viewDocument(String columnName) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\eclipse-workspace\\EmployeeGenerateSalarySlip\\Shatam.db");

            String query = "SELECT " + columnName + ", " + columnName + "_NAME FROM Documents WHERE EID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, textEid.getText());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Get the document bytes and name from the result set
                byte[] documentBytes = rs.getBytes(columnName);
                String documentName = rs.getString(columnName + "_NAME");

                if (documentBytes != null) {
                    // Create a new JFrame to display the document
                    JFrame documentFrame = new JFrame("Document Viewer");
                    documentFrame.setSize(800, 600);
                    documentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Create a JLabel to display the document image
                    JLabel documentLabel = new JLabel(new ImageIcon(documentBytes));
                    JScrollPane scrollPane = new JScrollPane(documentLabel);
                    documentFrame.getContentPane().add(scrollPane);

                    // Add a label to display the document name
                    JLabel nameLabel = new JLabel("Document Name: " + documentName);
                    documentFrame.getContentPane().add(nameLabel, BorderLayout.NORTH);

                    // Make the frame visible
                    documentFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Document not found.");
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Document not found.");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

	protected void displayImageOrPdf(String title, File file) {
        try {
            JFrame documentFrame = new JFrame(title);
            documentFrame.setSize(800, 600);
            documentFrame.setResizable(true);

            String fileName = file.getName().toLowerCase();
            
            if (fileName.endsWith(".pdf")) {
                // Handle PDF files
                System.out.println(file);
                 displayPdf(documentFrame, file);
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                // Handle image files
                 displayImage(documentFrame, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
    private void displayPdf(JFrame frame, File pdfFile) {
        try {
            PDDocument document = PDDocument.load(pdfFile);
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            JPanel pdfPanel = new JPanel();
            pdfPanel.setLayout(new GridLayout(0, 1));

            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 70);
                JLabel pdfLabel = new JLabel(new ImageIcon(image));
                pdfPanel.add(pdfLabel);
            }

            JScrollPane scrollPane = new JScrollPane(pdfPanel);
            frame.getContentPane().add(scrollPane);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void displayImage(JFrame frame, File imageFile) {
//        JLabel imageLabel = new JLabel();
//        imageLabel.setIcon(new ImageIcon(imageFile.getAbsolutePath()));
//
//        JScrollPane scrollPane = new JScrollPane(imageLabel);
//        frame.getContentPane().add(scrollPane);
//        frame.setVisible(true);
//    }
    
    private void displayImage(JFrame frame, File imageFile) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);

            // Define the desired width and height for the scaled image
            int scaledWidth = 300; // Adjust this according to your preference
            int scaledHeight = -1; // Maintain aspect ratio

            // Create a scaled version of the image
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            // Create an ImageIcon from the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Create a JLabel with the scaled ImageIcon
            JLabel imageLabel = new JLabel(scaledIcon);

            // Add the JLabel to a JScrollPane and set it as the content pane of the frame
            JScrollPane scrollPane = new JScrollPane(imageLabel);
            frame.getContentPane().add(scrollPane);

            // Set frame properties and make it visible
            frame.setSize(800, 600); // Adjust the size according to your preference
            frame.setResizable(true);
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private byte[] readFileToByteArray(File file) {
        byte[] byteArray = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            // Use ByteArrayOutputStream for reading the file content into bytes
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteStream.write(buffer, 0, bytesRead);
            }
            byteArray = byteStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }
    
    
    
    
}
