package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.dtm.DTMDOMException;

import server.db.DBClose;
import server.db.DBConnection;
import server.dto.ReviewDto;

public class ReviewDao {
/*	CREATE TABLE ORDER_DETAIL(
		    ID VARCHAR2(10),
		    MENU_NAME VARCHAR2(15),
		    COUNT1 NUMBER(10) NOT NULL,
		    BEV_COUPON NUMBER(10),
		    REVIEW VARCHAR2(1000),
		    SCORE NUMBER(5)
		);*/

	Socket sock;
	
	public void Sock_Daoreview(Socket sock) {
		this.sock = sock;
	}
	
	public List<client.dto.ReviewDto> Choice(int number) {
		switch(number) {
		case 0 :
			break;
		case 1 :
			return select();
		case 2 :
			break;
		case 3 :
			break;
		}
		return select();
	}
	
	
	
	public void insert(ReviewDto dto) {
		String id = dto.getUserId();
		String MenuName = dto.getMenuName();
		String Review = dto.getReview();
		int score = dto.getScore();
		
		
		String sql = " INSERT INTO MEMBER (ID, MENU_NAME, COUNT1, BEV_COUPON, REVIEW, SCORE ) "
				+ "VALUES ( '" + id + "', '"+MenuName+"', " + 0 +", " + 0 + ", '"
							   + Review + "', '" + 0 + "' )";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
				
	}
	
	public List<client.dto.ReviewDto> select() {
		
		List<client.dto.ReviewDto> list = new ArrayList<>();
		client.dto.ReviewDto dto  =new client.dto.ReviewDto();
		String sql = "SELECT * "
				+ " FROM ORDER_DETAIL ";
		System.out.println(sql);
		int i  = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
				try {
					conn = DBConnection.getConnection();
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					
					
					while(rs.next()) {
						dto.setReview(rs.getString(6));
						list.add(dto);
						i++;
					}
					
					
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DBClose.close(psmt, conn, rs);
				}
		
		System.out.println(list.get(0).getReview());
		
		return list;
		
	}
	
	public void update(ReviewDto dto) {
		
	}
	
	public void delete() {
		
	}

}
