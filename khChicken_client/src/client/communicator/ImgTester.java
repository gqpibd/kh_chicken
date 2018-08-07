package client.communicator;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImgTester extends JFrame {
	BufferedImage im;
	public ImgTester(BufferedImage im) {
		this.im = im;
		setBounds(0, 0, 300, 300);
		JLabel label = new JLabel();
		//label.setIcon(new ImageIcon(im));
		label.setBounds(0, 0, 200, 200);
		add(label);
		setVisible(true);

		setLayout(null);
	}
}
