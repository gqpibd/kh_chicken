package server.dao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.db.DBConnection;
import server.dto.MenuShowDto;

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
		String sql = " SELECT AVG_RATE, MENU_TYPE, PRICE, MENU_NAME  FROM MENU ";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		try {
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(sql);	
		rs = psmt.executeQuery(sql);
			
			if(rs.next()) {
				
				MenuShowDto showDto = new MenuShowDto(null, rs.getDouble("AVG_RATE"));
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// send 
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			Object obj = showMenuList;
			oos.writeObject(obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		sleep(100);

		
		
	}
	
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
