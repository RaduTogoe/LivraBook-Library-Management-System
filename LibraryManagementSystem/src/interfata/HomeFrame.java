package interfata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class HomeFrame extends JFrame {
	private JLabel logo;
	private OrangeButton loginButton;
	private OrangeButton registerButton;

	public HomeFrame() {

		JLabel logo = new JLabel();
		loginButton = new OrangeButton("Logare");
		registerButton = new OrangeButton("Inregistrare");

		loginButton.setBounds(250, 550, 300, 50);
		registerButton.setBounds(250, 650, 300, 50);
		loginButton.setBackground(new Color(231, 71, 60));
		registerButton.setBackground(new Color(231, 71, 60));
		loginButton.setFont(new Font("Roboto", Font.PLAIN, 20));
		registerButton.setFont(new Font("Roboto", Font.PLAIN, 20));
		loginButton.setFocusable(false);
		registerButton.setFocusable(false);
	
		
		 loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              
	                new LoginFrame();
	              
	                closeHomeFrame();
	            }
	        });
		 
		 registerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              
	                new RegisterFrame();
	              
	                closeHomeFrame();
	            }
	        });
		
		ImageIcon image = new ImageIcon(getClass().getResource("homebg.png"));
		logo.setIcon(image);
		logo.setBounds(-100, -200, 1000, 700);
		this.setIconImage(image.getImage());
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 234, 182));
		this.setSize(800, 900);
		this.setLayout(null);
		this.add(logo);
		this.add(loginButton);
		this.add(registerButton);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
	    
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}
	public void closeHomeFrame()
	{
		this.dispose();
	}
}
