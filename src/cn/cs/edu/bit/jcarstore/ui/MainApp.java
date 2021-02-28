package cn.cs.edu.bit.jcarstore.ui;

import java.util.Timer;

import javax.swing.JOptionPane;

import cn.cs.edu.bit.jcarstore.domain.Account;

public class MainApp {

	// 用户登录成功后需要保存的信息
	public static Account account;

	
	public static void main(String[] args) throws InterruptedException {
		Welcome login = new Welcome();

		Thread.sleep(2000);
		login.setVisible(false);
		JOptionPane.showMessageDialog(null, "老师已经给您注册了账号，并且输入了", "Welcome", JOptionPane.PLAIN_MESSAGE);
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);


	}

}
