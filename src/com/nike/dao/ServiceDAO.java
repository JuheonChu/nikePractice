package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nike.dto.ServiceBean;

public class ServiceDAO {
	private Connection getConnection() throws Exception {
		Connection conn=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/nike?characterEncoding=UTF-8" + "&serverTimezone=UTC", "root", "tiger");
		return conn;
	}
	private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	public ServiceBean selectService(String name) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;

		ServiceBean sb=null;
		
		try {
		conn = getConnection();
		
		String sql="SELECT * FROM service WHERE name='"+name+"'";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		
		if(rs.next()) {
			sb=new ServiceBean();
			sb.setName(name);
			sb.setPath(rs.getString("path"));
			sb.setContent(rs.getString("content"));
		}
		}catch(Exception e) {
		e.printStackTrace();
		}
		return sb;
	}
}
