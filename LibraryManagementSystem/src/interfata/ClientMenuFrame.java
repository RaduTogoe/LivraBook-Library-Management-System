package interfata;
import elemente.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenuFrame extends JFrame{
	private OrangeButton butonImprumutare;
	private OrangeButton butonReturnare;
	private OrangeButton butonTaxe;
	private OrangeButton butonEditare;
	private OrangeButton butonIstoricImprumuturi;
	private OrangeButton butonDeconectare;
	
	public ClientMenuFrame() {
		
		String conectare = "<html> Utilizator conectat:" + "<br /> " +  Biblioteca.utilizatorLogat.getUsername() + "</html>"; 
		JLabel conectatCa = new JLabel(conectare);
        conectatCa.setFont(new Font("Roboto", Font.PLAIN, 40));

		butonImprumutare = new OrangeButton("Imprumuta carte");
		butonReturnare = new OrangeButton("Returneaza carte");
		butonTaxe = new OrangeButton("Gestioneaza taxe");
		butonEditare = new OrangeButton("Editeaza informatiile contului");
		butonIstoricImprumuturi = new OrangeButton("Istoric imprumuturi");
		butonDeconectare = new OrangeButton("Deconectare");

		butonImprumutare.setBounds(150, 50, 500, 50);
		butonReturnare.setBounds(150, 150, 500, 50);
		butonTaxe.setBounds(150, 250, 500, 50);
		butonEditare.setBounds(150, 350, 500, 50);
		butonIstoricImprumuturi.setBounds(150, 450, 500, 50);
		butonDeconectare.setBounds(150, 550, 500, 50);
		conectatCa.setBounds(150, 650, 500, 150);
		
		butonImprumutare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	closeClientMenuFrame();
            	new BookTableFrame();
            }
        });	
		
		butonReturnare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeClientMenuFrame();
				new BookReturnFrame();
			}
		});
		
		butonEditare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeClientMenuFrame();
				new EditAccountFrame();
			}
		});
		
		butonTaxe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeClientMenuFrame();
				new TaxesFrame();
			}
		});
		
		butonIstoricImprumuturi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeClientMenuFrame();
				new BorrowHistoryFrame();
			}
		});
		
		butonDeconectare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeClientMenuFrame();
				Biblioteca.utilizatorLogat = null;
				new HomeFrame();
			}
		});
		
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 234, 182));
		this.setSize(800, 900);
		this.setLayout(null);
		this.setVisible(true);
		this.add(butonImprumutare);
		this.add(butonReturnare);
		this.add(butonTaxe);
		this.add(butonIstoricImprumuturi);
		this.add(butonEditare);
		this.add(butonDeconectare);
		this.add(conectatCa);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void closeClientMenuFrame() {
		this.dispose();
	}
	
}
