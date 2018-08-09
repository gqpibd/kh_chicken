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
	}
	
	public List<OrderedMenuDto> select() {
		
		String sql = " SELECT MENU_NAME, COUNT, BEV_COUPON, b.PRICE "
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
