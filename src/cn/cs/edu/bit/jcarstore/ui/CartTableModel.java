package cn.cs.edu.bit.jcarstore.ui;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class CartTableModel extends AbstractTableModel {

	//�������
	private Object[][] data;
	// ��������������
	private String[] columnNames = { "��Ʒ���", "��Ʒ��", "��Ʒ����", "��Ʒ����" ,"Ӧ�����"};
	
	
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
		//ֻ����������һ��
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
			int quntity = new Integer((String)aValue);//�ӽ������ı��ʶ���String
			//check�û�����,0
			if(quntity<0) {
				JOptionPane.showMessageDialog(null, "��,����������", "error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			//����������
			data[rowIndex][3]=quntity;
			//����һ��Ӧ�����
			//��Ʒ����
			double unitcost = (double)data[rowIndex][2];
			double totaPrice = unitcost*quntity;
			////������ƷӦ�������
			data[rowIndex][4]=totaPrice;
		} catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "��,����������", "error", JOptionPane.WARNING_MESSAGE);
		}
	
	}

	
}
