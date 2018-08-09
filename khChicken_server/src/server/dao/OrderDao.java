package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import server.dto.OrderedMenuDto;

public class OrderDao {
	
	public OrderDao() {
	}
	
	
	public void insert() {
	}
	
	public void select(Socket sock) {
		
<<<<<<< HEAD
		String sql = " SELECT MENU_NAME, COUNT, BEV_COUPON, b.PRICE "
					+ " FROM ORDER_DETAIL a, "
					+ " (SELECT DISTINCT PRICE "
=======
		String sql = " SELECT DISTINCT a.ORDER_DATE, a.menu_name, a.counts, a.bev_coupon, b.price "
>>>>>>> branch 'seungji_server' of https://github.com/gqpibd/kh_semi.git
					+ " FROM ORDER_DETAIL a, MENU b "
					+ " WHERE a.menu_name = b.menu_name ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ArrayList<OrderedMenuDto> list = new ArrayList<>();
		
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				OrderedMenuDto omd = new OrderedMenuDto(rs.getDate("ORDER_DATE"),
														rs.getNString("MENU_NAME"),
														rs.getInt("COUNTS"),
														rs.getInt("BEV_COUPON"),
														rs.getInt("PRICE"));
				list.add(omd);
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 담은 리스트를 소켓에 실어 보내자!
			SocketWriter.WriteAll(sock, list);
			DBClose.close(psmt, conn, rs);			
		}
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
