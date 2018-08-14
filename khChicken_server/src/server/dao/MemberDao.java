package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class MemberDao {

	public void execute(int number, MemberDto dto, Socket sock) {

		switch (number) {
		case Singleton.INSERT: // 회원가입 - 윤상필
			insert(dto);
			System.out.println(dto.getId() + "를 멤버 테이블에 추가하였습니다");
			break;
		case Singleton.SELECT: // 아이데 중복 체크 - 윤상필
			select(dto, sock);
			break;
		case Singleton.DELETE:
			break;
		case Singleton.UPDATE:
			break;
		case 4: // 로그인 - 윤상필
			select_login(dto, sock);
			break;
		case 5: // 내정보 수정
			Inform_Update(dto);
			break;
		}
	}

	public MemberDao() {

	}

	public void insert(MemberDto dto) {
		String name = dto.getName();
		String id = dto.getId();
		String pw = dto.getPw();
		int coupon = dto.getCoupon();
		int auth = dto.getAuth();
		String address = dto.getAddress();
		String phone = dto.getPhone();

		String sql = "INSERT INTO MEMBER (name, id, pw, USEDCOUPON, auth, adr, phone)"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ? )";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, id);
			psmt.setString(3, pw);
			psmt.setInt(4, coupon);
			psmt.setInt(5, auth);
			psmt.setString(6, address);
			psmt.setString(7, phone);

			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	public void select(MemberDto dto, Socket sock) {
		boolean isExistingId = false;
		// 존재하는 아이디인지 확인
		String sql = " SELECT ID " + " FROM MEMBER " + " WHERE ID = '" + dto.getId() + "' ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				isExistingId = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		if (isExistingId) {
			System.out.println("중복되는 아이디 입니다");
		} else {
			System.out.println("사용 가능한 아이디 입니다");
		}

		SocketWriter.Write(sock, isExistingId);
	}

	public void update() {
		String sql = "";

	}

	public void delete() {
		String sql = "";

	}

	public void select_login(MemberDto dto, Socket sock) {
		MemberDto loginUser = null;
		String id = dto.getId();
		String pw = dto.getPw();
		String sql = "SELECT NAME, USEDCOUPON, AUTH, ADR, PHONE " + " FROM MEMBER " + " WHERE ID = '" + id
				+ "' AND PW = '" + pw + "' ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				loginUser = new MemberDto();
				loginUser.setId(id);
				loginUser.setPw(pw);
				loginUser.setName(rs.getString(1));
				loginUser.setCoupon(rs.getInt(2));
				loginUser.setAuth(rs.getInt(3));
				loginUser.setAddress(rs.getString(4));
				loginUser.setPhone(rs.getString(5));
				System.out.println(loginUser.getName() + "님이 로그인 했습니다");
			} else {
				System.out.println("아이디 또는 패스워드가 틀렸습니다");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, loginUser);
	}

	public void Inform_Update(MemberDto dto) {

		String sql = " UPDATE MEMBER " + " SET NAME  = ?, adr = ?, phone = ? " + "WHERE ID =  ? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getAddress());
			psmt.setString(3, dto.getPhone());
			psmt.setString(4, dto.getId());
			psmt.executeQuery();
			System.out.println(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

	}
}
