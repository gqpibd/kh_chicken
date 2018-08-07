package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.MenuDto;
import dto.MenuShowDto;
import server.db.DBClose;
import server.db.DBConnection;

public class MenuDao {

	public MenuDao() {
	}

	public void insert(MenuDto dto) {
		String sql = "INSERT INTO MENU VALUES ( ?, ? )";
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMenu_name());
			psmt.setInt(2, dto.getPrice());

			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

	}// 도현

	public void select() {

	} // 다슬

	public void update(MenuShowDto dto) {
		String sql = "UPDATE MENU SET PRICE = ? WHERE MENU_NAME = ?";
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getPrice());
			psmt.setString(2, dto.getMenu_name());

			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	} // 도현

	public void delete() {

	} // 도현

	public void execute(int number, MenuShowDto dto) {
		switch (number) {
		case 0: // insert
		case 1: // select
		case 2: // delete
		case 3: // update
			update(dto);
			break;
		}
	}

}
