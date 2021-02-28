package cn.cs.edu.bit.jcarstore.ui;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CartTableModel extends AbstractTableModel {

	//表格数据
	private Object[][] data;
	// 表格标题类名数组
	private String[] columnNames = { "商品编号", "商品名", "商品单价", "商品数量" ,"应付金额"};
	
	
	public CartTableModel(Object[][] data) {
		this.data=data;
	}
	
	
	@Override
	public int getRowCount() {
		
		return data.length;
	}

	@Override
	public int getColumnCount() {
		
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}


	@Override
	public String getColumnName(int column) {
		
		return columnNames[column];
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//只允许数量这一列
		if(columnIndex==3) {
			return true;
		}
		else return false;

	}


	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
		if(columnIndex!=3) {
			return;
		}
		try {
			int quntity = new Integer((String)aValue);//从界面来的本质都是String
			//check用户输入,0
			if(quntity<0) {
				JOptionPane.showMessageDialog(null, "亲,请重新输入", "error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			//更新数量列
			data[rowIndex][3]=quntity;
			//计算一下应付金额
			//商品单价
			double unitcost = (double)data[rowIndex][2];
			double totaPrice = unitcost*quntity;
			////更新商品应付金额列
			data[rowIndex][4]=totaPrice;
		} catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "亲,请重新输入", "error", JOptionPane.WARNING_MESSAGE);
		}
	
	}

	
}
