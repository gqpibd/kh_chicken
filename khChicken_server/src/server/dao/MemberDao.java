package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			select(dto,sock);			
			break;
		case Singleton.DELETE:
			break;
		case Singleton.UPDATE:
			break;
		case 4: // 로그인 - 윤상필
			select_login(dto, sock);
			break;
		case 5 :
			//주문자 정보 검색
			selectOrder(sock, dto);
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
		String sql = " SELECT ID " +
				     " FROM MEMBER " + 
				     " WHERE ID = '" + dto.getId() + "' ";
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
		if(isExistingId) {
			System.out.println("중복되는 아이디 입니다");
		}else {
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

	public void select_login(dto.MemberDto dto, Socket sock) {
		boolean loginSuccess = false;
		String id = dto.getId();
		String pw = dto.getPw();
		String sql = "SELECT ID ,PW " + 
		            " FROM MEMBER " + 
				    " WHERE ID = '" + id + "' AND PW = '" + pw + "' ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) { // 일치하는 결과가 있으면 로그인 성공
				loginSuccess = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		if(loginSuccess) {
			System.out.println("로그인에 성공했습니다");
		}else {

			System.out.println("아이디 또는 패스워드가 틀렸습니다");
		}

		SocketWriter.Write(sock, loginSuccess);
		
	}
	
	public void selectOrder(Socket sock, MemberDto dto) { //주문 정보 (ID , 주소 , 전화번호 )
			
		String sql = "SELECT NAME, ID, USEDCOUPON, ADR, PHONE, " + 
				"(SELECT to_char(sysdate, 'yy/mm/dd hh24:mi:ss') " + 
				"	from dual) AS NOW_TIME " + 
				" from MEMBER " + 
				" where ID = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;		
		
		MemberDto mDto = new MemberDto();
		
		ArrayList<MemberDto> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();					
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1, dto.getId());
			rs = psmt.executeQuery();
			
			while (rs.next()) {	// 값이 들어갔는지 확인.
				mDto = new MemberDto( 
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
			}
			
//			while (rs.next()) {	// 값이 들어갔는지 확인.
//				System.out.println(rs.getString(1)+
//											rs.getString(2)+
//											rs.getInt(3)+
//											rs.getString(4)+
//											rs.getString(5)+
//											rs.getString(6));	
//			}
//			
		//	System.out.println(count + "개의 데이터 갱신!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, mDto);
	}
}
