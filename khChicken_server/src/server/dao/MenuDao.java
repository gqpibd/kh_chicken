package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.MenuDto;
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

	public void update() {

	} // 도현

	public void delete() {

	} // 도현

}
