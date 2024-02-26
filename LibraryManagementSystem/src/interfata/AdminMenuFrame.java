package interfata;
import elemente.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenuFrame extends JFrame{
	private RedButton butonAdaugareCarte;
	private RedButton butonEditareCarti;
	private RedButton butonEditareConturi;
	private RedButton butonDeconectare;
	
	
	public AdminMenuFrame() {
		
		String conectare = "<html> Utilizator conectat:" + "<br /> " +  Biblioteca.adminLogat.getUsername() + "</html>"; 
		JLabel conectatCa = new JLabel(conectare);
        conectatCa.setFont(new Font("Roboto", Font.PLAIN, 40));

		butonAdaugareCarte = new RedButton("Adauga carte");
		butonEditareCarti = new RedButton("Vizualizare si Editare Carti");
		butonEditareConturi = new RedButton("Vizualizare si Editare Conturi");
		butonDeconectare = new RedButton("Deconectare");

		butonAdaugareCarte.setBounds(150, 50, 500, 50);
		butonEditareCarti.setBounds(150, 150, 500, 50);
		butonEditareConturi.setBounds(150, 250, 500, 50);
		butonDeconectare.setBounds(150, 350, 500, 50);
		conectatCa.setBounds(150, 700, 500, 150);
		
		butonAdaugareCarte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAdminMenuFrame();
				new AdaugareCarteFrame();
			}
		});
		
		butonEditareCarti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAdminMenuFrame();
				new ViewBooksFrame();
			}
		});
		
		butonEditareConturi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAdminMenuFrame();
				new ViewAccountsFrame();
			}
		});
		
		butonDeconectare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAdminMenuFrame();
				Biblioteca.adminLogat = null;
				new HomeFrame();
			}
		});
		
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 204, 203));
		this.setSize(800, 900);
		this.setLayout(null);
		this.setVisible(true);
		this.add(butonAdaugareCarte);
		this.add(butonEditareCarti);
		this.add(butonEditareConturi);
		this.add(butonDeconectare);
		this.add(conectatCa);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void closeAdminMenuFrame() {
		this.dispose();
	}
	
}
