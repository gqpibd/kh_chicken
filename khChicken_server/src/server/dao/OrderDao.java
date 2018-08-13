package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import dto.BestSaleMenuDto;
import dto.MemberDto;
import dto.OrderedMenuDto;
import dto.ReviewDto;
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
		case Singleton.SELECT: // select : 2
			String coupon = selectAvailableCoupon(dto);
			SocketWriter.Write(sock, coupon);	
			break;
		case Singleton.DELETE: // delete
			break;
		case Singleton.UPDATE: // update
			break;
		case 4: // selectByDate (날짜순) - 백승지
			ArrayList<OrderedMenuDto> orderList = selectByDate();
			SocketWriter.Write(sock, orderList);
			break;
		case 5: // selectBySalse (매출순) - 백승지
			ArrayList<BestSaleMenuDto> bestSaleList = selectBySalse();
			SocketWriter.Write(sock, bestSaleList);
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

	public ArrayList<BestSaleMenuDto> selectBySalse() {

		String sql = " SELECT b.menu_type, A.menu_name, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액 "
				+ " FROM (SELECT 정렬.menu_name , 정렬.판매량 , 정렬.쿠폰 사용쿠폰 "
				+ " FROM(SELECT menu_name , SUM(counts) 판매량, SUM(BEV_COUPON) 쿠폰 " + " FROM ORDER_DETAIL "
				+ " GROUP BY menu_name) 정렬) A, MENU B " + " WHERE A.menu_name = B.MENU_NAME "
				+ " ORDER BY (B.PRICE*A.판매량) DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ArrayList<BestSaleMenuDto> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				// b.menu_type, A.menu_name, A.판매량, A.사용쿠폰, (B.PRICE*A.판매량) 총판매액
				BestSaleMenuDto omd = new BestSaleMenuDto(rs.getString("MENU_TYPE"), rs.getString("MENU_NAME"),
						rs.getInt("판매량"), rs.getInt("사용쿠폰"), rs.getInt("총판매액"));
				list.add(omd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public ArrayList<OrderedMenuDto> selectByDate() {

		String sql = " SELECT DISTINCT A.ORDER_DATE, A.ID, B.MENU_TYPE,  A.MENU_NAME, A.COUNTS, A.BEV_COUPON, B.PRICE "
				+ " FROM ORDER_DETAIL A, MENU B " + " WHERE A.MENU_NAME = B.MENU_NAME "
				+ " ORDER BY A.ORDER_DATE DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ArrayList<OrderedMenuDto> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				OrderedMenuDto omd = new OrderedMenuDto(
						rs.getDate("ORDER_DATE"), 
						rs.getString("ID"),
						rs.getString("MENU_TYPE"), 
						rs.getNString("MENU_NAME"), 
						rs.getInt("COUNTS"),
						rs.getInt("BEV_COUPON"), 
						rs.getInt("PRICE")
						);
				list.add(omd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 담은 리스트를 소켓에 실어 보내자!
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public String selectAvailableCoupon(OrderedMenuDto oDto) {	//socket , 클라에서 넘어온 id : ?에 들어갈꺼.
		String sql = "SELECT TRUNC(COUNT( O.REVIEW )/3)  " + 
							"FROM MENU N, ORDER_DETAIL O " + 
							"WHERE N.MENU_NAME = O.MENU_NAME " + 
								"AND N.MENU_TYPE = '메인' " + 
								"AND REVIEW IS NOT NULL " + 
							"GROUP BY ID " + 
							"HAVING O.ID = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		//쿠폰수를 저장할 변수
		String couponEA = "";

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,oDto.getId()); 
			rs = psmt.executeQuery();

		
			
			if(rs.next()) {
				//sql문에서 얻어낸 값 저장.(쿠폰수)
				couponEA = rs.getString(1);	//()안의 숫자는 select의 칼럼 순서						
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
			
		return couponEA;
	}
	
	public void delete() {

	}

}
