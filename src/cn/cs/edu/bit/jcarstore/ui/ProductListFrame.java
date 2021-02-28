package cn.cs.edu.bit.jcarstore.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import cn.cs.edu.bit.jcarstore.dao.mysql.ProductDaoImp;
import cn.cs.edu.bit.jcarstore.dao1.ProductDao;
import cn.cs.edu.bit.jcarstore.domain.Product;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class ProductListFrame extends MyFrame {

	//��Ʒ�б�
	private List<Product> products=null;
	//��ƷDAO
	private ProductDao dao=new ProductDaoImp();
	//ѡ�����Ʒ�к�
	int selectRow=-1;
	//���ﳵ
	private Map<String , Integer>cart=new HashMap<String, Integer>();
	JTable table;
	JLabel lblImage;
	JLabel lblDescn;
	JLabel lblListprice;
	JLabel lblUnitcost;
	public ProductListFrame() {
		super("��Ʒ�б�", 1045, 700);
		// ���ֲ���Ĭ�ϲ���
		getContentPane().setLayout(new BorderLayout());

		//��ѯ������Ʒ
		products=dao.findAll();
		// ��ʼ������
		// ��Ӷ�����������
		getContentPane().add(getSearchPanel(), BorderLayout.NORTH);
		// ��ʼ���ָ����
		JSplitPane splitPane = new JSplitPane();
		// ����������
		splitPane.setLeftComponent(getLeftPane());
		// ���÷ָ���λ��
		splitPane.setDividerLocation(600);
		// �����Ҳ����
		splitPane.setRightComponent(getRightPane());
		// ��ӷָ����
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}

	// �����������
	private JPanel getSearchPanel() {

		JPanel panel=new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(30);
		
		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u5546\u54C1\u7C7B\u522B");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		String[] categories= {"����","�߲�","ˮ��","�ǹ�","��ż","��ɡ"};
		JComboBox comboBox = new JComboBox(categories);
		comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		
		panel.add(comboBox);
		
		JButton btnGo = new JButton("\u67E5\u8BE2");
		btnGo.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		//��ѯ�¼�����
		btnGo.addActionListener(e->{
			//ѡ�����
			String category=(String)comboBox.getSelectedItem();
			products=dao.findByCategory(category);
			ProductTableModel model=new ProductTableModel(this.products);
			table.setModel(model);
			
		});
		panel.add(btnGo);
		
		JButton btnReset = new JButton("\u91CD\u7F6E");
		btnReset.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		//���ô���
		btnReset.addActionListener(e->{
			products = dao.findAll();
			ProductTableModel model=new ProductTableModel(this.products);
			table.setModel(model);
		});
		panel.add(btnReset);
		return panel;
	}

	// ������
	private JScrollPane getLeftPane() {
		// TODO���ñ��

		JScrollPane Leftpane=new JScrollPane();
		Leftpane.setViewportView(geTable());
		return Leftpane;
	}

	// ��ʼ�����
	private JTable geTable() {
		
		ProductTableModel model= new ProductTableModel(this.products);

		if(table==null) {
		
		 table=new JTable(model);
		//���ñ������
		table.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		//���ñ�����
		table.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 16));
		//�����и�
		table.setRowHeight(51);
		//���ñ���е�ѡ��ģʽ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//ѡ���е��¼�����
		ListSelectionModel rowListSelectionModel=table.getSelectionModel();
		rowListSelectionModel.addListSelectionListener(e->{
			//ֻ������갴��
			if(e.getValueIsAdjusting()) {
				return;
			}
			ListSelectionModel lsm=(ListSelectionModel)e.getSource();
			selectRow=lsm.getMinSelectionIndex();
			if(selectRow<0) {
				return;
			}
			//�����Ҳ����
			Product p=products.get(selectRow);
			String petImage=String.format("/images/%s", p.getImage());
			ImageIcon icon=new ImageIcon(ProductListFrame.class.getResource(petImage));
			lblImage.setIcon(icon);
			
			String descn=p.getDescn();
			lblDescn.setText("��Ʒ����: "+descn);
			
			double listprice=p.getListprice();
			String sListprice=String.format("��Ʒ�г���: %.2f", listprice);
			lblListprice.setText(sListprice);
			
			double unicost=p.getUnitcost();
			String sunicost=String.format("��Ʒ����: %.2f", unicost);
			lblUnitcost.setText(sunicost);
			
		});
		}else {
			table.setModel(model);
		}
		
		return table;
	}
	
	// �Ҳ����
	private JPanel getRightPane() {
		JPanel rightpanel=new JPanel();
		rightpanel.setLayout(new GridLayout(2,1,0,0));
		rightpanel.setBackground(Color.WHITE);
		//ͼ��
		lblImage=new JLabel();
		lblImage.setIcon(new ImageIcon(ProductListFrame.class.getResource("/bgpicture/WindowsPic.jpg")));
		lblImage.setBackground(Color.WHITE);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		rightpanel.add(lblImage);
		
		//��ϸ����
		JPanel detailpanel=new JPanel();
		rightpanel.add(detailpanel);
		detailpanel.setLayout(new GridLayout(7,1,0,5));
		detailpanel.setBackground(new Color(230, 230, 250));
		
		JSeparator separator1 = new JSeparator();
		detailpanel.add(separator1);
		
		lblDescn= new JLabel();
		lblDescn.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		detailpanel.add(lblDescn);
		
		lblListprice = new JLabel();
		lblListprice.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		detailpanel.add(lblListprice);
		
		lblUnitcost= new JLabel();
		lblUnitcost.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		detailpanel.add(lblUnitcost);
		
		JSeparator separator2 = new JSeparator();
		detailpanel.add(separator2);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0\u5230\u8D2D\u7269\u8F66");
		//�����Ʒ�����ﳵ
		btnAdd.addActionListener(e->{
			if(selectRow<0) {
				return;
			}
			
			//�����Ʒ�����ﳵ
			Product p=products.get(selectRow);
			String productid=p.getProductid();
			if(cart.containsKey(productid)) {
				//�����Ʒ����
				Integer count=cart.get(productid);
				cart.put(productid, ++count);
			}else {
				cart.put(productid, 1);
			}
			System.out.println(cart);
		});
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		detailpanel.add(btnAdd);
		
		JButton btnCheck = new JButton("\u67E5\u770B\u8D2D\u7269\u8F66");
		//�鿴���ﳵ
		btnCheck.addActionListener(e->{
			CartFrame frame=new CartFrame(cart,this);
			frame.setVisible(true);
			this.setVisible(false);
		});
		btnCheck.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		detailpanel.add(btnCheck);
		
		return rightpanel;
		
	}
}
