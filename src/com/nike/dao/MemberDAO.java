package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.nike.dto.BoardDTO;
import com.nike.dto.MemberDTO;

public class MemberDAO {
	private static final MemberDAO instance = new MemberDAO(); //Singleton 방식

	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	
	private Connection getConnection() throws Exception {
		Connection conn =  null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/nike?useSSL=false&useUnicode=true&characterEncoding=UTF-8" + "&serverTimezone=UTC", "root", "tiger");
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
	
	public boolean idOverlapCheck(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		String mid = "";
		
		String sql = "SELECT *FROM nike.member WHERE id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			mid = rs.getString("id");
			
		}
		
		if(id.equals(mid)) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public void insertMember(MemberDTO memberBean) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = getConnection();
		
		String sql = "INSERT INTO member(id, pw, grade, poc, address,name) VALUES(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setString(1, memberBean.getId());
		pstmt.setString(2, memberBean.getPw());
		pstmt.setString(3, "user");
		pstmt.setString(4, memberBean.getPoc());
		pstmt.setString(5, memberBean.getAddress());
		pstmt.setString(6, memberBean.getName());
		
		pstmt.executeUpdate();
		
		closeConnection(conn, pstmt,null);
		
		
	}
	
	public MemberDTO selectMember(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//Statement st = null;
		ResultSet rs = null;
		MemberDTO memberBean = new MemberDTO();
		
		conn = getConnection();
		
		String sql = "SELECT * FROM nike.member WHERE ID = ?";		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberBean.setMember_id(rs.getInt("member_id"));
				memberBean.setId(id);
				memberBean.setPw(rs.getString("pw"));
				memberBean.setAddress(rs.getString("address"));
				memberBean.setGrade(rs.getString("grade"));
				memberBean.setPoc(rs.getString("poc"));
				memberBean.setName(rs.getString("name"));
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(conn, null, null);
		return memberBean;
	}
	
	public boolean login(String id, String pw) throws Exception {
		Connection conn = null;
		//Statement st = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDTO memberBean = new MemberDTO();
		
		boolean $login = false;
		
		conn = getConnection();
		
	//	String  alternative = "SELECT count(*) FROM database1.member WHERE ID = 'eer' AND PW = 5522";
	
	//	if(rs.next()) {
//			int count = rs.getInt("count(*)");
	//	if(count == 1){
	//	<script>yo microphone</script>
		//}
	//	}
	
		String sql = "SELECT * FROM nike.member WHERE ID =? AND PW = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				memberBean.setId(id);
				memberBean.setPw(pw);
				$login = true;
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(conn, pstmt, null);
		return $login;
	}
	
	
	
	
	public boolean signOut(String id) throws Exception { //회원탈퇴 작업을 진행합니다.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(id==null) {
			return false;
		}
		
		int mid = 0;
		
		String sql = "SELECT * FROM nike.member WHERE ID = ?";
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		if(rs.next()) {
			mid = rs.getInt("member_id");
		}
		
		closeConnection(conn, pstmt, rs);
		
		String signout = "DELETE FROM nike.member WHERE member_id=?";
		conn = getConnection();
		pstmt = conn.prepareStatement(signout);
		pstmt.setInt(1, mid);
		
		pstmt.executeUpdate();
		
		
		closeConnection(conn, pstmt, rs);
		return true;
	}
	
	
	public ArrayList<MemberDTO> allMember() {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		ArrayList<MemberDTO>mb = new ArrayList<MemberDTO>();
		
		try {
		conn=getConnection();
		
		String sql="SELECT * FROM member ORDER BY member_id desc";
		st=conn.createStatement();
		rs=st.executeQuery(sql);
		
		
	
		st=conn.createStatement();
		rs=st.executeQuery(sql);
	
		while(rs.next()) {
			MemberDTO dto =new MemberDTO();
			dto.setMember_id(rs.getInt("member_id"));
			dto.setName(rs.getString("name"));
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setGrade(rs.getString("grade"));
			dto.setPoc(rs.getString("poc"));
			dto.setAddress(rs.getString("address"));
			mb.add(dto);
		}
		closeConnection(conn,null,rs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mb;
	}
	
	
	public int getTotalMemberCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int totalRowCount = 0;

		String sql = "SELECT count(*) from nike.member";

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
	
	
	public ArrayList<MemberDTO> selectMemberListPerPage(int offset) throws Exception {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = getConnection();
		String sql = "SELECT * FROM nike.member ORDER BY member_id DESC LIMIT 10 Offset ?";
		// 최신순서대로할예정
		System.out.println("query문: " + sql);

		// offset = sizeOfPage * (pageNum - 1)
		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, offset);
			// pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO board = new MemberDTO();
				board.setMember_id(rs.getInt("member_id"));
				board.setId(rs.getString("id"));
				board.setPw(rs.getString("pw"));
				board.setGrade(rs.getString("grade"));
				board.setPoc(rs.getString("poc"));
				board.setAddress(rs.getString("address"));
				board.setName(rs.getString("name"));
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
	
	public int getMemberID(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM nike.member WHERE id=?";
		int member_id = 0;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member_id = rs.getInt("member_id");
			}
			return member_id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return -1;
		
	}
	
	
	
	public MemberDTO selectMemberToName(String name,String poc) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      //Statement st = null;
	      ResultSet rs = null;
	      MemberDTO memberBean = new MemberDTO();
	      
	      conn = getConnection();
	      
	      String sql = "SELECT * FROM nike.member WHERE name = ? AND poc =? ";      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, name);
	         pstmt.setString(2, poc);
	         //pstmt.executeUpdate();
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            memberBean.setMember_id(rs.getInt("member_id"));
	            memberBean.setId(rs.getString("id"));
	            memberBean.setPw(rs.getString("pw"));
	            memberBean.setAddress(rs.getString("address"));
	            memberBean.setGrade(rs.getString("grade"));
	            memberBean.setPoc(poc);
	            memberBean.setName(name);
	            
	         }
	      
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      closeConnection(conn, null, null);
	      return memberBean;
	   }
	
	
}
