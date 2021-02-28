package cn.cs.edu.bit.jcarstore.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.cs.edu.bit.jcarstore.domain.Product;

public class ProductTableModel extends AbstractTableModel {

	// ��������������
	private String[] columnNames = { "��Ʒ���", "��Ʒ���", "��Ʒ������", "��ƷӢ����" };
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

		// ÿһ�о���һ����Ʒ
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
