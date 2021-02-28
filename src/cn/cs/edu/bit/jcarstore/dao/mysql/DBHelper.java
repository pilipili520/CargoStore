package cn.cs.edu.bit.jcarstore.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor;

public class DBHelper {

	//��������
	//��������url
	static String url;
	//����Pro����
	static Properties info=new Properties();
	//��̬�����
	static {
		//��ȡ�����ļ���������
		InputStream in=DBHelper.class.getClassLoader().getResourceAsStream("cn/cs/edu/bit/jcarstore/dao/mysql/config.properties");
		try {
			//����
			
			info.load(in);
			//�������ļ��ж�ȡurl
			//System.out.println("ok");
			url=info.getProperty("url");
			//�������ļ��ж�ȡdriver
			String driveClassName=info.getProperty("driver");
			
			Class.forName(driveClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("�����������ʧ��");
		}catch (IOException e) {
			System.out.println("�����������ʧ��");
		}
	}
	public static Connection getConnection() throws SQLException {
		//�������ݿ�����
		Connection conn=DriverManager.getConnection(url,info);
		return conn;
		
	}

}
