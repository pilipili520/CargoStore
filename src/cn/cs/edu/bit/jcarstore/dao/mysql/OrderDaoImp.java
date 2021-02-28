package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.cs.edu.bit.jcarstore.dao1.OrderDao;
import cn.cs.edu.bit.jcarstore.domain.Order;
import cn.cs.edu.bit.jcarstore.domain.Product;

public class OrderDaoImp implements OrderDao {

	@Override
	public List<Order> findAll() {
		String sql = "select orderid,userid,orderdate from orders";

		List<Order> orders = new ArrayList<Order>();

		try {
			// 2.�������ݿ�����
			Connection conn = DBHelper.getConnection();
			// 3.����������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4.�󶨲���
			// 5.ִ�в�ѯ
			ResultSet rs = pstmt.executeQuery();
			// 6.���������
			while (rs.next()) {
				Order order = new Order();

				order.setOrderid(rs.getLong("orderid"));
				order.setUserid(rs.getString("userid"));
				order.setOrderdate(rs.getDate("orderdate"));

				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order fingById(String orderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int creat(Order order) {

		String sql = "insert into orders (orderid,userid,orderdate,status,amount) values (?,?,?,?,?)";

		try {
			// 2.�������ݿ�����
			Connection conn = DBHelper.getConnection();
			// 3.����������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4.�󶨲���
			pstmt.setLong(1, order.getOrderid());
			pstmt.setString(2, order.getUserid());
			// util
			java.util.Date now = new java.util.Date();
			java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());
			// ���Ӿ�ȷ��Timestamp��Date
			// pstmt.setDate(3, date);
			pstmt.setTimestamp(3, date);
			pstmt.setInt(4, order.getStatus());
			pstmt.setDouble(5, order.getAmount());
			// 5.ִ�в�ѯ
			int a = pstmt.executeUpdate();
			System.out.printf("�ɹ�����%d����.\n", a);
			// 6.���������

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int modify(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
