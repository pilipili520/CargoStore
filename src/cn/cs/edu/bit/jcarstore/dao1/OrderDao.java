package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Order;

public interface OrderDao {

	//��ѯ���õĶ�������
			List<Order> findAll();
			//����������ѯ
			Order fingById(String orderid);
			//����������Ϣ
			int creat(Order  order);
			//�޸Ķ�����Ϣ
			int modify(Order  order);
			//ɾ��������Ϣ
			int remove(Order  order);
}
