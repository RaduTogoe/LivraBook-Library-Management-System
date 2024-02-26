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

public class ViewAccountsFrame extends JFrame {
	private JTable accountTable;
	private JButton editButton;
	private JButton backButton;
	private JTextField searchField;

	public Object[][] conturiToObject(String[] columnNames) {
		int k = 0;
		Object[][] data = new Object[Biblioteca.clienti.size() + Biblioteca.admini.size()][columnNames.length];
		for (int i = 0; i < Biblioteca.clienti.size(); i++) {
			Client client = Biblioteca.clienti.get(i);
			data[k++] = new Object[] { client.getUsername(), client.getParola(), client.getNume(),
									 client.getPrenume(), client.getDataNasterii(), client.getEmail(),
									 client.getNumarTelefon(), client.getAdresa(), client.getIsAdmin(), client.getTaxaPlata()};
		}
		
		for (int i = 0; i < Biblioteca.admini.size(); i++) {
			Admin admin = Biblioteca.admini.get(i);
			data[k++] = new Object[] { admin.getUsername(), admin.getParola(), admin.getNume(),
					admin.getPrenume(), admin.getDataNasterii(), admin.getEmail(),
					admin.getNumarTelefon(), admin.getAdresa(), admin.getIsAdmin(), 0};
		}
		return data;
	}

	public Client objectToClient(Object[] object) {
		return new Client((String) object[0], (String) object[1], (String) object[2], (String) object[3],
				(String) object[4], (String) object[5], (String) object[6], (String) object[7] , (int) object[9]);
	}

	public ViewAccountsFrame() {
		String[] columnNames = { "Username", "Parola", "Nume", "Prenume", "Data Nasterii", 
								  "Email", "Numar Telefon", "Adresa", "Este Admin", "Total taxe" };
		
		Object[][] data = conturiToObject(columnNames);

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

		accountTable = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(accountTable);

		editButton = new RedButton("Edit Account");
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
				int selectedRow = accountTable.getSelectedRow();
				if (selectedRow != -1) {
					Object[] rowData = new Object[columnNames.length];
					for (int i = 0; i < columnNames.length; i++) {
						rowData[i] = accountTable.getValueAt(selectedRow, i);
					}
					new AdminEditAccountFrame((objectToClient(rowData)));
					closeViewAccountsFrame();
				} else {
					JOptionPane.showMessageDialog(ViewAccountsFrame.this, "Please select an account to borrow.");
				}
			}
		});

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminMenuFrame();
				closeViewAccountsFrame();
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
		DefaultTableModel tableModel = (DefaultTableModel) accountTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		accountTable.setRowSorter(sorter);

		RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchText);
		sorter.setRowFilter(rowFilter);
	}

	private void closeViewAccountsFrame() {
		this.dispose();
	}

}