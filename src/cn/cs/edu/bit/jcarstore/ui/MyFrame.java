package cn.cs.edu.bit.jcarstore.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

	// �����Ļ�Ŀ��
	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	// �����Ļ�ĸ߶�
	private double screenHeigth = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public MyFrame(String title, int width, int height) {
		super(title);

		// ���ô��ڴ�С
		setSize(width, height);
		// ���㴰�ھ��е�λ��
		int x = (int) (screenWidth - width) / 2;
		int y = (int) (screenHeigth - height) / 2;
		// ���ô��ڵ�λ��
		setLocation(x, y);

		// ע�ᴰ���¼�

		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// �˳�ϵͳ
				System.exit(0);
			}

		});
	}
	
}
