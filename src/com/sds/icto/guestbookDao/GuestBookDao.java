package com.sds.icto.guestbookDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.guestbookVo.GuestBookVo;

@Repository
public class GuestBookDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;

		// 1 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2 connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return conn;
	}

	public void insert(GuestBookVo vo) {
		try{
		// 1 connection 생성
		Connection conn = getConnection();

		// 2 Statement 준비
		String sql = "insert into GUESTBOOK values(guestbook_seq.nextval, ?,?,?,SYSDATE)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		// 3 바인딩
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getMsg());
		
		// 4 쿼리실행
		pstmt.executeUpdate();
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (SQLException ex){
			ex.printStackTrace();
		}

	}

	
	public void delete(GuestBookVo vo) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		
		String sql = "delete from GUESTBOOK where NO=? and PASSWORD=?";

		// statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getPwd());
		
		pstmt.executeUpdate();
		
		// 자원정리

	/*	try {
			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
		}*/

	}

	public List<GuestBookVo> fetchList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		try{
		

		Connection conn = getConnection();

		// 3 statement 생성
		Statement stmt = conn.createStatement();

		// 4 SQL문 실행
		String sql = "select * from guestbook";

		ResultSet rs = stmt.executeQuery(sql);

		// 5 결과 처리
		while (rs.next()) {
			Long no = rs.getLong(1);
			String nm = rs.getString(2);
			String msg = rs.getString(4);
			String date = rs.getString(5);

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setName(nm);
			vo.setMsg(msg);
			vo.setDate(date);
			
			list.add(vo);
		}
		}catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}catch (SQLException ex){
			ex.printStackTrace();
		}

		return list;
	}
}
