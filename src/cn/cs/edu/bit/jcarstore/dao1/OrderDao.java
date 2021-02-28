package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Order;

public interface OrderDao {

	//查询所用的订单数据
			List<Order> findAll();
			//根据主键查询
			Order fingById(String orderid);
			//创建订单信息
			int creat(Order  order);
			//修改订单信息
			int modify(Order  order);
			//删除订单信息
			int remove(Order  order);
}
