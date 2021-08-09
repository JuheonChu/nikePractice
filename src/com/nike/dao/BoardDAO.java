package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;
import com.nike.dto.BoardDTO;

public class BoardDAO {
	private static final BoardDAO instance = new BoardDAO(); // Singleton 방식

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		return instance;
	}

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

	public void WriteBoard(BoardDTO boardBean) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = getConnection();
		int num = 0;
		//int result = 0;
		String maxSql = "select max(boardNumber) from nike.bulletinboard";
		
		pstmt = conn.prepareStatement(maxSql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			num = rs.getInt(1)+1;
		}else {
			num = 1;
		}
		
		pstmt.close();

		

		String sql = "INSERT INTO nike.bulletinboard(boardNumber, title, content, writer, date, hitcount, ref, step, level,file, file_path) VALUES(?,?,?,?,now(),?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, boardBean.getTitle());
		pstmt.setString(3, boardBean.getContent());
		pstmt.setString(4, boardBean.getWriter());
		pstmt.setInt(5, 0);
		pstmt.setInt(6, num);
		pstmt.setInt(7,0);
		pstmt.setInt(8, 0);
		pstmt.setString(9, boardBean.getFile());
		pstmt.setString(10, boardBean.getFilePath());
		//System.out.println(pstmt);
		pstmt.executeUpdate();

		closeConnection(conn, pstmt, rs);

	}

	public ArrayList<BoardDTO> selectBoardListPerPage(int offset) throws Exception {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = getConnection();
		String sql = "select * from \r\n" + 
				"					 (select boardNumber as rnum, A.* from \r\n" + 
				"					 (select * from nike.bulletinboard order by ref desc, step)A)R\r\n" + 
				"					 limit 10 offset ?";
		
		//"SELECT * FROM nike.bulletinboard ORDER BY boardNumber DESC LIMIT 10 Offset ?";(실패하면 이거씁시당)

		
		// 최신순서대로할예정
		System.out.println("query문: " + sql);

		// offset = sizeOfPage * (pageNum - 1)
		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, offset);
			// pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setBoardNumber(rs.getInt("boardNumber"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setDate(rs.getDate("date"));
				board.setHitcount(rs.getInt("hitcount"));
				board.setContent(rs.getString("content"));
				board.setLevel(rs.getInt("level"));
				board.setRef(rs.getInt("ref"));
				board.setStep(rs.getInt("step"));
				board.setFile(rs.getString("file"));
				board.setFilePath(rs.getString("file_path"));
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
	
	
	public ArrayList<BoardDTO> getBoardList(int page, int limit) throws Exception {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = getConnection();
		
		//String sql = "SELECT * FROM nike.bulletinboard  WHERE boardNumber >=? and boardNumber<=? order by ref desc, step asc";
		//안되면 위의걸로 다시합니다
		String sql = "select * from \r\n" + 
				"					 (select boardNumber as rnum, A.* from \r\n" + 
				"					 (select * from nike.bulletinboard order by ref desc, step)A )R\r\n" + 
				"					 limit ? offset ?";
		
		int startrow = (page-1)* 10 + 1;
		int endrow = startrow+limit-1;
		
		// 최신순서대로할예정
		System.out.println("query문: " + sql);

		
		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			// pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setBoardNumber(rs.getInt("boardNumber"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setDate(rs.getDate("date"));
				board.setHitcount(rs.getInt("hitcount"));
				board.setContent(rs.getString("content"));
				board.setLevel(rs.getInt("level"));
				board.setRef(rs.getInt("ref"));
				board.setStep(rs.getInt("step"));
				board.setFile(rs.getString("file"));
				board.setFilePath(rs.getString("file_path"));
				list.add(board);
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

	public void updateHitcount(int boardNumber) {

		String sql = "UPDATE nike.bulletinboard SET hitcount = hitcount + 1 WHERE boardNumber=" + boardNumber;

		Connection conn = null;
		PreparedStatement pstmt = null;
		Resultset rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception se) {
			System.out.println("에러는? " + se);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
	}

	public int getTotalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int totalRowCount = 0;

		String sql = "SELECT count(*) from nike.bulletinboard";

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

	public BoardDTO selectBoardByKey(int boardNumber) {
		BoardDTO board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "SELECT * FROM nike.bulletinboard WHERE boardNumber=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			resultset = pstmt.executeQuery();
			
			if (resultset.next()) {
				board = new BoardDTO();
				board.setBoardNumber(boardNumber);
				board.setTitle(resultset.getString("title"));
				board.setContent(resultset.getString("content"));
				board.setDate(resultset.getDate("date"));
				board.setWriter(resultset.getString("writer"));
				board.setHitcount(resultset.getInt("hitcount"));
				board.setLevel(resultset.getInt("level"));
				board.setRef(resultset.getInt("ref"));//ref관련 여기서 고쳐야함 (if 문제발생)
				board.setStep(resultset.getInt("step"));
				board.setFile(resultset.getString("file"));
				board.setFilePath(resultset.getString("file_path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(conn, pstmt, resultset);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}

	public boolean isBoardWriter(int num, String pass) {
		String sql = "select * from nike.bulletinboard where boardNumber=?";
		System.out.println("들어온 pass 비교대상: " + pass);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String writer = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				writer = rs.getString("writer");
				System.out.println("수정용 db에서 가져온 writer: " + writer);
			}

			if (pass.equals(writer)) { // nullpointerexception
				return true;
			}
		} catch (Exception se) {
			se.printStackTrace();
		}

		return false;
	}

	public boolean boardModify(BoardDTO modifyBoard) throws Exception {
		String sql = "UPDATE nike.bulletinboard SET title = ?, content = ? WHERE boardNumber = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyBoard.getTitle());
			pstmt.setString(2, modifyBoard.getContent());
			pstmt.setInt(3, modifyBoard.getBoardNumber());

			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // NullPointerException
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
		}
		return false;
	}

	public void deleteBoard(int bno) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = getConnection();

		String sql = "DELETE FROM nike.bulletinboard WHERE boardNumber = ?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, bno);

		pstmt.executeUpdate();

		closeConnection(conn, pstmt, null);

	}

	public int boardReply(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT max(boardNumber) from nike.bulletinboard";
		String sql2 = "";
		String sql3 = "";
		//String sql2 = "UPDATE nike.bulletinboard set step = step+1 where ref = ? and step >= ?";// 답글달기의 경우 먼저 같은 그룹의
																								// 나머지 글들의 순서를 1씩 증가시킴

		int num = 0;
		int result = 0;

		int ref = board.getRef();
		int step = board.getStep();
		int level = board.getLevel();

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // 댓글이 달릴때 boardNumber가 될거임.
			} else {
				num = 1;
			}
			
			pstmt.close();
			//////////////////////////// 댓글이 달릴때 bno를 num 변수를 통해
			//////////////////////////// 설정함////////////////////////////////////
			sql2 += "UPDATE nike.bulletinboard set step = step+1 where ref = ? and step >?";
			
			pstmt = conn.prepareStatement(sql2); // 댓글이 달릴때 group에대해서 step(몇번째인지). , 그댓글이 달릴때 몇번째 댓글일지에대한설정(step)

			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			result = pstmt.executeUpdate(); // 답글에 step 값이 담겨있는 변수.
			System.out.println("parent댓글내에서 몇번째? " + result);
			///////////////////////////////// 이제 댓글을 다는 로직
			///////////////////////////////// ////////////////////////////////////////////////////
			step = step + 1;
			level = level + 1;

			sql3 = "INSERT INTO nike.bulletinboard(boardNumber, title, content, writer,date, hitcount, ref,step,level,file,file_path) VALUES(?,?,?,?,now(),?,?,?,?,?,?)";
			
			pstmt.close();
			
			
			pstmt = conn.prepareStatement(sql3);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWriter());
			//pstmt.setDate(5, board.getDate());
			pstmt.setInt(5, 0); //댓글에대한 조회수는 따로 설정하지않음
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, level);
			pstmt.setString(9, board.getFile());
			pstmt.setString(10, board.getFilePath());

			pstmt.executeUpdate();

			return num;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					closeConnection(conn, pstmt, rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;

	}

	/* 컨텐츠 보기 */
	public int readBoard(int bdNum) {

		BoardDTO dto = new BoardDTO();
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			getConnection(); // db 연결
			stmt = conn.createStatement();

			String query = "select * from bulletinboard where boardNumber= " + bdNum;
			rs = stmt.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				i++;
				dto.setBoardNumber(rs.getInt("boardNumber"));
				dto.setRef(rs.getInt("ref"));
				dto.setStep(rs.getInt("step"));
				dto.setLevel(rs.getInt("level"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setHitcount(rs.getInt("hitcount"));
				dto.setDate(rs.getDate("date"));
			}

			// 검색된 게시물이 하나도 없을 경우 (삭제된 게시물 등)
			if (i == 0) {
				return 1; //Ctrl.False
			}

			// 조회수 늘리기 (이미 게시글을 본 세션에 대해서는 조회수를 늘리지 않음)
			
		//	HttpSession session = request.getSession();
			//String view = "viewBdNum" + bdNum;

			// 게시물을 이미 본 세션이 아니면 조회수 올려줌
			if (dto.getWriter() == null) {
				query = "update bulletinboard set " + "hitcount=hitcount+ 1 where boardNumber= " + bdNum;
				stmt.executeUpdate(query);
				conn.commit();
				// 조회수를 올렸으면 세션에 표시
				//session.setAttribute(view, true);
			}

			// 최종적으로 객체를 담고 리턴
			//request.setAttribute("boardDTO", dto);
			return 0;

			// 업데이트 쿼리 예외 발생 시 롤백 수행
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception rollbackEx) {
				rollbackEx.printStackTrace();
				System.out.println("view update rollaback fail");
			}
			System.out.println("board db select fail");
			e.printStackTrace();
		} finally {
			try {
				closeConnection(conn, null, rs);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return 2;
	}

	public ArrayList<BoardDTO> getCommentList(int boardNumber) {
		ArrayList<BoardDTO> commentList = new ArrayList<BoardDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM nike.bulletinboard WHERE boardNumber = ? order by ref desc, step asc";

		BoardDTO dto = new BoardDTO();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {

			}
		} catch (Exception e) {

		}

		return commentList;

	}

}
