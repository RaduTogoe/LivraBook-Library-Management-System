package interfata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class OrangeButton extends JButton {
    public OrangeButton(String text) {
        this.setBackground(new Color(231, 71, 60));
        this.setFont(new Font("Roboto", Font.PLAIN, 20));
        this.setFocusable(false);
        this.setText(text);
        this.setBorderPainted(false);
	    this.setBorderPainted(false);
        this.setForeground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                OrangeButton.this.setBackground(new Color(255, 102, 89)); // Change background on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                OrangeButton.this.setBackground(new Color(231, 71, 60)); // Reset background on exit
            }
        });
    }
}