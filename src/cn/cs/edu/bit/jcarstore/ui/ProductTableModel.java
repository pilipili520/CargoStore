package cn.cs.edu.bit.jcarstore.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.cs.edu.bit.jcarstore.domain.Product;

public class ProductTableModel extends AbstractTableModel {

	// 表格标题类名数组
	private String[] columnNames = { "商品编号", "商品类别", "商品中文名", "商品英文名" };
	private List<Product> data = null;

	public ProductTableModel(List<Product> data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		// 每一行就是一个商品
		Product p = data.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return p.getProductid();
		case 1:
			return p.getCategory();
		case 2:
			return p.getCname();

		default:
			return p.getEname();
		}

	}

	@Override
	public String getColumnName(int column) {

		return columnNames[column];
	}

}
