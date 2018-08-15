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
import server.dao.interfaces.ReviewDaoImpl;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class ReviewDao implements ReviewDaoImpl {
	private final int WRITABLE_REVIEW = 4;
	private final int SELECT_BY_ID = 5;

	public ReviewDao() {
	}

	public void execute(int number, ReviewDto dto, Socket sock) {
		switch (number) {
		case Singleton.INSERT: // 주문 내역 없는 리뷰를 추가하는 경우는 없다.
			break;
		case Singleton.DELETE:
			break;
		case Singleton.UPDATE: // 기존 주문한 내역에 리뷰 추가하기
			update(dto);
			break;
		case Singleton.SELECT: // 리뷰 불러오기
			select(dto, sock);
			break;
		case WRITABLE_REVIEW: // 작성할 수 있는 리뷰가 있는지 확인
			select_WritableReview(dto, sock);
			break;
		case SELECT_BY_ID: // 사용자 아이디별 내역 모두 불러오기
			select_byUserId(dto, sock);
		}
	}

	public void update(ReviewDto dto) { // 기존 주문한 내역에 리뷰 추가하기
		String sql = " UPDATE ORDER_DETAIL " + " SET REVIEW = ?, SCORE = ? "
				+ " WHERE ID = ? AND MENU_NAME = ? AND ORDER_DATE = TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') ";
		System.out.println(" new review: " + dto);
		Connection conn = null;
		PreparedStatement psmt = null;
		// ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getReview());
			psmt.setInt(2, dto.getScore());
			psmt.setString(3, dto.getUserId());
			psmt.setString(4, dto.getMenuName());
			psmt.setString(5, dto.getOrderDate());

			int count = psmt.executeUpdate();

			if (count > 0) {
				System.out.println(dto.getUserId() + "님의 " + dto.getMenuName() + "에 대한 리뷰가 업데이트 되었습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	public void select_WritableReview(ReviewDto dto, Socket sock) {
		String sql = " SELECT TO_CHAR(ORDER_DATE,'YYYY-MM-DD HH24:MI:SS') FROM ORDER_DETAIL "
				+ " WHERE ID = ? AND MENU_NAME = ? AND REVIEW is null AND (SYSDATE - ORDER_DATE) <= 2";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ReviewDto resultDto = null;// 보낼 Dto

		try {

			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getMenuName());
			rs = psmt.executeQuery();

			if (rs.next()) {
				resultDto = dto;
				resultDto.setOrderDate(rs.getString(1)); // 날짜 정보를 담아 보낸다. -- 업데이트할 Dto를 특정하기 위해서
				System.out.println("작성 가능한 리뷰가 있습니다.");
			} else {
				System.out.println("작성 가능한 리뷰가 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		SocketWriter.Write(sock, resultDto);
	}

	public void select(ReviewDto dto, Socket sock) {
		String sql = "SELECT ID, MENU_NAME, TO_CHAR(ORDER_DATE, 'YYYY-MM-DD HH24:MI:SS'), REVIEW, SCORE "
				+ " FROM ORDER_DETAIL " + " WHERE MENU_NAME = ? AND REVIEW IS NOT NULL";

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

	public void select_byUserId(ReviewDto dto, Socket sock) {
		String id = dto.getUserId();
		String sql = "SELECT MENU_NAME, REVIEW,  ID, TO_CHAR(ORDER_DATE, 'YYYY-MM-DD HH24:MI:SS'), SCORE " + " FROM ORDER_DETAIL" + " WHERE ID = ? ";

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
