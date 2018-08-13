package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CustomerManageDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;

public class CustomerManageDao {
	
	public CustomerManageDao() {
	}

	public void execute(int number, CustomerManageDto dto, Socket sock) {

		switch (number) {
		case 1: // selectByDate (날짜순) - 백승지
			ArrayList<CustomerManageDto> orderList = selectCustomerOrder();
			// 담은 리스트를 소켓에 실어 보내자!
			SocketWriter.Write(sock, orderList);
			break;

		}
	}

	public void insert() {
	}
	
	
	
	private ArrayList<CustomerManageDto> selectCustomerOrder() {

		String sql = " SELECT A.ID, A.NAME, A.ADR, A.PHONE, B.주문건수 "
				+ " FROM MEMBER A, "
				+ " (SELECT A.ID, COUNT(*) 주문건수 "
				+ " FROM ORDER_DETAIL A, MENU B "
				+ " WHERE A.MENU_NAME = B.MENU_NAME "
				+ " AND B.MENU_TYPE = '메인' "
				+ " GROUP BY ID "
				+ " ORDER BY COUNT(*) DESC) B "
				+ " WHERE A.ID = B.ID ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ArrayList<CustomerManageDto> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				// A.ID, A.NAME, A.ADR, A.PHONE, B.주문건수
				CustomerManageDto omd = new CustomerManageDto(
						rs.getString("ID"), 
						rs.getString("NAME"),
						rs.getString("ADR"),
						rs.getString("PHONE"),
						rs.getInt("주문건수")
						);
				list.add(omd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}

	public void delete() {

	}
	
	public void update() {
		
	}

}
