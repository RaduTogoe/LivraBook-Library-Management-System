package interfata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import elemente.*;


public class EditBookFrame extends JFrame{
	
	private JTextField titluField; 
	private JTextField autorField; 
	private JTextField edituraField; 
	private JTextField categorieField; 
	private JTextField ISBNfield; 
	private JTextField exemplareDisponibileField;
	private JTextField exemplareTotaleField;
	private JTextField anulAparitieiField;

	private RedButton editButton;
	private RedButton backButton;

	public EditBookFrame(Carte carteDeEditat) {
		
		editButton = new RedButton("Editare");
		backButton = new RedButton("Inapoi la meniu");
		
		editButton.setBounds(40, 700, 300, 100);
		backButton.setBounds(450, 700, 300, 100);
		
		titluField = new JTextField(20);
		autorField = new JTextField(20);
		edituraField = new JTextField(20);
		categorieField = new JTextField(20);
		ISBNfield = new JTextField(20);
		exemplareDisponibileField = new JTextField(25);
		exemplareTotaleField = new JTextField(20);
		anulAparitieiField = new JTextField(30);
		
		titluField.setText(carteDeEditat.getTitlu());
		autorField.setText(carteDeEditat.getAutor());
		edituraField.setText(carteDeEditat.getEditura());
		categorieField.setText(carteDeEditat.getCategorie());
		ISBNfield.setText(carteDeEditat.getISBN());
		exemplareDisponibileField.setText(String.valueOf(carteDeEditat.getNrExemplareDisponibile()));
		exemplareTotaleField.setText(String.valueOf(carteDeEditat.getNrExemplareTotale()));
		anulAparitieiField.setText(String.valueOf(carteDeEditat.getAnulAparitiei()));

		
		
		JLabel titluLabel = new JLabel("Titlu");
		JLabel autorLabel = new JLabel("Autor");
		JLabel edituraLabel = new JLabel("Editura");
		JLabel categorieLabel = new JLabel("Categorie");
		JLabel ISBNlabel = new JLabel("ISBN");
		JLabel exemplareDisponibileLabel = new JLabel("<html> Exemplare <br /> Disponibile </html>");
		JLabel exemplareTotaleLabel = new JLabel("<html> Exemplare <br /> Totale </html>");
		JLabel anulAparitieiLabel = new JLabel("<html> Anul <br /> Aparitiei </html>");
		
		titluField.setFont(new Font("Roboto", Font.PLAIN, 20));
		autorField.setFont(new Font("Roboto", Font.PLAIN, 20));
		edituraField.setFont(new Font("Roboto", Font.PLAIN, 20));
		categorieField.setFont(new Font("Roboto", Font.PLAIN, 20));
		ISBNfield.setFont(new Font("Roboto", Font.PLAIN, 20));
		exemplareDisponibileField.setFont(new Font("Roboto", Font.PLAIN, 20));
		exemplareTotaleField.setFont(new Font("Roboto", Font.PLAIN, 20));
		anulAparitieiField.setFont(new Font("Roboto", Font.PLAIN, 20));

		titluLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		autorLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		edituraLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		categorieLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		ISBNlabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		exemplareDisponibileLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		exemplareTotaleLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		anulAparitieiLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		titluField.setBounds(200, 60, 500, 50);
		titluLabel.setBounds(50, 60, 100, 50);
		edituraField.setBounds(200, 120, 500, 50);
		edituraLabel.setBounds(50, 120, 100, 50);
		ISBNfield.setBounds(200, 180, 500, 50);
		ISBNlabel.setBounds(50, 180, 200, 50);
		exemplareDisponibileField.setBounds(200, 240, 500, 50);
		exemplareDisponibileLabel.setBounds(50, 240, 200, 50);
		exemplareTotaleField.setBounds(200, 300, 500, 50);
		exemplareTotaleLabel.setBounds(50, 300, 200, 50);
		anulAparitieiField.setBounds(200, 360, 500, 50);
		anulAparitieiLabel.setBounds(50, 360, 200, 50);
		
		
		backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                new ViewBooksFrame();
              
                closeEditBookFrame();
            }
        });	
		
		editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Biblioteca.carti.remove(carteDeEditat);
            	Carte carte = new Carte(titluField.getText(), autorField.getText(), edituraField.getText(), categorieField.getText(), ISBNfield.getText(), Integer.parseInt(exemplareDisponibileField.getText()), Integer.parseInt(exemplareTotaleField.getText()), Integer.parseInt(anulAparitieiField.getText())); 
            	Biblioteca.carti.add(carte);
            	
            	Biblioteca.actualizareCarti();
            	closeEditBookFrame();
            	new EditBookFrame(carte);
            	//for (int i = 0; i < Biblioteca.carti.size(); i++)
            		//System.out.println(Biblioteca.carti.get(i));
            }
        });	
		
		ImageIcon image = new ImageIcon(getClass().getResource("homebg.png"));
		JLabel logo = new JLabel();
		logo.setIcon(image);
		logo.setBounds(-100, -200, 1000, 700);
		this.setIconImage(image.getImage());
		this.setLocation(100, 100);
		this.getContentPane().setBackground(new Color(255, 204, 203));
		this.setSize(800, 900);
		this.setLayout(null);
		//this.add(logo);
		this.add(titluLabel);
		this.add(titluField);
		this.add(autorLabel);
		this.add(autorField);
		this.add(edituraLabel);
		this.add(edituraField);
		this.add(categorieLabel);
		this.add(categorieField);
		this.add(ISBNlabel);
		this.add(ISBNfield);
		this.add(exemplareDisponibileLabel);
		this.add(exemplareDisponibileField);
		this.add(exemplareTotaleLabel);
		this.add(exemplareTotaleField);
		this.add(anulAparitieiLabel);
		this.add(anulAparitieiField);
		this.add(editButton);
		this.add(backButton);
		this.setVisible(true);
	}
	
	public void closeEditBookFrame() {
		this.dispose();
	}
}
