package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nike.dto.OrderDTO;
import com.nike.dto.ProductDTO;

public class OrderDAO {
	private static final OrderDAO instance = new OrderDAO(); // Singleton 방식

	private OrderDAO() {

	}

	public static OrderDAO getInstance() {
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

	public void insertOrder(int pnum, int qty, int member_id,  int price, String size, String img, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "INSERT INTO `order`(pnum,qty, member_id,total,size,date,img,name) VALUES(?,?,?,?,?,now(),?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pnum);
			pstmt.setInt(2, qty);
			pstmt.setInt(3, member_id);
			pstmt.setInt(4, price*qty);
			pstmt.setString(5, size);
			pstmt.setString(6,img);
			pstmt.setString(7, name);

			pstmt.executeUpdate();

			closeConnection(conn, pstmt, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public boolean searchOrder(OrderDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM nike.order";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				OrderDTO temp = new OrderDTO();
				int order_id = rs.getInt("order_id");
				int pnum = rs.getInt("")
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}*/

	//관리자가 회원 주문목록 조회할때 쓸거임.
	public ArrayList<OrderDTO> selectList(int startRow, int limit) throws Exception {

		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();

		Connection connection = getConnection();

		String sql = "SELECT * FROM nike.order ORDER BY order_id DESC LIMIT ?, ?";

		// 위 쿼리의 LIMIT 키워드는 원하는 부분부터 필요한 개수만큼의 자료를 보여줄 수 있다.

		// 첫번째 ?는 정렬된 결과에서 시작하고자하는 위치이다. (0 부터 시작한다.)

		// 두번째 ?는 시작 위치에서 보고싶은 자료의 개수이다.

		// 다음으로 필요한 것은 두가지의 ?를 채워줄 수 있는 값을 받아와야한다.

		// DESC로 하는 이유는 가장 마지막 데이터가 제일 위에 와야한다.

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, startRow);

		stmt.setInt(2, limit);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
 
			OrderDTO order= new OrderDTO();
			order.setOrder_id(rs.getInt("order_id"));
			order.setPnum(rs.getInt("pnum"));
			order.setQty(rs.getInt("qty"));
			order.setMember_id(rs.getInt("member_id"));
			order.setTotal(rs.getInt("total"));
			order.setSize(rs.getString("size"));
			order.setDate(rs.getDate("date"));
			order.setImg(rs.getString("img"));
			order.setName(rs.getString("name"));
			
			

			list.add(order);

		}

		return list;

	}
	
	
	public ArrayList<OrderDTO> mypageList(int offset, int mid) throws Exception {

		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		String sql = "SELECT * FROM nike.order WHERE member_id = ? order by order_id desc LIMIT 10 OFFSET ?";


		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mid);
		pstmt.setInt(2, offset);

		

		rs = pstmt.executeQuery();

		while (rs.next()) {
 
			OrderDTO order= new OrderDTO();
			order.setOrder_id(rs.getInt("order_id"));
			order.setPnum(rs.getInt("pnum"));
			order.setQty(rs.getInt("qty"));
			order.setMember_id(rs.getInt("member_id"));
			order.setTotal(rs.getInt("total"));
			order.setSize(rs.getString("size"));
			order.setDate(rs.getDate("date"));
			order.setImg(rs.getString("img"));
			order.setName(rs.getString("name"));
			list.add(order);

		}
		
		closeConnection(conn, pstmt, rs);

		return list;

	}
	
	public int getTotalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int totalRowCount = 0;

		String sql = "SELECT count(*) from nike.order";

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
	
	public int getTotalProfit(int member_id) {
		int profit = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sum(`total`) FROM nike.order WHERE member_id=? group by member_id";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				profit = rs.getInt(1);
			}
			closeConnection(conn, pstmt, rs);
			return profit;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return profit;
	}
	
	
	
}
