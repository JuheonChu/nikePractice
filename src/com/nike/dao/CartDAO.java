package com.nike.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nike.dto.*;


import com.nike.dto.*;

public class CartDAO{
	private static final CartDAO instance = new CartDAO();
	private CartDAO() { }
	public static CartDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/nike?useSSL=false&useUnicode=true&characterEncoding=UTF-8"
						+ "&serverTimezone=UTC", "root", "tiger");
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
	
	public void insertcart(int member_id, int qty, String size, ProductDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into nike.cart(pnum , member_id,qty,price,size,name,img) values(?,?,?,?,?,?,?)";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPnum());
			pstmt.setInt(2, member_id);
			pstmt.setInt(3, qty);
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, size);
			pstmt.setString(6, dto.getName());
			pstmt.setString(7,dto.getImg());
			
			pstmt.executeUpdate();
			closeConnection(conn, pstmt, null);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pluscart(int member_id, int pnum, int qty) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			
			String sql = "update nike.cart set qty=qty+? where member_id=? and pnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qty);
			pstmt.setInt(2, member_id);
			pstmt.setInt(3, pnum);
			pstmt.executeUpdate();
			closeConnection(conn, pstmt, null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatecart(int member_id, int pnum, int qty) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			
			String sql = "update nike.cart set qty=? where member_id=? and pnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qty);
			pstmt.setInt(2, member_id);
			pstmt.setInt(3, pnum);
			pstmt.executeUpdate();
			closeConnection(conn, pstmt, null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean searchcart(int member_id, int pnum) {
		boolean search = false;
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select count(*) from nike.cart where member_id=? and pnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, pnum);
			System.out.println("searchcart:"+pstmt);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int n = rs.getInt(1);
				if(n>0) {	
					search = true;
				} else search = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	public ArrayList<CartDTO> getcartListFromMemberId(int member_id) throws Exception {
		ArrayList<CartDTO> listResult = new ArrayList<CartDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM nike.cart WHERE member_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  member_id);
		//System.out.println("getcartListFromMemberId:"+pstmt);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			CartDTO bean = new CartDTO();
			bean.setCart_id(rs.getInt("cart_id"));
			bean.setMember_id(rs.getInt("member_id"));
			bean.setPnum(rs.getInt("pnum"));
			bean.setQty(rs.getInt("qty"));
			bean.setImg(rs.getString("img"));
			bean.setCart_id(rs.getInt("cart_id"));
			bean.setName(rs.getString("name"));
			bean.setPrice(rs.getInt("price"));
			bean.setSize(rs.getString("size"));
			listResult.add(bean);
		}
		return listResult;
	}
	
	public void deletecart(int cartnum) {
		try {
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			
			String sql = "delete from nike.cart where cart_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartnum);
			pstmt.executeUpdate();
			
			closeConnection(conn, pstmt, null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int totalRowCount = 0;

		String sql = "SELECT count(*) from nike.cart";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRowCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalRowCount;

	}
	
	
	public ArrayList<CartDTO> selectCartListPerPage(int offset,int mid) throws Exception {
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = getConnection();
		String sql = "SELECT * FROM nike.cart WHERE member_id = ? order by cart_id desc LIMIT 10 OFFSET ?";
		
		//"SELECT * FROM nike.bulletinboard ORDER BY boardNumber DESC LIMIT 10 Offset ?";(실패하면 이거씁시당)

		
		// 최신순서대로할예정
		//System.out.println("query문: " + sql);

		// offset = sizeOfPage * (pageNum - 1)
		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, mid);
			pstmt.setInt(2, offset);
			// pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartDTO board = new CartDTO();
				board.setCart_id(rs.getInt("cart_id"));
				board.setImg(rs.getString("img"));
				board.setMember_id(rs.getInt("member_id"));
				board.setName(rs.getString("name"));
				board.setPnum(rs.getInt("pnum"));
				board.setPrice(rs.getInt("price"));
				board.setQty(rs.getInt("qty"));
				board.setSize(rs.getString("size"));
				
				list.add(board);
				// System.out.println(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(connection, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list);// 아무것도 안담김.
		return list;
	}
	public String getCart(String userid) {
		String cart = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql = "SELECT cart from nike.member WHERE id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cart = rs.getString("cart");
			}
			
			return cart;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public String updateCart(String userid, int pnum, int qty) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String cart = "";
		
		String sql = "UPDATE nike.member SET cart = ? WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum+"_"+qty+"/");
			pstmt.setString(2, userid);
			
			pstmt.executeUpdate();
			
			closeConnection(conn, pstmt, null);
			
			conn= getConnection();
			String sql2 = "SELECT cart from nike.member where id=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, userid);
			if(rs.next()) {
				cart = rs.getString("cart");
			}
			return cart;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
//	public void totalPrice(int member_id) {
//		try {
//			Connection conn = getConnection();
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			
//			String sql = "";
//			
//			closeConnection(conn, pstmt, rs);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	}
}












