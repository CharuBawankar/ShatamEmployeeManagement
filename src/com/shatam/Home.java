package com.shatam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 70, 1100, 600);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Management System");
		lblNewLabel.setBounds(376, 63, 400, 27);
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		JLabel lblShatamTechnologies = new JLabel("Shatam Technologies");
		lblShatamTechnologies.setBounds(370, 11, 509, 39);
		lblShatamTechnologies.setFont(new Font("Book Antiqua", Font.BOLD, 34));
		lblShatamTechnologies.setForeground(new Color(255, 255, 255));
		lblShatamTechnologies.setBackground(new Color(0, 204, 255));
		contentPane.add(lblShatamTechnologies);
		
		JLabel label = new JLabel("");
		label.setBounds(42, 101, 1100, 375);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/emp1.png"));
		label.setIcon(img);
		contentPane.add(label);
		
		JButton btnRegisterButton = new JButton("Register");
		btnRegisterButton.setBounds(132, 499, 179, 23);
		btnRegisterButton.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		btnRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register reg = new Register();
				reg.setVisible(true);
				
		        dispose();

			}
		});
		btnRegisterButton.setBackground(new Color(224, 255, 255));
		btnRegisterButton.setForeground(new Color(0, 0, 204));
		contentPane.add(btnRegisterButton);
		
		final JButton btnLogin = new JButton("Increment");
		btnLogin.setBounds(748, 499, 179, 23);
		btnLogin.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				JOptionPane.showMessageDialog(btnLogin, "Login");
				Increment login = new Increment();
				login.setVisible(true);	
				
		        dispose();

			}			
		});
		btnLogin.setBackground(new Color(224, 255, 255));
		btnLogin.setForeground(new Color(0, 0, 204));
		contentPane.add(btnLogin);
		
		JButton btnDailyAttendence = new JButton("Mark Attendence");
		btnDailyAttendence.setBounds(340, 499, 179, 23);
		btnDailyAttendence.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		btnDailyAttendence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Attendence attend = new Attendence();
				attend.setVisible(true);
		        dispose();

			}
		});
		btnDailyAttendence.setBackground(new Color(224, 255, 255));
		btnDailyAttendence.setForeground(new Color(0, 0, 204));
		contentPane.add(btnDailyAttendence);
		

		JButton btnShowAllAttendence = new JButton("Show All Attendence");
		btnShowAllAttendence.setBounds(546, 499, 179, 23);
		btnShowAllAttendence.setFont(new Font("Book Antiqua", Font.BOLD, 12));
		btnShowAllAttendence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DisplayAllAttendance attend = new DisplayAllAttendance();
				attend.setVisible(true);
		        dispose();
			}
		});
		btnShowAllAttendence.setForeground(new Color(0, 0, 204));
		btnShowAllAttendence.setBackground(new Color(224, 255, 255));
		contentPane.add(btnShowAllAttendence);
	}
}


