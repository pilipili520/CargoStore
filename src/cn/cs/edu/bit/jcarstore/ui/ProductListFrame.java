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

	//商品列表
	private List<Product> products=null;
	//商品DAO
	private ProductDao dao=new ProductDaoImp();
	//选择的商品行号
	int selectRow=-1;
	//购物车
	private Map<String , Integer>cart=new HashMap<String, Integer>();
	JTable table;
	JLabel lblImage;
	JLabel lblDescn;
	JLabel lblListprice;
	JLabel lblUnitcost;
	public ProductListFrame() {
		super("商品列表", 1045, 700);
		// 布局采用默认布局
		getContentPane().setLayout(new BorderLayout());

		//查询所有商品
		products=dao.findAll();
		// 初始化界面
		// 添加顶部搜索界面
		getContentPane().add(getSearchPanel(), BorderLayout.NORTH);
		// 初始化分割面板
		JSplitPane splitPane = new JSplitPane();
		// 设置左侧面板
		splitPane.setLeftComponent(getLeftPane());
		// 设置分割线位置
		splitPane.setDividerLocation(600);
		// 设置右侧面板
		splitPane.setRightComponent(getRightPane());
		// 添加分割面板
		getContentPane().add(splitPane, BorderLayout.CENTER);
	}

	// 顶部搜索面板
	private JPanel getSearchPanel() {

		JPanel panel=new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(30);
		
		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u5546\u54C1\u7C7B\u522B");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		String[] categories= {"宠物","蔬菜","水果","糖果","玩偶","雨伞"};
		JComboBox comboBox = new JComboBox(categories);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		panel.add(comboBox);
		
		JButton btnGo = new JButton("\u67E5\u8BE2");
		btnGo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		//查询事件处理
		btnGo.addActionListener(e->{
			//选择类别
			String category=(String)comboBox.getSelectedItem();
			products=dao.findByCategory(category);
			ProductTableModel model=new ProductTableModel(this.products);
			table.setModel(model);
			
		});
		panel.add(btnGo);
		
		JButton btnReset = new JButton("\u91CD\u7F6E");
		btnReset.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		//重置处理
		btnReset.addActionListener(e->{
			products = dao.findAll();
			ProductTableModel model=new ProductTableModel(this.products);
			table.setModel(model);
		});
		panel.add(btnReset);
		return panel;
	}

	// 左侧面板
	private JScrollPane getLeftPane() {
		// TODO放置表格

		JScrollPane Leftpane=new JScrollPane();
		Leftpane.setViewportView(geTable());
		return Leftpane;
	}

	// 初始化表格
	private JTable geTable() {
		
		ProductTableModel model= new ProductTableModel(this.products);

		if(table==null) {
		
		 table=new JTable(model);
		//设置表格字体
		table.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		//设置表格标题
		table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));
		//设置行高
		table.setRowHeight(51);
		//设置表格行的选择模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//选择行的事件处理
		ListSelectionModel rowListSelectionModel=table.getSelectionModel();
		rowListSelectionModel.addListSelectionListener(e->{
			//只考虑鼠标按下
			if(e.getValueIsAdjusting()) {
				return;
			}
			ListSelectionModel lsm=(ListSelectionModel)e.getSource();
			selectRow=lsm.getMinSelectionIndex();
			if(selectRow<0) {
				return;
			}
			//更新右侧面板
			Product p=products.get(selectRow);
			String petImage=String.format("/images/%s", p.getImage());
			ImageIcon icon=new ImageIcon(ProductListFrame.class.getResource(petImage));
			lblImage.setIcon(icon);
			
			String descn=p.getDescn();
			lblDescn.setText("商品描述: "+descn);
			
			double listprice=p.getListprice();
			String sListprice=String.format("商品市场价: %.2f", listprice);
			lblListprice.setText(sListprice);
			
			double unicost=p.getUnitcost();
			String sunicost=String.format("商品单价: %.2f", unicost);
			lblUnitcost.setText(sunicost);
			
		});
		}else {
			table.setModel(model);
		}
		
		return table;
	}
	
	// 右侧面板
	private JPanel getRightPane() {
		JPanel rightpanel=new JPanel();
		rightpanel.setLayout(new GridLayout(2,1,0,0));
		rightpanel.setBackground(Color.WHITE);
		//图标
		lblImage=new JLabel();
		lblImage.setIcon(new ImageIcon(ProductListFrame.class.getResource("/bgpicture/WindowsPic.jpg")));
		lblImage.setBackground(Color.WHITE);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		rightpanel.add(lblImage);
		
		//详细界面
		JPanel detailpanel=new JPanel();
		rightpanel.add(detailpanel);
		detailpanel.setLayout(new GridLayout(7,1,0,5));
		detailpanel.setBackground(new Color(230, 230, 250));
		
		JSeparator separator1 = new JSeparator();
		detailpanel.add(separator1);
		
		lblDescn= new JLabel();
		lblDescn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		detailpanel.add(lblDescn);
		
		lblListprice = new JLabel();
		lblListprice.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		detailpanel.add(lblListprice);
		
		lblUnitcost= new JLabel();
		lblUnitcost.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		detailpanel.add(lblUnitcost);
		
		JSeparator separator2 = new JSeparator();
		detailpanel.add(separator2);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0\u5230\u8D2D\u7269\u8F66");
		//添加商品到购物车
		btnAdd.addActionListener(e->{
			if(selectRow<0) {
				return;
			}
			
			//添加商品到购物车
			Product p=products.get(selectRow);
			String productid=p.getProductid();
			if(cart.containsKey(productid)) {
				//获得商品数量
				Integer count=cart.get(productid);
				cart.put(productid, ++count);
			}else {
				cart.put(productid, 1);
			}
			System.out.println(cart);
		});
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		detailpanel.add(btnAdd);
		
		JButton btnCheck = new JButton("\u67E5\u770B\u8D2D\u7269\u8F66");
		//查看购物车
		btnCheck.addActionListener(e->{
			CartFrame frame=new CartFrame(cart,this);
			frame.setVisible(true);
			this.setVisible(false);
		});
		btnCheck.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		detailpanel.add(btnCheck);
		
		return rightpanel;
		
	}
}
