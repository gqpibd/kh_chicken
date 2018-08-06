package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import model.BbsDto;
import server.dto.OrderedMenuDto;

public class OrderDao {
	
	public OrderDao() {
	}
	
	
	public void insert() {
		
	}
	
	
	
	public List<BbsDto> getBbsList() {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT,"
				+ " WDATE, DEL, READCOUNT "
				+ " FROM BBS "
				+ " ORDER BY WDATE DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
				
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {				
				BbsDto dto = new BbsDto(rs.getInt(1),
										rs.getString(2), 
										rs.getString(3), 
										rs.getString(4), 
										rs.getString(5), 
										rs.getInt(6), 
										rs.getInt(7));
				
				list.add(dto);
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}
	public List<OrderedMenuDto> select() {
		
		String sql = " SELECT MENU_NAME,   "
		/*
		CREATE TABLE ORDER_DETAIL(
	    ID VARCHAR2(10),
	    MENU_NAME VARCHAR2(15),
	    COUNT NUMBER(10) NOT NULL,
	    BEV_COUPON NUMBER(10),
	    ORDER_DATE DATE NOT NULL,
	    REVIEW VARCHAR2(1000),
	    SCORE NUMBER(5),
	    CONSTRAINT FK_ID FOREIGN KEY(ID)
	    REFERENCES MEMBER(ID),
	    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
	    REFERENCES MENU(MENU_NAME)
		);
		 */
		
		return null;
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
