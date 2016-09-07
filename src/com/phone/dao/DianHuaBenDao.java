package com.phone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phone.pojo.Phone;

/*持久层
 * 对数据库操作
 * */
public class DianHuaBenDao {
	static Connection conn = null;

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver") ; 
			//获取链接
			String url = "jdbc:mysql://127.0.0.1/test"
					+ "?useUnicode=true&characterEncoding=UTF-8";
			String userName = "root";
			String password = "";
			conn = java.sql.DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//添加电话
	public boolean add(Phone phone) throws SQLException{
		PreparedStatement prepareStatement = conn.prepareStatement("insert into dianhuaben(name,phone) values(?,?)");
		prepareStatement.setString(1, phone.getName());
		prepareStatement.setString(2, phone.getPhone());
		int size = prepareStatement.executeUpdate();
		if (size == 0) {
			return false;
		}
		prepareStatement.close();
		return true;
	}

	//删除
	public boolean del(String sql) throws SQLException{
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		int size = prepareStatement.executeUpdate();
		if (size == 0) {
			return false;
		}
		prepareStatement.close();
		return true;
	}
	
	//修改
	public boolean update(String sql) throws SQLException{
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		int size = prepareStatement.executeUpdate();
		if (size == 0) {
			return false;
		}
		prepareStatement.close();
		return true;
	}
	
	//查询
	public List<Phone> query(String sql) throws SQLException{
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet result = prepareStatement.executeQuery();
		List<Phone> phones = new ArrayList<>();
		Phone temp = null;
		while(result.next()){
			temp = new Phone();
			temp.setId(result.getInt("id"));
			temp.setName(result.getString("name"));
			temp.setPhone(result.getString("phone"));
			phones.add(temp);
		}
		prepareStatement.close();
		return phones;
	}
}
