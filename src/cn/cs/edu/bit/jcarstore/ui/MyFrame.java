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

	// 获得屏幕的宽度
	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	// 获得屏幕的高度
	private double screenHeigth = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	public MyFrame(String title, int width, int height) {
		super(title);

		// 设置窗口大小
		setSize(width, height);
		// 计算窗口居中的位置
		int x = (int) (screenWidth - width) / 2;
		int y = (int) (screenHeigth - height) / 2;
		// 设置窗口的位置
		setLocation(x, y);

		// 注册窗口事件

		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 退出系统
				System.exit(0);
			}

		});
	}
	
}
