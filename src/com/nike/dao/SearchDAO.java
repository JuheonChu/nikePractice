package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nike.dto.SearchBean;

public class SearchDAO {
	private Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/nike?characterEncoding=UTF-8" + "&serverTimezone=UTC", "root", "tiger");
		return conn;
	}

	private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public ArrayList<SearchBean> selectSearch(String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<SearchBean> sb = new ArrayList<SearchBean>(); // 상품목록들

		try {
			conn = getConnection();

			String sql = "SELECT * FROM nike.product WHERE `explain` LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				SearchBean temp = new SearchBean();
				temp.setType(rs.getString("type"));
				temp.setPnum(rs.getInt("pnum"));
				temp.setName(rs.getString("name"));
				
				temp.setPrice(rs.getInt("price"));
				temp.setExplain(rs.getString("explain"));
				temp.setDetailexplain(rs.getString("detailexplain"));
				temp.setImg(rs.getString("img"));
				temp.setGender(rs.getString("gender"));
				sb.add(temp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	
	
	public SearchBean selectProduct(String pnum) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		SearchBean sb=new SearchBean();
		
		int num=Integer.parseInt(pnum);
		
		try {
		conn=getConnection();
		
		String sql="SELECT * FROM product WHERE pnum="+num;
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		if(rs.next()) {
			sb.setPnum(num);
			sb.setType(rs.getString("type"));
			sb.setName(rs.getString("name"));
			sb.setPrice(rs.getInt("price"));
			sb.setExplain(rs.getString("explain"));
			sb.setDetailexplain(rs.getString("detailexplain"));
			sb.setImg(rs.getString("img"));
			sb.setGender(rs.getString("gender"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
}
