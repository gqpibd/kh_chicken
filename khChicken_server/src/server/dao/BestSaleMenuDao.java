package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;

public class BestSaleMenuDao {
	
	public BestSaleMenuDao() {
	}
	
	
	public void insert() {
	}
	
	public void select(Socket sock) {
		
		String sql = " SELECT b.menu_type, A.menu_name, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액 "
					+ " FROM (SELECT 정렬.menu_name , 정렬.판매량 , 정렬.쿠폰 사용쿠폰 "
					+ " FROM(SELECT menu_name , SUM(counts) 판매량, SUM(BEV_COUPON) 쿠폰 "
					+ " FROM ORDER_DETAIL "
					+ " GROUP BY menu_name) 정렬) A, MENU B "
					+ " WHERE A.menu_name = B.MENU_NAME "
					+ " ORDER BY (B.PRICE*A.판매량) DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ArrayList<BestSaleMenuDto> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				// b.menu_type, A.menu_name, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액
				BestSaleMenuDto omd = new BestSaleMenuDto(rs.getString("MENU_TYPE"), 
															rs.getString("MENU_NAME"), 
															rs.getInt("판매량"), 
															rs.getInt("사용쿠폰"), 
															rs.getInt("총판매액"));
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
			SocketWriter.WriteAll(sock, list);
			DBClose.close(psmt, conn, rs);			
		}
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
