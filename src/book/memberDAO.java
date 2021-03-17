package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class memberDAO {
	
	private static memberDAO instance = new memberDAO();
	
	public static memberDAO getInstance() {
		return instance;
	}
	
	public static Connection getConn() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","hr");
		return con;
	}
	
	public ArrayList<memberVO> selectMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		try {
			conn = getConn();
			String sql="SELECT * from MEM_TBL_BOOK";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberVO vo = new memberVO();
				vo.setCustno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				vo.setJoindate(rs.getDate(3));
				switch (rs.getString(4)) {
				case "A": vo.setGrade("VIP");break;
				case "B": vo.setGrade("일반");break;
				case "C": vo.setGrade("직업");break;
				}
				vo.setAddress(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select에서 오류남");
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	public int getMax(){
		int max = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			String sql = "SELECT MAX(custno) from mem_tbl_book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				max = rs.getInt(1)+1;
				System.out.println(max);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return max;
	}
	public boolean insertMember(memberVO vo){
		boolean rst = false;
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "insert into mem_tbl_book values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setDate(3, vo.getJoindate());
			pstmt.setString(4, vo.getGrade());
			pstmt.setString(5, vo.getAddress());
			
			cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				rst = true;
				System.out.println(rst);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, null);
		}
		System.out.println();
		return rst;
	}
	public boolean updateMember(memberVO vo) {
		boolean rst = false;
		int num = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			String sql = "UPDATE MEM_TBL_BOOK SET custno=?,custname=?,joindate=?,grade=?,address=? WHERE custno=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setDate(3, vo.getJoindate());
			pstmt.setString(4, vo.getGrade());
			pstmt.setString(5, vo.getAddress());
			pstmt.setInt(6, vo.getCustno());
			
			num = pstmt.executeUpdate();
			
			if(num >0) {
				rst = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, null);
		}
		return rst;
	}
	public memberVO getAline(int custno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberVO vo = new memberVO();
		try {
			conn = getConn();
			String sql = "SELECT * from MEM_TBL_BOOK where custno =  ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setCustno(custno);
				vo.setCustname(rs.getString(2));
				vo.setJoindate(rs.getDate(3));
				vo.setGrade(rs.getString(4));
				vo.setAddress(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return vo;
	}
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {if(conn!=null) conn.close();} catch (SQLException e) {e.printStackTrace();	}
		try {if(pstmt!=null) pstmt.close();} catch (SQLException e) {e.printStackTrace();	}
		try {if(rs!=null) rs.close();} catch (SQLException e) {e.printStackTrace();	}
	}
	
	
}
