package cn.cs.edu.bit.jcarstore.ui;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import cn.cs.edu.bit.jcarstore.dao.mysql.OrderDaoImp;
import cn.cs.edu.bit.jcarstore.dao.mysql.OrderDetailDAOImp;
import cn.cs.edu.bit.jcarstore.dao.mysql.ProductDaoImp;
import cn.cs.edu.bit.jcarstore.dao1.OrderDao;
import cn.cs.edu.bit.jcarstore.dao1.OrderDetailDAO;
import cn.cs.edu.bit.jcarstore.dao1.ProductDao;
import cn.cs.edu.bit.jcarstore.domain.Order;
import cn.cs.edu.bit.jcarstore.domain.OrderDetail;
import cn.cs.edu.bit.jcarstore.domain.Product;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.xml.crypto.Data;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CartFrame extends MyFrame {

	private Map<String, Integer> cart;
	private OrderDao orderDao=new OrderDaoImp();
	//订单DAO
	private OrderDetailDAO orderDetailDao= new OrderDetailDAOImp();
	
	//商品DAO
	private ProductDao dao = new ProductDaoImp();
	// 表格数据
	private Object[][] data;
	private JTable table = null;
	// productListFrame的窗口
	ProductListFrame productListFrame;

	//ProductListFrame productListFrame方便重置按钮行动
	public CartFrame(Map<String, Integer> cart, ProductListFrame productListFrame) {
		super("购物车", 1000, 700);
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartFrame.class.getResource("/bgpicture/ShoppingCar2.png")));
		this.cart = cart;
		this.productListFrame = productListFrame;
		// 顶部的面板
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(230, 230, 250));

		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CartFrame.class.getResource("/images/Chinese cabbage.png")));
		topPanel.add(lblNewLabel);

		JButton btnReturn = new JButton("\u8FD4\u56DE\u5546\u54C1\u5217\u8868");
		//回到原来的表格
		btnReturn.addActionListener(e -> {
			//修改购物车的数量
			for(int i=0;i<data.length;i++) {
				String productid=(String)data[i][0];
				//数量
				Integer quantity=(Integer)data[i][3];
				cart.put(productid, quantity);
			}
			
			this.setVisible(false);
			productListFrame.setVisible(true);
		});
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		topPanel.add(btnReturn);

		JButton btnSubmit = new JButton("\u63D0\u4EA4\u8BA2\u5355");
		btnSubmit.addActionListener(e->{
			//生成订单
			generateOrders();
			JLabel label=new JLabel("订单已经生成，等待付款。");
			if(JOptionPane.showConfirmDialog(null, label, "信息",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
				System.out.println("未付款");
				this.setVisible(false);
				new GoodBye().setVisible(true);
				
				//System.exit(0);
			}else {
				System.out.println("付款");
				this.setVisible(false);
				new GoodBye().setVisible(true);;
			
				//System.exit(0);
			}
		});
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		topPanel.add(btnSubmit);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CartFrame.class.getResource("/images/doll rabbit.png")));
		topPanel.add(lblNewLabel_1);

		// 中部表格
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(geTable());
	}

	// 初始化表格
	private JTable geTable() {

		// 准备数据
		data = new Object[cart.size()][5];
		int idx = 0;
		// 遍历购物车
		for (String productid : cart.keySet()) {
			Product p = dao.fingById(productid);
			data[idx][0] = p.getProductid(); // 商品编号
			data[idx][1] = p.getCname(); // 商品名
			data[idx][2] = p.getUnitcost(); // 商品单价
			data[idx][3] = cart.get(productid); // 商品数量
			// 计算一下应付金额
			double amount = (double) data[idx][2] * (int) data[idx][3];
			data[idx][4] = amount; // 应付金额

			idx++;

		}
		CartTableModel model = new CartTableModel(data);
		if (table == null) {

			table = new JTable(model);
			table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			// 设置表格标题
			table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 17));
			// 设置行高
			table.setRowHeight(51);
			// 设置表格行的选择模式

		} else {
			table.setModel(model);
		}
		return table;
	}

	
	//生成订单的方法
	private void generateOrders() {
		Order order = new Order();
		order.setUserid(MainApp.account.getUserid());
		//设置付款状态 等待付款状态
		
		order.setStatus(0);
		//订单ID(用当前时间当订单ID)
		Date now=new Date();
		order.setOrderdate(now);
		order.setOrderid(now.getTime());
		
		order.setAmount(getOrderTotalAmount());
		//创建订单
		orderDao.creat(order);
		//创建订单的详细数据
		
		for(int i=0;i<data.length;++i) {
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrderid(order.getOrderid());
			orderDetail.setProductid((String)data[i][0]);
			orderDetail.setQuantity((int)data[i][3]);
			orderDetail.setUnitcost((double)data[i][2]);
			//插入订单详细数据
			orderDetailDao.create(orderDetail);
		}
		
	}
	//计算订单金额的方法
	private double getOrderTotalAmount() {
		double totalAmount = 0.0;
		for(int i=0;i<data.length;++i) {
			totalAmount+=(double)data[i][4];
		}
		return totalAmount;
	}
}
