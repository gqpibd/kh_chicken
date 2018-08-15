package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.OrderedMenuDto;
import server.communicator.SocketWriter;
import server.dao.interfaces.OrderDaoImpl;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class OrderDao implements OrderDaoImpl{

	public OrderDao() {
	}

	public void execute(int number, OrderedMenuDto dto, Socket sock) {

		switch (number) {
		case Singleton.INSERT: // 주문 내역 추가 - 우진영
			insert(dto);
			System.out.println("주문 내역에 추가하였습니다");
			break;
		case Singleton.DELETE: // delete
			break;
		case Singleton.UPDATE: // update
			break;
		case Singleton.SELECT: // 내가 받은 쿠폰 수량
			selectAvailableCoupon(dto, sock);			
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

	public void selectAvailableCoupon(OrderedMenuDto oDto, Socket sock) { // socket , 클라에서 넘어온 id : ?에 들어갈꺼.
		String sql = "SELECT TRUNC(COUNT( O.REVIEW )/3) " 
				+ "FROM MENU N, ORDER_DETAIL O "
				+ "WHERE N.MENU_NAME = O.MENU_NAME " 
				+ "AND N.MENU_TYPE = '메인' " 
				+ "AND REVIEW IS NOT NULL "
				+ "GROUP BY ID " + "HAVING O.ID = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 쿠폰수를 저장할 변수
		String couponEA = "";

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, oDto.getId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				// sql문에서 얻어낸 값 저장.(쿠폰수)
				couponEA = rs.getString(1); // ()안의 숫자는 select의 칼럼 순서				
			}else {
				couponEA = "0";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		System.out.println("사용 가능한 쿠폰은 "+ couponEA + "개 입니다.");
		SocketWriter.Write(sock, couponEA);
	}
}
