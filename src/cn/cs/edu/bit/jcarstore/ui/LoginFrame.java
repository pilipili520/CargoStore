package cn.cs.edu.bit.jcarstore.ui;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.SwingConstants;

import cn.cs.edu.bit.jcarstore.dao.mysql.AccountDaolmp;
import cn.cs.edu.bit.jcarstore.dao1.AccountDao;
import cn.cs.edu.bit.jcarstore.domain.Account;

import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class LoginFrame extends MyFrame {
	private JTextField txtAccountId;
	private JPasswordField txtPassword;

	private AccountDao dao=null;
	public LoginFrame() {
		super("用户登录", 608, 544);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/bgpicture/bg.jpg")));
		// 设置布局管理为绝对布局
		getContentPane().setLayout(null);

		getContentPane().setBackground(Color.PINK);
		JLabel label1 = new JLabel();
		label1.setForeground(Color.PINK);
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setBounds(142, 191, 83, 30);
		getContentPane().add(label1);
		label1.setText("账号：");
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		txtAccountId = new JTextField(10);
		txtAccountId.setText("Jinxuliang");
		txtAccountId.setBounds(239, 191, 157, 30);
		txtAccountId.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		getContentPane().add(txtAccountId);

		JLabel label2 = new JLabel();
		label2.setForeground(Color.PINK);
		label2.setText("密码：");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setBounds(142, 234, 83, 30);
		getContentPane().add(label2);

		txtPassword = new JPasswordField(10);
		txtPassword.setText("521");
		txtPassword.setBounds(240, 235, 157, 30);
		getContentPane().add(txtPassword);

		JButton btnOk = new JButton();
		btnOk.setBackground(Color.WHITE);
		btnOk.setForeground(Color.PINK);
		btnOk.addActionListener(e->{
			 dao=new AccountDaolmp();
			Account account=dao.findById(txtAccountId.getText());
			String passText=new String(txtPassword.getPassword());//char[]
			if(account!=null&&passText.equals(account.getPassword())) {
				//成功登陆
				System.out.println("登陆成功。");
				//界面跳转，到商品列表窗口
				ProductListFrame listFrame=new ProductListFrame();
				listFrame.setVisible(true);
				this.setVisible(false);
				MainApp.account=account;
			}else {//登陆失败
				System.out.println("登陆失败。");
				JLabel label=new JLabel("您输入的账号或密码有误，请重新输入。");
				JOptionPane.showMessageDialog(null, label, "登录失败",JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnOk.setText("确定");
		btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOk.setBounds(136, 401, 100, 30);
		getContentPane().add(btnOk);

		JButton btnCancel = new JButton();
		btnCancel.setForeground(Color.PINK);
		btnCancel.setText("取消");
		btnCancel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnCancel.setBounds(327, 401, 100, 30);
		getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Welcome to CargoStore");
		lblNewLabel.setForeground(new Color(255, 255, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("华文新魏", Font.ITALIC, 31));
		lblNewLabel.setBounds(96, 51, 341, 47);
		getContentPane().add(lblNewLabel);
		
		JLabel bg = new JLabel("New label");
		bg.setIcon(new ImageIcon(LoginFrame.class.getResource("/bgpicture/night.jpg")));
		bg.setBounds(0, 0, 590, 497);
		getContentPane().add(bg);


		

		// 注册btnCancel的ActionEvent事件监听器
		btnCancel.addActionListener(e -> {
			// 退出系统
			System.exit(0);
		});
	}
}
