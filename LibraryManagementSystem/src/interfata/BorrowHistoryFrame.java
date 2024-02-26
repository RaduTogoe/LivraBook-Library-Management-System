package interfata;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import elemente.*;

public class BorrowHistoryFrame extends JFrame {

	private JTable bookTable;
	private JButton backButton;

	public static Object[][] createObjects(ArrayList<elemente.CarteImprumutata> carti, String[] columnNames) {

		Object[][] data = new Object[carti.size()][columnNames.length];
		for (int i = 0; i < carti.size(); i++) {
			CarteImprumutata carte = carti.get(i);
			data[i] = new Object[] { carte.getTitlu(), carte.getDataImprumutului(), carte.getDataScadenta(), carte.getDataReturnarii()};
		}
		return data;
	}

	public BorrowHistoryFrame() {
		String[] columnNames = { "Carte", "Data Imprumut", "Data Scadenta", "Data Returnarii"};
		// System.out.println("Type of Biblioteca.utilizatorLogat: " +
		// Biblioteca.utilizatorLogat.getClass().getName());
		elemente.Client client = (elemente.Client) Biblioteca.utilizatorLogat;
		ArrayList<elemente.CarteImprumutata> carti = (ArrayList) (client.getCartiImprumutate());
		Object[][] data = createObjects(carti, columnNames);

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
		bookTable = new JTable(tableModel);

		backButton = new OrangeButton("Back to Menu");
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(backButton);
		JScrollPane scrollPane = new JScrollPane(bookTable);
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeBorrowHistoryFrame();
				new ClientMenuFrame();
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

	public void closeBorrowHistoryFrame() {
		this.dispose();
	}
}
