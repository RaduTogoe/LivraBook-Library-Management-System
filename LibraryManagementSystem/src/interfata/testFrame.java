package interfata;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testFrame extends JFrame {

    private JTable table;
    private JButton[] operationButtons;

    public testFrame() {
        String[] columnNames = {"Column 1", "Column 2", "Operation"};

        // Sample data
        Object[][] data = {
                {"Data 1", "Data 2", "Operation 1"},
                {"Data 3", "Data 4", "Operation 2"},
                // Add more rows as needed
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        table = new JTable(model);

        // Add an extra column for the operation buttons
        operationButtons = new JButton[model.getRowCount()];
        for (int i = 0; i < model.getRowCount(); i++) {
            JButton button = new JButton("Button " + i);
            operationButtons[i] = button;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the row index of the clicked button
                    int rowIndex = Integer.parseInt(e.getActionCommand());

                    // Access data from the specific row
                    String column1Data = (String) model.getValueAt(rowIndex, 0);
                    String column2Data = (String) model.getValueAt(rowIndex, 1);
                    String operationData = (String) model.getValueAt(rowIndex, 2);

                    // Perform operations with the data from the specific row
                    System.out.println("Button clicked for row " + rowIndex);
                    System.out.println("Column 1 data: " + column1Data);
                    System.out.println("Column 2 data: " + column2Data);
                    System.out.println("Operation data: " + operationData);
                }
            });

            // Add the button to the table
            table.setValueAt(button, i, columnNames.length - 1);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new testFrame();
        });
    }
}