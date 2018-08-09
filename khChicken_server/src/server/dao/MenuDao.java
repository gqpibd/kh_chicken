package server.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import server.communicator.SocketWriter;
import server.db.DBConnection;
import dto.MenuShowDto;

public class MenuDao {
	
	public MenuDao() {
	}

	
	public void insert() {
		
	}
	
	public void select() {
		
	}
	

	
	public void execute(int number, MenuShowDto dto, Socket sock) {

		selectAll(sock);

	}

	private void selectAll(Socket sock) {
		ArrayList<MenuShowDto> list = new ArrayList<>();
		String sql = "SELECT MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION, AVG_RATE FROM MENU";
		Connection conn = null;
		PreparedStatement psmt;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MenuShowDto dto = new MenuShowDto();
				dto.setMenu_name(rs.getString(1));
				dto.setPrice(rs.getInt(2));
				dto.setType(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setavgScore(rs.getDouble(5));
				// System.out.println(dto);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		SocketWriter.WriteAll(sock, list);
	}

	/*
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
				
				MenuShowDto showDto = new MenuShowDto(rs.getDouble("AVG_RATE"), );
				showMenuList.add(showDto);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		// send 

	}
	*/
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
