package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.cs.edu.bit.jcarstore.dao1.ProductDao;
import cn.cs.edu.bit.jcarstore.domain.Account;
import cn.cs.edu.bit.jcarstore.domain.Product;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> findAll() {
		String sql = "select productid,category,cname,ename,image,listprice,unitcost,descn from product";

		List<Product> products = new ArrayList<Product>();

		try {
			// 2.创建数据库连接
			Connection conn = DBHelper.getConnection();
			// 3.创建语句对象
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4.绑定参数
			// 5.执行查询
			ResultSet rs = pstmt.executeQuery();
			// 6.遍历结果集
			while (rs.next()) {
				Product p = new Product();

				p.setCategory(rs.getString("category"));
				p.setProductid(rs.getString("productid"));
				p.setCname(rs.getString("cname"));
				p.setEname(rs.getString("ename"));
				p.setImage(rs.getString("image"));
				p.setListprice(rs.getDouble("listprice"));
				p.setUnitcost(rs.getDouble("unitcost"));
				p.setDescn(rs.getString("descn"));

				products.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public Product fingById(String productid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select productid,category,cname,ename,image,listprice,unitcost,descn from product where productid = ?";

		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// productid,category,cname,ename,image,listprice,unitcost,descn from product"

			// 3.创建语句对象
			pstmt = conn.prepareStatement(sql);// 可以自动资源管理
			// 4.绑定参数
			pstmt.setString(1, productid);
			// 5.执行查询
			rs = pstmt.executeQuery();// 可以自动资源管理
			// 6.遍历结果集
			if (rs.next()) {
				Product p = new Product();

				p.setCategory(rs.getString("category"));
				p.setProductid(rs.getString("productid"));
				p.setCname(rs.getString("cname"));
				p.setEname(rs.getString("ename"));
				p.setImage(rs.getString("image"));
				p.setListprice(rs.getDouble("listprice"));
				p.setUnitcost(rs.getDouble("unitcost"));
				p.setDescn(rs.getString("descn"));

				return p;
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
	public int creat(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> findByCategory(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select productid,category,cname,ename,image,listprice,unitcost,descn from product where category = ?";
		List<Product> products = new ArrayList<Product>();

		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// productid,category,cname,ename,image,listprice,unitcost,descn from product"

			// 3.创建语句对象
			pstmt = conn.prepareStatement(sql);// 可以自动资源管理
			// 4.绑定参数
			pstmt.setString(1, category);
			// 5.执行查询
			rs = pstmt.executeQuery();// 可以自动资源管理
			// 6.遍历结果集
			while (rs.next()) {
				Product p = new Product();

				p.setCategory(rs.getString("category"));
				p.setProductid(rs.getString("productid"));
				p.setCname(rs.getString("cname"));
				p.setEname(rs.getString("ename"));
				p.setImage(rs.getString("image"));
				p.setListprice(rs.getDouble("listprice"));
				p.setUnitcost(rs.getDouble("unitcost"));
				p.setDescn(rs.getString("descn"));

				products.add(p);
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

		return products;

	}

}
