package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.OrderDetail;

public interface OrderDetailDAO {

	//��ѯ���õĶ�����ϸ����
			List<OrderDetail> findAll();
			//����������ѯ
			OrderDetail fingBypk(int orderid,  String productid);
			//����������ϸ��Ϣ
			int create(OrderDetail  orderDetail);
			//�޸Ķ�����ϸ��Ϣ
			int modify(OrderDetail  orderDetail);
			//ɾ��������ϸ��Ϣ
			int remove(OrderDetail  orderDetail);
}
