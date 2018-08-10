package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import dto.OrderedMenuDto;

public class OrderDao {
	
	public OrderDao() {
	}
	
	
	public void insert() {
	}
	
	public void select(Socket sock) {
		
		String sql = " SELECT DISTINCT A.ORDER_DATE, A.ID, B.MENU_TYPE,  A.MENU_NAME, A.COUNTS, A.BEV_COUPON, B.PRICE "
					+ " FROM ORDER_DETAIL A, MENU B "
					+ " WHERE A.MENU_NAME = B.MENU_NAME "
					+ " ORDER BY A.ORDER_DATE DESC ";
		
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
														rs.getString("ID"),
														rs.getString("MENU_TYPE"),
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
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			SocketWriter.Write(sock, list);
			DBClose.close(psmt, conn, rs);			
		}
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
