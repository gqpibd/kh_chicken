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
import server.singleton.Singleton;

public class OrderDao {

	public OrderDao() {
	}

	public void execute(int number, OrderedMenuDto dto, Socket sock) {

		switch (number) {
		case Singleton.INSERT: // 주문 내역 추가 - 우진영
			insert(dto);
			System.out.println("주문 내역에 추가하였습니다");
			break;
		case Singleton.SELECT: // select
			break;
		case Singleton.DELETE: // delete
			break;
		case Singleton.UPDATE: // update
			break;
		}
		
	}

	public void insert(OrderedMenuDto dto) {
		String sql = " INSERT INTO ORDER_DETAIL (ID, MENU_NAME, COUNTS, BEV_COUPON, ORDER_DATE) "
				+ " VALUES (?, ?, ?, ?, SYSDATE) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getMenu_name());
			psmt.setInt(3, dto.getCount());
			psmt.setInt(4, dto.getCoupon());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.println("주문 내역을 저장하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}
	
	

	public void delete() {

	}

}
