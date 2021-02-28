package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.cs.edu.bit.jcarstore.dao1.OrderDetailDAO;
import cn.cs.edu.bit.jcarstore.domain.OrderDetail;
import cn.cs.edu.bit.jcarstore.domain.Product;

public class OrderDetailDAOImp implements OrderDetailDAO {

	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail fingBypk(int orderid, String productid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select orderid,productid,quantity,unitcost from ordersdetail where orderid=? and productid = ?";
		

		try {
			// 2.�������ݿ�����
			 conn = DBHelper.getConnection();
			// productid,category,cname,ename,image,listprice,unitcost,descn from product"

			// 3.����������
			pstmt = conn.prepareStatement(sql);//�����Զ���Դ����
			// 4.�󶨲���
			pstmt.setInt(1, orderid);
			pstmt.setString(2, productid);
			// 5.ִ�в�ѯ
			rs = pstmt.executeQuery();//�����Զ���Դ����
			// 6.���������
			if (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();

				orderDetail.setOrderid(rs.getInt("orderid"));
				orderDetail.setProductid(rs.getString("productid"));
				orderDetail.setQuantity(rs.getInt("quantiy"));
				orderDetail.setUnitcost(rs.getDouble("unitcost"));

				return orderDetail;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public int create(OrderDetail orderDetail) {
		String sql = "insert into ordersdetail (orderid,productid,quantity,unitcost) values (?,?,?,?)";

		try {
			// 2.�������ݿ�����
			Connection conn = DBHelper.getConnection();
			// 3.����������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4.�󶨲���
			pstmt.setLong(1, orderDetail.getOrderid());
			pstmt.setString(2, orderDetail.getProductid());
			pstmt.setInt(3,orderDetail.getQuantity());
			pstmt.setDouble(4, orderDetail.getUnitcost());
			// util
			java.util.Date now = new java.util.Date();
			java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());
			
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
	public int modify(OrderDetail account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(OrderDetail account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
