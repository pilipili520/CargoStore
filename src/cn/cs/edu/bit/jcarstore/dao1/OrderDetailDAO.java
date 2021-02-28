package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.OrderDetail;

public interface OrderDetailDAO {

	//查询所用的订单详细数据
			List<OrderDetail> findAll();
			//根据主键查询
			OrderDetail fingBypk(int orderid,  String productid);
			//创建订单详细信息
			int create(OrderDetail  orderDetail);
			//修改订单详细信息
			int modify(OrderDetail  orderDetail);
			//删除订单详细信息
			int remove(OrderDetail  orderDetail);
}
