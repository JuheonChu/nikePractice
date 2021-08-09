package com.nike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nike.dto.BoardDTO;
import com.nike.dto.ProductDTO;

public class ProductDAO {
	private static final ProductDAO instance = new ProductDAO();

	private ProductDAO() {
	}

	public static ProductDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/nike?charactherEncoding=UTF-8&serverTimezone=UTC", "root", "tiger");
		return conn;
	}

	private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) throws Exception {
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
	
	public ArrayList<ProductDTO> selectProductDefault(String type, String gender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> shoesArr = new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		try {

			conn = getConnection();
			String sql = "SELECT * FROM product WHERE type=? AND (gender='all' OR gender=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, gender);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setType(rs.getString("type"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExplain(rs.getString("explain"));
				dto.setImg(rs.getString("img"));
				dto.setDetailexplain(rs.getString("detailexplain"));
				dto.setGender(rs.getString("gender"));
				shoesArr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoesArr;
	}

	public ArrayList<ProductDTO> selectProductDesc(String type, String gender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> shoesArr = new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		try {

			conn = getConnection();
			String sql = "SELECT * FROM product WHERE type=? AND (gender='all' OR gender=?) order by price desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, gender);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setType(rs.getString("type"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExplain(rs.getString("explain"));
				dto.setImg(rs.getString("img"));
				dto.setDetailexplain(rs.getString("detailexplain"));
				dto.setGender(rs.getString("gender"));
				shoesArr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoesArr;
	}
	
	public ArrayList<ProductDTO> selectProductAsc(String type, String gender) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> shoesArr = new ArrayList<ProductDTO>();
		ProductDTO dto = null;
		try {

			conn = getConnection();
			String sql = "SELECT * FROM product WHERE type=? AND (gender='all' OR gender=?) order by price asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, gender);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new ProductDTO();
				dto.setType(rs.getString("type"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExplain(rs.getString("explain"));
				dto.setImg(rs.getString("img"));
				dto.setDetailexplain(rs.getString("detailexplain"));
				dto.setGender(rs.getString("gender"));
				shoesArr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoesArr;
	}
	
	public ProductDTO selectProduct(String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO dto = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM product WHERE type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new ProductDTO();
				dto.setType(rs.getString("type"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExplain(rs.getString("explain"));
				dto.setImg(rs.getString("img"));
				dto.setDetailexplain(rs.getString("detailexplain"));
				dto.setGender(rs.getString("gender"));
			}
			closeConnection(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	
	

	public ProductDTO detailProduct(int pnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO dto = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM product WHERE pnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new ProductDTO();
				dto.setType(rs.getString("type"));
				dto.setPnum(rs.getInt("pnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExplain(rs.getString("explain"));
				dto.setImg(rs.getString("img"));
				dto.setDetailexplain(rs.getString("detailexplain"));
				dto.setGender(rs.getString("gender"));
			
			}
			closeConnection(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public void insertProduct(ProductDTO product) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;
		ProductDTO dto = null;
		try {
			conn = getConnection();

			String sql = "INSERT INTO `product`(`type`, `name`, price, `explain`, detailexplain, img, gender) VALUES(?,?,?,?,?,?,?)";
			// INSERT INTO `product`(`type`, `name`, price, `explain`, detailexplain,
			// img,gender)
			// values('','',4,'','','','');
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, product.getType());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getExplain());
			pstmt.setString(5, product.getDetailexplain());
			pstmt.setString(6, product.getImg());
			pstmt.setString(7, product.getGender());

			pstmt.executeUpdate();

			closeConnection(conn, pstmt, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	//type 종류 뽑아오기 (shoes, clothes 등)
		public ArrayList<String> category(){
			ArrayList<String> cateList = new ArrayList<String>();;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				
				String sql = "SELECT type from product group by type";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					cateList.add(rs.getString(1));
				}
				closeConnection(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cateList;
		}
		
		//gender 종류 뽑아오기 (women, men, all 등)
		public ArrayList<String> genderType(){
			ArrayList<String> genderList = new ArrayList<String>();;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				
				String sql = "SELECT gender from product group by gender";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					genderList.add(rs.getString(1));
				}
				closeConnection(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return genderList;
		}
		
		//상품 수정
		public void updateProduct(int pnum, ProductDTO product) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ProductDTO dto = null;
			try {
				conn = getConnection();
				String sql = "UPDATE nike.product SET `type`=?, `name`=?, price=?, `explain`=?, detailexplain=?, img=?, gender=? WHERE pnum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, product.getType());
				pstmt.setString(2, product.getName());
				pstmt.setInt(3, product.getPrice());
				pstmt.setString(4, product.getExplain());
				pstmt.setString(5, product.getDetailexplain());
				pstmt.setString(6, product.getImg());
				pstmt.setString(7, product.getGender());
				pstmt.setInt(8, pnum);
				pstmt.executeUpdate();
				closeConnection(conn, pstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//상품 삭제
		public void deleteProduct(int pnum) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				
				String sql = "DELETE FROM nike.product WHERE pnum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,pnum);
				pstmt.executeUpdate();
				closeConnection(conn, pstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<ProductDTO> selectProduct(String[] p1) {
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			ArrayList<String[]>strArr = new ArrayList<String[]>();
			ArrayList<Integer>pnumArrayList = new ArrayList<Integer>();
		//	ArrayList<Integer>quantityArrayList = new ArrayList<Integer>();
			ArrayList<ProductDTO>products = new ArrayList<ProductDTO>();
			
			String pnum = "";
		//	String quantity = "";
			for(int i = 0; i < p1.length;i++) {
				strArr.add(p1[i].substring(0,3).split("_"));
				pnum +=strArr.get(i)[0]+" ";
		//		quantity +=strArr.get(i)[1]+ " ";
			}
			
			String [] pnumArr = pnum.split(" ");
			
			
		//	String [] quantityArr = pnum.split(" ");
			
			for(int j = 0; j < pnumArr.length;j++) {
				pnumArrayList.add(Integer.parseInt(pnumArr[j]));
		//		quantityArrayList.add(Integer.parseInt(quantityArr[j]));
			}
			
			try {
				conn=getConnection();
			
			for(int i=0;i<pnumArrayList.size();i++) {
				ProductDTO pdt = new ProductDTO();
				
				String sql="SELECT * FROM nike.product WHERE pnum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,pnumArrayList.get(i));
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					pdt.setPnum(rs.getInt("pnum"));
					pdt.setType(rs.getString("type"));
					pdt.setName(rs.getString("name"));
					pdt.setPrice(rs.getInt("price"));
					pdt.setExplain(rs.getString("explain"));
					pdt.setDetailexplain(rs.getString("detailexplain"));
					pdt.setImg(rs.getString("img"));
					pdt.setGender(rs.getString("gender"));
				}
				
				products.add(pdt);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return products;
		}
		
		
		public ArrayList<Integer> selectProductByQuantity(String[] p1) {
		
			ArrayList<String[]>strArr = new ArrayList<String[]>();
		//	ArrayList<Integer>pnumArrayList = new ArrayList<Integer>();
			ArrayList<Integer>quantityArrayList = new ArrayList<Integer>();
		//	ArrayList<ProductDTO>products = new ArrayList<ProductDTO>();
			
		//	String pnum = "";
			String quantity = "";
			for(int i = 0; i < p1.length;i++) {
				strArr.add(p1[i].substring(0,3).split("_"));
		//		pnum +=strArr.get(i)[0]+" ";
				quantity +=strArr.get(i)[2]+ " ";
			}
			
	//		String [] pnumArr = pnum.split(" ");
			
			
			String [] quantityArr = quantity.split(" ");
			
			for(int j = 0; j < quantityArr.length;j++) {
				quantityArrayList.add(Integer.parseInt(quantityArr[j]));
			}
		
			
			return quantityArrayList;
		}
		
		
		public ProductDTO[] selectProduct(String[][] pns,int length) {
			Connection conn=null;
			Statement st=null;
			ResultSet rs=null;
			
			ProductDTO[] pd = new ProductDTO[length];
			
			try {
			conn=getConnection();
			
			for(int i=0;i<length;i++) {
				pd[i]=new ProductDTO();
				String sql="SELECT * FROM product name='"+pns[i][0]+"'";
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				
				if(rs.next()) {
					pd[i].setPnum(rs.getInt("pnum"));
					pd[i].setType(rs.getString("type"));
					pd[i].setName(rs.getString("name"));
					pd[i].setPrice(rs.getInt("price"));
					pd[i].setExplain(rs.getString("explain"));
					pd[i].setDetailexplain(rs.getString("detailexplain"));
					pd[i].setImg(rs.getString("img"));
					pd[i].setGender(rs.getString("gender"));
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return pd;
		}
		
		
		public ArrayList<ProductDTO> selectProductExplain(String explain,String type, String gender){
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			ProductDTO dto;
			ArrayList<ProductDTO> dtoArr = new ArrayList<ProductDTO>();
			try {
				conn= getConnection();
				String sql = "SELECT * FROM nike.product WHERE `explain` LIKE ? AND gender= ? AND type= ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "%"+explain+"%");
				pstmt.setString(2, gender);
				pstmt.setString(3, type);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					dto = new ProductDTO();
					dto.setType(rs.getString("type"));
					dto.setPnum(rs.getInt("pnum"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setExplain(rs.getString("explain"));
					dto.setImg(rs.getString("img"));
					dto.setDetailexplain(rs.getString("detailexplain"));
					dto.setGender(rs.getString("gender"));
					dtoArr.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dtoArr;
		}


	
}
