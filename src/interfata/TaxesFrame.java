package interfata;
import elemente.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TaxesFrame extends JFrame{
	
	private JSpinner campPlata;
	private OrangeButton payButton;
	private OrangeButton backButton;
	public TaxesFrame() {
		
		JLabel sumaPlataLabel = new JLabel("Total plata: " + Biblioteca.utilizatorLogat.getTaxaPlata() + " lei");
		// SpinnerNumberModel modelSpinner = new SpinnerNumberModel(0, 0, Biblioteca.utilizatorLogat.getTaxaPlata(), 1);
		
		payButton = new OrangeButton("Plateste");
		backButton = new OrangeButton("Inapoi");
		
		//campPlata = new JSpinner(modelSpinner);
		 //campPlata.setFont(new Font("Roboto", Font.PLAIN, 20));
		sumaPlataLabel.setFont(new Font("Roboto", Font.PLAIN, 50));

		sumaPlataLabel.setBounds(200, 250, 600, 50);
		payButton.setBounds(100, 450, 250, 100);
		backButton.setBounds(450, 450, 250, 100);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeTaxesFrame();
				new ClientMenuFrame();
			}
		});
		
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Biblioteca.utilizatorLogat.setTaxaPlata(0);
				Biblioteca.clienti.remove(Biblioteca.utilizatorLogat);
				Biblioteca.clienti.add(Biblioteca.utilizatorLogat);
				closeTaxesFrame();
				new TaxesFrame();
				
			}
		});
		
		
		this.add(sumaPlataLabel);
		this.add(payButton);
		this.add(backButton);
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 234, 182));
		this.setSize(800, 900);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public  void closeTaxesFrame() {
		this.dispose();
		
	}
}
