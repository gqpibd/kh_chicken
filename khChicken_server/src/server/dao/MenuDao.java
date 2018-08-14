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

import dto.MenuShowDto;
import server.communicator.SocketWriter;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class MenuDao {
	private final String PATH = "d:/share/images/";

	public MenuDao() {
	}

	public void execute(int number, MenuShowDto dto, Socket sock) {
		switch (number) {
		case Singleton.INSERT: // 메뉴 추가 - 이도현
			insert(dto);
			receiveAndSaveImage(dto.getMenu_name(), sock);
			System.out.println(dto.getMenu_name() + "를 메뉴 테이블에 추가하였습니다");
			break;
		case Singleton.SELECT: // 메뉴 모두 불러오기 - 김다슬
			select(sock);
			System.out.println("메뉴 목록을 불러왔습니다");
			break;
		case Singleton.DELETE: // 메뉴 삭제 - 이도현
			delete(dto);
			System.out.println(dto.getMenu_name() + "를 테이블에서 삭제하였습니다");
			break;
		case Singleton.UPDATE: // 메뉴 수정 - 이도현
			update(dto);
			System.out.println(dto.getMenu_name() + "의 정보를 수정하였습니다.");
			break;
		case 4: // 이미지 수정 - 이도현
			receiveAndSaveImage(dto.getMenu_name(), sock);
			System.out.println(dto.getMenu_name() + "의 이미지를 수정하였습니다.");
			break;
		// case 5: // 별점 업데이트 - 윤상필
		// Sco_Update(dto);
		// break;
		}
	}

	public void insert(MenuShowDto dto) {
		String sql = "INSERT INTO MENU (MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION, AVG_RATE) VALUES ( ?, ?, ?, ?, ? )";
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMenu_name());
			psmt.setInt(2, dto.getPrice());
			psmt.setString(3, dto.getType());
			psmt.setString(4, dto.getDescription());
			psmt.setDouble(5, dto.getavgScore());
			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

	}

	public void update(MenuShowDto dto) {
		String sql = "UPDATE MENU SET PRICE = ?, DESCRIPTION = ?, AVG_RATE = ? WHERE MENU_NAME = ?";
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;

		try {
			System.out.println(dto);
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getPrice());
			psmt.setString(2, dto.getDescription());
			psmt.setDouble(3, dto.getavgScore());
			psmt.setString(4, dto.getMenu_name());

			int count = psmt.executeUpdate();
			if (count > 0) {
				System.out.println("정상적으로 업테이트 되었습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

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
	}

	private void select(Socket sock) {
		ArrayList<MenuShowDto> list = new ArrayList<>();
		String sql = "SELECT MENU_NAME, PRICE, MENU_TYPE, DESCRIPTION, AVG_RATE FROM MENU ORDER BY MENU_TYPE";
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

		SocketWriter.Write(sock, list);
	}

	public void receiveAndSaveImage(String name, Socket sock) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedImage im = ImageIO.read(ois);

			if (im == null) {
				System.out.println("이미지 파일을 받지 못했습니다");
				return;
			} else {
				ImageIO.write(im, "jpg", new File(PATH + name.replace(" ", "_") + ".jpg"));
				System.out.println("이미지 파일을 저장했습니다");
			}
		} catch (SocketException e) {
			System.out.println("커넥션 리셋됨");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void Sco_Update(MenuShowDto dto) {
	// /*
	// * String sql = " UPDATE ORDER_DETAIL " + " SET REVIEW = ?, SCORE = ? " +
	// * " WHERE ID = ? AND MENU_NAME = ? AND REVIEW is null AND (TO_DATE(sysdate,
	// 'yyyy/mm/dd') - TO_DATE(ORDER_DATE, 'yyyy/mm/dd')) <= '2'"
	// * ;
	// */
	// String sql = " UPDATE MENU " + " SET AVG_RATE = ? " + " WHERE MENU_NAME = ?
	// ";
	//
	// System.out.println(dto.getMenu_name());
	// Connection conn = null;
	// PreparedStatement psmt = null;
	// try {
	// conn = DBConnection.getConnection();
	// psmt = conn.prepareStatement(sql);
	// psmt.setDouble(1, dto.getavgScore());
	// psmt.setString(2, dto.getMenu_name());
	// psmt.executeQuery();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// DBClose.close(psmt, conn, null);
	//
	// }
	// }
}
