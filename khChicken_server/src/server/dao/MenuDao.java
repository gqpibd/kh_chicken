package server.dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.db.DBConnection;
import dto.MenuShowDto;

public class MenuDao {
	
	public MenuDao() {
	}

	
	public void insert() {
		
	}
	
	public void select() {
		
	}
	
public void getShowMenu(Socket sock) {
		
		//리턴보낼 dto 리스트
		List<MenuShowDto> showMenuList = new ArrayList<>();
		

		//menu이름 별로 별점 가져옴 
		String sql = " SELECT * FROM MENU ";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		try {
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(sql);	
		rs = psmt.executeQuery(sql);
			
			if(rs.next()) {
				
				MenuShowDto showDto = new MenuShowDto(
																											rs.getString("MENU_NAME"), 
																											rs.getInt("PRICE"), 
																											rs.getString("MENU_TYPE"), 
																											rs.getString("DESCRIPTION"), 
																											rs.getDouble("AVG_RATE"));
				showMenuList.add(showDto);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// send 

		
		
		
	}
	
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
