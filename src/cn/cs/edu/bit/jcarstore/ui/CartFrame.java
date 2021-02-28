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
	//����DAO
	private OrderDetailDAO orderDetailDao= new OrderDetailDAOImp();
	
	//��ƷDAO
	private ProductDao dao = new ProductDaoImp();
	// �������
	private Object[][] data;
	private JTable table = null;
	// productListFrame�Ĵ���
	ProductListFrame productListFrame;

	//ProductListFrame productListFrame�������ð�ť�ж�
	public CartFrame(Map<String, Integer> cart, ProductListFrame productListFrame) {
		super("���ﳵ", 1000, 700);
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartFrame.class.getResource("/bgpicture/ShoppingCar2.png")));
		this.cart = cart;
		this.productListFrame = productListFrame;
		// ���������
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(230, 230, 250));

		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CartFrame.class.getResource("/images/Chinese cabbage.png")));
		topPanel.add(lblNewLabel);

		JButton btnReturn = new JButton("\u8FD4\u56DE\u5546\u54C1\u5217\u8868");
		//�ص�ԭ���ı��
		btnReturn.addActionListener(e -> {
			//�޸Ĺ��ﳵ������
			for(int i=0;i<data.length;i++) {
				String productid=(String)data[i][0];
				//����
				Integer quantity=(Integer)data[i][3];
				cart.put(productid, quantity);
			}
			
			this.setVisible(false);
			productListFrame.setVisible(true);
		});
		btnReturn.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		topPanel.add(btnReturn);

		JButton btnSubmit = new JButton("\u63D0\u4EA4\u8BA2\u5355");
		btnSubmit.addActionListener(e->{
			//���ɶ���
			generateOrders();
			JLabel label=new JLabel("�����Ѿ����ɣ��ȴ����");
			if(JOptionPane.showConfirmDialog(null, label, "��Ϣ",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
				System.out.println("δ����");
				this.setVisible(false);
				new GoodBye().setVisible(true);
				
				//System.exit(0);
			}else {
				System.out.println("����");
				this.setVisible(false);
				new GoodBye().setVisible(true);;
			
				//System.exit(0);
			}
		});
		btnSubmit.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		topPanel.add(btnSubmit);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(CartFrame.class.getResource("/images/doll rabbit.png")));
		topPanel.add(lblNewLabel_1);

		// �в����
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(geTable());
	}

	// ��ʼ�����
	private JTable geTable() {

		// ׼������
		data = new Object[cart.size()][5];
		int idx = 0;
		// �������ﳵ
		for (String productid : cart.keySet()) {
			Product p = dao.fingById(productid);
			data[idx][0] = p.getProductid(); // ��Ʒ���
			data[idx][1] = p.getCname(); // ��Ʒ��
			data[idx][2] = p.getUnitcost(); // ��Ʒ����
			data[idx][3] = cart.get(productid); // ��Ʒ����
			// ����һ��Ӧ�����
			double amount = (double) data[idx][2] * (int) data[idx][3];
			data[idx][4] = amount; // Ӧ�����

			idx++;

		}
		CartTableModel model = new CartTableModel(data);
		if (table == null) {

			table = new JTable(model);
			table.setFont(new Font("΢���ź�", Font.PLAIN, 16));
			// ���ñ�����
			table.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 17));
			// �����и�
			table.setRowHeight(51);
			// ���ñ���е�ѡ��ģʽ

		} else {
			table.setModel(model);
		}
		return table;
	}

	
	//���ɶ����ķ���
	private void generateOrders() {
		Order order = new Order();
		order.setUserid(MainApp.account.getUserid());
		//���ø���״̬ �ȴ�����״̬
		
		order.setStatus(0);
		//����ID(�õ�ǰʱ�䵱����ID)
		Date now=new Date();
		order.setOrderdate(now);
		order.setOrderid(now.getTime());
		
		order.setAmount(getOrderTotalAmount());
		//��������
		orderDao.creat(order);
		//������������ϸ����
		
		for(int i=0;i<data.length;++i) {
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrderid(order.getOrderid());
			orderDetail.setProductid((String)data[i][0]);
			orderDetail.setQuantity((int)data[i][3]);
			orderDetail.setUnitcost((double)data[i][2]);
			//���붩����ϸ����
			orderDetailDao.create(orderDetail);
		}
		
	}
	//���㶩�����ķ���
	private double getOrderTotalAmount() {
		double totalAmount = 0.0;
		for(int i=0;i<data.length;++i) {
			totalAmount+=(double)data[i][4];
		}
		return totalAmount;
	}
}
