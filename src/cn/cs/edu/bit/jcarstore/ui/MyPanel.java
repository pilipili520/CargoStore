package cn.cs.edu.bit.jcarstore.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

	Image image = null;

	public void paint(Graphics g) {
		try {
			// image=ImageIO.read(new File("/bgpicture/night.jpg"));
			Image image = javax.imageio.ImageIO.read(getClass().getResource("/bgpicture/bg.jpg"));
			g.drawImage(image, 0, 0, 700, 450, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
