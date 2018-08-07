package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.db.DBClose;
import server.db.DBConnection;
import server.dto.OrderedMenuDto;

public class OrderDao {
	
	public OrderDao() {
	}
	
	
	public void insert() {
		
		String sql = " INSERT INTO ORDER_DETAIL(ID, MENU_NAME, COUNT, BEV_COUPON, ORDER_DATE, REVIEW, SCORE) "
				+ " VALUES('홍길동', '후라이드 치킨', 1, 0, SYSDATE, null, null) ";
		
		String sql2 = " INSERT INTO MENU(MENU_NAME, PRICE) "
				+ " VALUES('후라이드 치킨', 16000) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql2);
			psmt.executeQuery();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		
				/*
				INSERT INTO EMP(employee_id, first_name, last_name, email, phone_number, hire_date,
                job_id, salary, commission_pct, manager_id, department_id)
				VALUES(NULL, NULL, '홍길동', 'hgj@naver.com', null, sysdate, 'IT_PROG', null, null, null, null);
				 */
				
				/*
				CREATE TABLE MENU(
			    MENU_NAME VARCHAR2(15) PRIMARY KEY,
			    PRICE NUMBER(5) NOT NULL
				);
			
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
	}
	
	public List<OrderedMenuDto> select() {
		
		String sql = " SELECT ORDER_DATE, MENU_NAME, COUNT, BEV_COUPON, b.PRICE "
					+ " FROM ORDER_DETAIL a, "
					+ " (SELECT DISTINCT PRICE "
					+ " FROM ORDER_DETAIL a, MENU b "
					+ " WHERE a.menu_name = b.menu_name) b ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<OrderedMenuDto> list = new ArrayList<OrderedMenuDto>();
		
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				
				System.out.println("b.PRICE = "+ rs.getInt("b.PRICE"));
				
				/*OrderedMenuDto omd = new OrderedMenuDto(rs.getNString("MENU_NAME"), 
														rs.getInt("b.PRICE"), 
														rs.getInt("BEV_COUPON"), 
														rs.getInt("COUNT"));
				list.add(omd);*/
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
