package cn.cs.edu.bit.jcarstore.ui;

import java.util.Timer;

import javax.swing.JOptionPane;

import cn.cs.edu.bit.jcarstore.domain.Account;

public class MainApp {

	// �û���¼�ɹ�����Ҫ�������Ϣ
	public static Account account;

	
	public static void main(String[] args) throws InterruptedException {
		Welcome login = new Welcome();

		Thread.sleep(2000);
		login.setVisible(false);
		JOptionPane.showMessageDialog(null, "��ʦ�Ѿ�����ע�����˺ţ�����������", "Welcome", JOptionPane.PLAIN_MESSAGE);
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);


	}

}
