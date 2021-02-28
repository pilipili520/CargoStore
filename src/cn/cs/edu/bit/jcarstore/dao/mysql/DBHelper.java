package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor;

public class DBHelper {

	//加载驱动
	//数据连接url
	static String url;
	//创建Pro对象
	static Properties info=new Properties();
	//静态代码块
	static {
		//读取属性文件的输入流
		InputStream in=DBHelper.class.getClassLoader().getResourceAsStream("cn/cs/edu/bit/jcarstore/dao/mysql/config.properties");
		try {
			//加载
			
			info.load(in);
			//从属性文件中读取url
			//System.out.println("ok");
			url=info.getProperty("url");
			//从属性文件中读取driver
			String driveClassName=info.getProperty("driver");
			
			Class.forName(driveClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动程序加载失败");
		}catch (IOException e) {
			System.out.println("驱动程序加载失败");
		}
	}
	public static Connection getConnection() throws SQLException {
		//创建数据库连接
		Connection conn=DriverManager.getConnection(url,info);
		return conn;
		
	}

}
