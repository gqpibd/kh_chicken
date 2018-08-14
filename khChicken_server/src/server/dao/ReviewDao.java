package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrderedMenuDto;
import dto.ReviewDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class ReviewDao {

	public ReviewDao() {
	}

	public void execute(int number, ReviewDto dto, Socket sock) {
		switch (number) {
		case Singleton.INSERT: // 주문 내역 없는 리뷰를 추가하는 경우는 없다.
			break;
		case Singleton.SELECT: // 리뷰 불러오기 - 윤상필
			select(dto, sock);
			break;
		case Singleton.DELETE:
			break;
		case Singleton.UPDATE: // 기존 주문한 내역에 리뷰 추가하기 - 윤상필
			update(dto);
			break;
		case 4: // 내가 지금까지 시킨 치킨 정보 뺴오기
			select_Interface(dto, sock);
			break;
		}
	}

	public void select(ReviewDto dto, Socket sock) {
		String sql = "SELECT ID, MENU_NAME, ORDER_DATE, REVIEW, SCORE " + " FROM ORDER_DETAIL "
				+ " WHERE MENU_NAME = ? AND REVIEW IS NOT NULL";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<ReviewDto> list = new ArrayList<>();
		try {

			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMenuName());
			rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.println(dto.getMenuName());
				// ID, MENU_NAME, ORDER_DATE, REVIEW, SCORE
				ReviewDto resultDto = new dto.ReviewDto();
				resultDto.setUserId(rs.getString(1));
				resultDto.setMenuName(rs.getString(2));
				resultDto.setOrderDate(rs.getString(3));
				resultDto.setReview(rs.getString(4));
				resultDto.setScore(rs.getInt(5));

				list.add(resultDto);
				// System.out.println(dto.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		SocketWriter.Write(sock, list);

	}

	public void update(ReviewDto dto) { // 기존 주문한 내역에 리뷰 추가하기
		String sql = " UPDATE ORDER_DETAIL " + " SET REVIEW = ?, SCORE = ? "
				+ " WHERE ID = ? AND MENU_NAME = ? AND ORER_DATE = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getReview());
			psmt.setInt(2, dto.getScore());
			psmt.setString(3, dto.getUserId());
			psmt.setString(4, dto.getMenuName());
			psmt.setString(5, dto.getOrderDate());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.println(dto.getUserId() + "님의 " + dto.getMenuName() + "에 대한 리뷰가 업데이트 되었습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	public void delete() {

	}

	public void select_Interface(ReviewDto dto, Socket sock) {
		String id = dto.getUserId();
		String sql = "SELECT MENU_NAME, REVIEW,  ID, ORDER_DATE, SCORE " + " FROM ORDER_DETAIL" + " WHERE ID = ? ";

		System.out.println(sql);
		ReviewDto rdto = null;

		List<ReviewDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rdto = new ReviewDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				list.add(rdto);
			}
			System.out.println(list);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		SocketWriter.Write(sock, list);
	}
}
