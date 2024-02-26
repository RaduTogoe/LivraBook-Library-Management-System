package interfata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RedButton extends JButton {
	 public RedButton(String text) {
	        this.setBackground(new Color(255, 51, 51));
	        this.setFont(new Font("Roboto", Font.PLAIN, 20));
	        this.setFocusable(false);
	        this.setText(text);
	        this.setBorderPainted(false);
		    this.setBorderPainted(false);
	        this.setForeground(Color.white);
	        this.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                RedButton.this.setBackground(new Color(255, 80, 80)); // Change background on hover
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                RedButton.this.setBackground(new Color(255, 51, 51)); // Reset background on exit
	            }
	        });
	 }
}
