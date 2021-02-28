package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.cs.edu.bit.jcarstore.dao1.AccountDao;
import cn.cs.edu.bit.jcarstore.domain.Account;

public class AccountDaolmp implements AccountDao {

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById(String userid) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		
		try {
			// 2.创建数据库连接
			 conn = DBHelper.getConnection();
			String sql = "select userid,password,email,name,addr,city,country,phone from account where userid = ?";
			// 3.创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.绑定参数
			pstmt.setString(1, userid);
			// 5.执行查询
			rs = pstmt.executeQuery();
			// 6.遍历结果集
			if (rs.next()) {
				account = new Account();

				account.setUserid(rs.getString("userid"));
				account.setUsername(rs.getString("name"));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				account.setUsername(rs.getString("name"));
				account.setAddr(rs.getString("addr"));
				account.setCountry(rs.getString("country"));
				account.setPhone(rs.getString("phone"));

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

		return account;
	}

	@Override
	public int creat(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modify(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
