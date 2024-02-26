package interfata;
import java.awt.*;
import elemente.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import elemente.Biblioteca;
public class LoginFrame extends JFrame{
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private OrangeButton loginButton;
	private OrangeButton backButton;
	public LoginFrame(){
		ImageIcon image = new ImageIcon(getClass().getResource("homebg.png"));
		JLabel logo = new JLabel();
		loginButton = new OrangeButton("Logare");
		backButton = new OrangeButton("Inapoi");
		
		usernameField = new JTextField(20);
		usernameLabel = new JLabel("Username");
		passwordField = new JPasswordField(20);
		passwordLabel = new JLabel("Password");

		usernameField.setBounds(200, 500, 500, 50);
		usernameLabel.setBounds(100, 500, 100, 50);
		passwordField.setBounds(200, 600, 500, 50);
		passwordLabel.setBounds(100, 600, 100, 50);
		
		loginButton.setBounds(50, 700, 300, 100);
		backButton.setBounds(450, 700, 300, 100);
		
		usernameField.setFont(new Font("Roboto", Font.PLAIN, 20));
		usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 20));
		passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		

		backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                new HomeFrame();
              
                closeLoginFrame();
            }
        });	
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
				for (int i = 0; i < Biblioteca.clienti.size(); i++) {
					Utilizator utilizator = Biblioteca.clienti.get(i);
					if (utilizator.getUsername().equals(usernameField.getText()) && utilizator.getParola().equals(password)) {
							Biblioteca.utilizatorLogat = (Client)(utilizator);
							new ClientMenuFrame();
							closeLoginFrame();
							break;
					}
				}
				
				for (int i = 0; i < Biblioteca.admini.size(); i++) {
					Utilizator utilizator = Biblioteca.admini.get(i);
					if (utilizator.getUsername().equals(usernameField.getText()) && utilizator.getParola().equals(password)) {
							Biblioteca.adminLogat = (Admin)(utilizator);
							new AdminMenuFrame();
							closeLoginFrame();
							break;
					}
				}
			}
		});
		logo.setIcon(image);
		logo.setBounds(-100, -200, 1000, 700);
		this.setIconImage(image.getImage());
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 234, 182));
		this.setSize(800, 900);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(logo);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(loginButton);
		this.add(backButton);
		this.setVisible(true);
		
	}
	public void closeLoginFrame()
	{
		this.dispose();
	}
}
