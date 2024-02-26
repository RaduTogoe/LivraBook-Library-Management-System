package interfata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import elemente.*;

public class ViewBooksFrame extends JFrame {
	private JTable bookTable;
	private JButton editButton;
	private JButton backButton;
	private JTextField searchField;

	public Object[][] cartiToObject(String[] columnNames) {
		Object[][] data = new Object[Biblioteca.carti.size()][columnNames.length];
		for (int i = 0; i < Biblioteca.carti.size(); i++) {
			Carte carte = Biblioteca.carti.get(i);
			data[i] = new Object[] { carte.getTitlu(), carte.getAutor(), carte.getEditura(), carte.getCategorie(),
					carte.getISBN(), carte.getNrExemplareDisponibile(), carte.getNrExemplareTotale(),
					carte.getAnulAparitiei() };
		}
		return data;
	}

	public Carte objectToCarte(Object[] object) {
		return new Carte((String) object[0], (String) object[1], (String) object[2], (String) object[3],
				(String) object[4], (int) object[5], (int) object[6], (int) object[7]);
	}

	public ViewBooksFrame() {
		String[] columnNames = { "Titlu", "Autor", "Editura", "Categorie", "ISBN", "Nr. copii valabile",
				"Nr. Copii Totale", "Anul Aparitiei" };
		Object[][] data = cartiToObject(columnNames);

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

		bookTable = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(bookTable);

		editButton = new RedButton("Edit Book");
		backButton = new RedButton("Back to Menu");

		searchField = new JTextField(20);

		searchField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

				filterTable(searchField.getText().toLowerCase());
			}
		});

		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(searchField);
		controlPanel.add(backButton);
		controlPanel.add(editButton);

		setLayout(new BorderLayout());

		add(controlPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bookTable.getSelectedRow();
				if (selectedRow != -1) {
					Object[] rowData = new Object[columnNames.length];
					for (int i = 0; i < columnNames.length; i++) {
						rowData[i] = bookTable.getValueAt(selectedRow, i);
					}
					new EditBookFrame(objectToCarte(rowData));
				} else {
					JOptionPane.showMessageDialog(ViewBooksFrame.this, "Please select a book to borrow.");
				}
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminMenuFrame();
				closeViewBooksFrame();
			}
		});

		scrollPane.getViewport().setBackground(new Color(255, 204, 203));
		controlPanel.setBackground(new Color(255, 204, 203));
		setSize(800, 900);
		setLocation(100, 100);
		getContentPane().setBackground(new Color(255, 234, 182));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void filterTable(String searchText) {
		DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		bookTable.setRowSorter(sorter);

		RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchText);
		sorter.setRowFilter(rowFilter);
	}

	private void closeViewBooksFrame() {
		this.dispose();
	}

}