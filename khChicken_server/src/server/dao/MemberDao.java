package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDto;
import oracle.net.aso.s;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class MemberDao {

	public MemberDao() {

	}

	public Object insert(MemberDto dto) {

		String id = dto.getId();
		String pw = dto.getPw();
		String name = dto.getName();
		int coupon = dto.getCoupon();
		int auth = dto.getAuth();
		String address = dto.getAddress();
		String phone = dto.getPhone();

		String sql = "INSERT INTO MEMBER (name, id, pw, USEDCOUPON, auth, adr, phone)" + " VALUES ( '" + name + "', '"
				+ id + "', '" + pw + "', " + coupon + ", " + auth + ", '" + address + "', '" + phone + "' )";

		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);

		}
		return null;

	}

	public boolean select(MemberDto dto) {
		// 존재하는 아이디인지 확인
		String sql = "SELECT ID"
				+ "	FROM MEMBER" 
				+ " WHERE ID = '" + dto.getId() + "' ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return false;

	}

	public void update() {
		String sql = "";

	}

	public void delete() {
		String sql = "";

	}

	public boolean select_loging(dto.MemberDto dto) {
		String id = dto.getId();
		String pw = dto.getPw();
		String sql = "SELECT ID ,PW " + " FROM MEMBER " + " WHERE ID = '" + id + "' AND PW = '" + pw + "' ";

		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		dto.setId(null);
		dto.setPw(null);

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.println("11");
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				System.out.println(dto.getId());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		if (dto.getId() == null && dto.getPw() == null) {
			return false;
		} else {
			return true;
		}

	}

	public void execute(int number, MemberDto dto, Socket sock) {

		switch (number) {
		case 0: // insert
			insert(dto);
			System.out.println(dto.getId() + "를 멤버 테이블에 추가하였습니다");
			break;
		case 1: // select -- id_select
			boolean isExistingId = select(dto);
			System.out.println("존재하는 아이디 : " + isExistingId);
			SocketWriter.Write(sock, isExistingId);
			break;
		case 2: // delete

			break;
		case 3: // update
			break;
		}
	}

}
