package cn.cs.edu.bit.jcarstore.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoodBye extends MyFrame {

	MyPanel mp = null;

	public GoodBye() {
		super("Ç×ÇëÁô²½", 1000, 700);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4EB2\uFF0C\u518D\u901B\u4E00\u4F1A\u513F\u5427");
		label.setForeground(new Color(255, 192, 203));
		label.setFont(new Font("»ªÎÄ²ÊÔÆ", Font.PLAIN, 41));
		label.setBounds(343, 122, 391, 110);
		getContentPane().add(label);
		
		JButton btQuit = new JButton("\u9000\u51FA");
		btQuit.addActionListener(e->{
			System.exit(0);
		});
		btQuit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btQuit.setForeground(new Color(255, 0, 255));
		btQuit.setBounds(464, 311, 113, 27);
		getContentPane().add(btQuit);
		
		JButton btnReturn = new JButton("\u8FD4\u56DE\u8D2D\u7269");
		btnReturn.addActionListener(e->{
			new ProductListFrame().setVisible(true);
			
			this.setVisible(false);
		});
		btnReturn.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnReturn.setBounds(464, 384, 113, 27);
		getContentPane().add(btnReturn);
		
		JLabel bg = new JLabel("");
		bg.setForeground(new Color(255, 255, 255));
		bg.setIcon(new ImageIcon(GoodBye.class.getResource("/bgpicture/night.jpg")));
		bg.setBounds(0, 0, 982, 653);
		getContentPane().add(bg);
		JPanel panel=new JPanel();
		
	}
}

