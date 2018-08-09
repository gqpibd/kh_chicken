package server.dao;

// CREATE TABLE MENU(
//	    MENU_NAME VARCHAR2(30) PRIMARY KEY,
//	    PRICE NUMBER(5) NOT NULL,
//	    MENU_TYPE VARCHAR2(10) NOT NULL,
//	    DESCRIPTION VARCHAR2(1000) NOT NULL,
//	    AVG_RATE NUMBER(2)
//	);


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

import javax.imageio.ImageIO;

import dto.MenuDto;
import dto.MenuShowDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;

public class MenuDao {
	private final String PATH = "d:/share/images/";

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

	} // 다슬, 도현

	public void updatePrice(MenuShowDto dto) {
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
		System.out.println(dto.getMenu_name() +  "의 가격이 변경 되었습니다");
	} // 도현

	public void delete(MenuShowDto dto) {
		String sql = "DELETE FROM MENU WHERE MENU_NAME = ?";
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMenu_name());

			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		System.out.println(dto.getMenu_name() +  "이(가) 메뉴 테이블에서 삭제되었습니다");
	} // 도현

	public void execute(int number, MenuShowDto dto, Socket sock) {
		switch (number) {
		case 0: // insert
			insert(dto);
			receiveAndSaveImage(dto.getMenu_name(), sock);
			break;
		case 1: // select
			selectAll(sock);
			break;
		case 2: // delete
			delete(dto);
		case 3: // update			
			updatePrice(dto);
			break;
		}
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

	public void receiveAndSaveImage(String name, Socket sock) {
		System.out.println("receiveAndSaveImage");
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedImage im = null;// = ImageIO.read(ois);

			im = ImageIO.read(ois);
			System.out.println("받음");
			if (im == null) {
				System.out.println("이미지 파일을 받지 못했습니다.");
				return;
			} else {
				System.out.println(im.toString());
				ImageIO.write(im, "jpg", new File(PATH + name.replace(" ", "_") + ".jpg"));
			}
		} catch (SocketException e) {
			System.out.println("커넥션 리셋됨");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
