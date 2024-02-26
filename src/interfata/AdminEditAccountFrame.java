package interfata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import elemente.*;

public class AdminEditAccountFrame extends JFrame {

	private JTextField usernameField;
	private JTextField numeField;
	private JTextField prenumeField;
	private JTextField adresaField;
	private JTextField numarTelefonField;
	private JTextField dataNasteriiField;
	private JTextField emailField;
	private JTextField taxaField;
	private JPasswordField parolaField;
	private JPasswordField confirmareParolaField;
	private OrangeButton editAccountButton;
	private OrangeButton backButton;
	Client client;
	Admin admin;

	public AdminEditAccountFrame(Object obj) {

		client = new Client();
		admin = new Admin();
		boolean isAdmin = (obj instanceof Admin);
		if (isAdmin == false)
			client = (Client) obj;
		else
			admin = (Admin) obj;

		editAccountButton = new OrangeButton("Editare");
		backButton = new OrangeButton("Inapoi");

		editAccountButton.setBounds(40, 700, 300, 100);
		backButton.setBounds(450, 700, 300, 100);

		usernameField = new JTextField(20);
		numeField = new JTextField(20);
		prenumeField = new JTextField(20);
		adresaField = new JTextField(80);
		numarTelefonField = new JTextField(20);
		emailField = new JTextField(25);
		dataNasteriiField = new JTextField(20);
		parolaField = new JPasswordField(30);
		confirmareParolaField = new JPasswordField(30);
		taxaField = new JTextField(20);

		JLabel usernameLabel = new JLabel("Username");
		JLabel numeLabel = new JLabel("Nume");
		JLabel prenumeLabel = new JLabel("Prenume");
		JLabel adresaLabel = new JLabel("Adresa");
		JLabel numarTelefonLabel = new JLabel("<html> Numar <br /> Telefon </html>");
		JLabel emailLabel = new JLabel("Email");
		JLabel dataNasteriiLabel = new JLabel("<html> Data <br /> Nasterii </html>");
		JLabel parolaLabel = new JLabel("Parola");
		JLabel confirmareParolaLabel = new JLabel("<html> Confirmare <br /> Parola </html>");
		JLabel taxaLabel = new JLabel("<html> Taxa <br /> Totala </html>");

		usernameField.setFont(new Font("Roboto", Font.PLAIN, 20));
		numeField.setFont(new Font("Roboto", Font.PLAIN, 20));
		prenumeField.setFont(new Font("Roboto", Font.PLAIN, 20));
		adresaField.setFont(new Font("Roboto", Font.PLAIN, 20));
		numarTelefonField.setFont(new Font("Roboto", Font.PLAIN, 20));
		emailField.setFont(new Font("Roboto", Font.PLAIN, 20));
		parolaField.setFont(new Font("Roboto", Font.PLAIN, 20));
		confirmareParolaField.setFont(new Font("Roboto", Font.PLAIN, 20));
		dataNasteriiField.setFont(new Font("Roboto", Font.PLAIN, 20));
		taxaField.setFont(new Font("Roboto", Font.PLAIN, 20));
		usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		numeLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		prenumeLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		adresaLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		numarTelefonLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		emailLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		parolaLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		confirmareParolaLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		dataNasteriiLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		taxaLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		if (isAdmin == false) {
			taxaField.setText(String.valueOf(client.getTaxaPlata()));
			usernameField.setText( client.getUsername());
			numeField.setText(client.getNume());
			prenumeField.setText(client.getPrenume());
			emailField.setText(client.getEmail());
			adresaField.setText(client.getAdresa());
			numarTelefonField.setText(client.getNumarTelefon());
			dataNasteriiField.setText(client.getDataNasterii());
		}

		usernameField.setBounds(200, 60, 500, 50);
		usernameLabel.setBounds(50, 60, 100, 50);
		parolaField.setBounds(200, 120, 500, 50);
		parolaLabel.setBounds(50, 120, 100, 50);
		confirmareParolaField.setBounds(200, 180, 500, 50);
		confirmareParolaLabel.setBounds(50, 180, 200, 50);
		numeField.setBounds(200, 240, 500, 50);
		numeLabel.setBounds(50, 240, 200, 50);
		prenumeField.setBounds(200, 300, 500, 50);
		prenumeLabel.setBounds(50, 300, 200, 50);
		emailField.setBounds(200, 360, 500, 50);
		emailLabel.setBounds(50, 360, 100, 50);
		numarTelefonField.setBounds(200, 420, 500, 50);
		numarTelefonLabel.setBounds(50, 420, 100, 50);
		adresaField.setBounds(200, 480, 500, 50);
		adresaLabel.setBounds(50, 480, 100, 50);
		dataNasteriiField.setBounds(200, 540, 500, 50);
		dataNasteriiLabel.setBounds(50, 540, 100, 50);
		taxaField.setBounds(200, 600, 500, 50);
		taxaLabel.setBounds(50, 600, 500, 50);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new ViewAccountsFrame();

				closeAdminEditAccountFrame();
			}
		});

		editAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordChars = parolaField.getPassword();
				String password = new String(passwordChars);
				Client c = new Client(usernameField.getText(), password, numeField.getText(), prenumeField.getText(),
						dataNasteriiField.getText(), emailField.getText(), numarTelefonField.getText(),
						adresaField.getText(), Integer.parseInt(taxaField.getText()));
				Admin a = new Admin(numeField.getText(), prenumeField.getText() ,dataNasteriiField.getText(), emailField.getText(), numarTelefonField.getText(), adresaField.getText(), usernameField.getText(), password);
				int i = 0;
				if (isAdmin == false) {
					{
						Biblioteca.clienti.remove(client);
						Biblioteca.clienti.add(c);
						Main.exportUsers(Main.conn);
					}

				} else {
					Biblioteca.admini.remove(a);
					Biblioteca.admini.add(a);
					Biblioteca.actualizareUtilizatori();
				}
				new AdminEditAccountFrame(c);
				closeAdminEditAccountFrame();
			}
		});

		ImageIcon image = new ImageIcon(getClass().getResource("homebg.png"));
		JLabel logo = new JLabel();
		logo.setIcon(image);
		logo.setBounds(-100, -200, 1000, 700);
		this.setIconImage(image.getImage());
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 234, 182));
		this.setSize(800, 900);
		this.setLayout(null);
		// this.add(logo);
		this.add(usernameLabel);
		this.add(usernameField);
		this.add(parolaLabel);
		this.add(parolaField);
		this.add(numeLabel);
		this.add(numeField);
		this.add(prenumeLabel);
		this.add(prenumeField);
		this.add(emailLabel);
		this.add(emailField);
		this.add(numarTelefonLabel);
		this.add(numarTelefonField);
		this.add(adresaLabel);
		this.add(adresaField);
		this.add(parolaLabel);
		this.add(parolaField);
		this.add(confirmareParolaLabel);
		this.add(confirmareParolaField);
		this.add(dataNasteriiLabel);
		this.add(dataNasteriiField);
		this.add(editAccountButton);
		this.add(backButton);
		if (isAdmin == false) {
			this.add(taxaLabel);
			this.add(taxaField);
		}
		this.setVisible(true);
	}

	public void closeAdminEditAccountFrame() {
		this.dispose();
	}
}
