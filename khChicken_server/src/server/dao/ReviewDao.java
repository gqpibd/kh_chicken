package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void select(ReviewDto dto) {
		
	}
	
	public void update(ReviewDto dto) {
		
	}
	
	public void delete() {
		
	}

}
