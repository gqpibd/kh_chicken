package server.dao;

import java.net.Socket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import client.dto.ReviewDto;
import server.db.DBClose;
import server.db.DBConnection;

public class ReviewDao {
/*	CREATE TABLE ORDER_DETAIL(
    ID VARCHAR2(10),
    MENU_NAME VARCHAR2(30),
    COUNT1 NUMBER(10) NOT NULL,
    BEV_COUPON NUMBER(3),
    ORDER_DATE DATE NOT NULL,
    REVIEW VARCHAR2(1000),
    SCORE NUMBER(5),
		);*/

	Socket sock;
	
	public void Sock_Daoreview(Socket sock) {
		this.sock = sock;
	}
	
	public List<client.dto.ReviewDto> Choice(int number ,client.dto.ReviewDto dto) {
		switch(number) {
		case 0 :
			return insert(dto);
		case 1 :
			return select();
		case 2 :
			break;
		case 3 :
			break;
		}
		return select();
	}
	
	
	
	public List<client.dto.ReviewDto> insert(client.dto.ReviewDto dto) {
		String id = dto.getUserId();
		String MenuName = dto.getMenuName();
		String Review = dto.getReview();
		int score = dto.getScore();
	
		
		String sql = " INSERT INTO ORDER_DETAIL (ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE,REVIEW, SCORE ) "
				+ "VALUES ( '" + id + "', '"+MenuName+"', " + 0 +", " + 0 + ", TO_DATE(sysdate , 'yyyy/mm/dd'), '"
							   + Review + "', " + 1 + " )";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeQuery();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return null;
	}
	
	public List<client.dto.ReviewDto> select() {
		
		String sql = "SELECT ID, MENU_NAME, COUNT1, BEV_COUPON, ORDER_DATE, REVIEW, SCORE "
				+ " FROM ORDER_DETAIL ";
		
		
		System.out.println(sql);
		int i  = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<client.dto.ReviewDto> list = new ArrayList<>();
				try {
				
					conn = DBConnection.getConnection();
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					
					
					while(rs.next()) {
					client.dto.ReviewDto dto = 
					new client.dto.ReviewDto(rs.getString(1), rs.getString(2), rs.getInt(3),
							rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
						
						list.add(dto);
						System.out.println(dto.toString());
					}
					
					
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DBClose.close(psmt, conn, rs);
				}
		
		System.out.println(list.size()+"사이즈");
		return list;
		
	}
	
	public void update(ReviewDto dto) {
		
	}
	
	public void delete() {
		
	}

}
