package server.dao;

import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.dto.MenuShowDto;

public class MenuDao {
	
	public MenuDao() {
	}

	
	public void insert() {
		
	}
	
	public void select() {
		
	}
	
public void getShowMenu() {
		
		//리턴보낼 dto 리스트
		List<MenuShowDto> showMenuList = new ArrayList<>();
		

		//menu이름 별로 별점 가져옴 
		String sql = " SELECT  ";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		try {
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(sql);	
		rs = psmt.executeQuery(sql);
			
			if(rs.next()) {
				
				MenuShowDto showDto = new MenuShowDto(null, rs.getDouble("SCORE"));
				showMenuList.add(showDto);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// send (받는건 번호+dto 지만 보내는건 한번만 해도됨)
		
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		Object obj = showMenuList;
		oos.writeObject(obj);
		sleep(100);

		
		
	}
	
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
