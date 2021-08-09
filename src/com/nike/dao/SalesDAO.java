package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nike.dto.OrderDTO;
import com.nike.dto.SalesDTO;

public class SalesDAO {
	private static final SalesDAO instance = new SalesDAO(); //Singleton 방식

	private SalesDAO() {
		
	}
	
	public static SalesDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Connection conn =  null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/nike?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true" + "&serverTimezone=UTC", "root", "tiger");
		return conn;
	}
	
	private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
		if(rs!=null) {
			rs.close();
		}
		if(pstmt!= null) {
			pstmt.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	
	public ArrayList<SalesDTO> periodSales(String start, String end) {
		ArrayList<SalesDTO> olist = new ArrayList<SalesDTO>();
		SalesDTO order = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT pnum, name, sum(total) as total, sum(qty) as qty from nike.order where DATE(date) BETWEEN ? AND ? group by pnum order by total desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order = new SalesDTO();
				order.setPnum(rs.getInt(1));
				order.setName(rs.getString(2));
				order.setTotal(rs.getInt(3));
				order.setQty(rs.getInt(4));
				olist.add(order);
			}
			closeConnection(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return olist;
	}
	
	public ArrayList<SalesDTO> monthSales(int year){
		ArrayList<SalesDTO> olist = new ArrayList<SalesDTO>();
		SalesDTO order = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT month(`date`) as `monthdate`, sum(total) as `total` from nike.order where DATE(date) BETWEEN ? AND ? group by `monthdate` order by `monthdate` asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year+"-01-01");
			pstmt.setString(2, year+"-12-31");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order = new SalesDTO();
				order.setDate(rs.getString(1));
				order.setTotal(rs.getInt(2));
				olist.add(order);
			}
			closeConnection(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return olist;
	}
}
