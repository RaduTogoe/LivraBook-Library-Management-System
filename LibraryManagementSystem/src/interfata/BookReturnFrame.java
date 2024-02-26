package interfata;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import elemente.*;

public class BookReturnFrame extends JFrame {

	private JTable bookTable;
	private JButton returnButton;
	private JButton backButton;

	public static Object[][] createObjects(ArrayList<elemente.CarteImprumutata> carti, String[] columnNames) {
		int nrCartiNereturnate = 0, k = 0;
		for (int i = 0; i < carti.size(); i++)
			if (carti.get(i).getEsteReturnat() == false)
				nrCartiNereturnate++;
		Object[][] data = new Object[nrCartiNereturnate][columnNames.length];
		for (int i = 0; i < carti.size(); i++) {
			CarteImprumutata carte = carti.get(i);
			if (carte.getEsteReturnat() == false)
			{ 
				data[k++] = new Object[] { carte.getTitlu(), carte.getDataImprumutului(), carte.getDataScadenta() };
			}
		}
		return data;
	}

	public BookReturnFrame() {
		String[] columnNames = { "Carte", "Data Imprumut", "Data Scadenta" };
		// System.out.println("Type of Biblioteca.utilizatorLogat: " +
		// Biblioteca.utilizatorLogat.getClass().getName());
		elemente.Client client = (elemente.Client) Biblioteca.utilizatorLogat;
		ArrayList<elemente.CarteImprumutata> carti = (ArrayList) (client.getCartiImprumutate());
		Object[][] data = createObjects(carti, columnNames);

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		bookTable = new JTable(tableModel);

		returnButton = new OrangeButton("Return Book");
		backButton = new OrangeButton("Back to Menu");
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(returnButton);
		controlPanel.add(backButton);
		JScrollPane scrollPane = new JScrollPane(bookTable);
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeReturnFrame();
				new ClientMenuFrame();
			}
		});

		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookTable.getSelectedRow();
				if (selectedRow != -1) {
					// Get the data from the selected row
					Object[] rowData = new Object[columnNames.length];
					for (int i = 0; i < columnNames.length; i++) {
						rowData[i] = bookTable.getValueAt(selectedRow, i);
					}
					int i = 0;
					ArrayList <CarteImprumutata> carti = (ArrayList)Biblioteca.utilizatorLogat.getCartiImprumutate();
					while (i < carti.size())
					{
						CarteImprumutata carte = carti.get(i);
						if (carte.getTitlu().equals(rowData[0]) && carte.getDataImprumutului().equals(rowData[1]) && carte.getDataScadenta().equals(rowData[2])) {
							carte.setEsteReturnat(true);
							carte.returneazaCarte();
							
							carti.remove(i);
							carti.add(carte);
							
							refreshReturnFrame();
							break;
						}
							
					}
					Biblioteca.actualizareImprumuturi();
				} else {
					JOptionPane.showMessageDialog(BookReturnFrame.this, "Please select a book to return.");
				}
			}
		});

		scrollPane.getViewport().setBackground(new Color(255, 234, 182));
		controlPanel.setBackground(new Color(255, 234, 182));
		//bookTable.setBackground(new Color(255, 234, 182));

		setSize(800, 900);
		setLocation(100, 100);
		getContentPane().setBackground(new Color(255, 234, 182));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void refreshReturnFrame() {
		closeReturnFrame();
		new BookReturnFrame();
	}
	public void closeReturnFrame() {
		this.dispose();
	}
}
