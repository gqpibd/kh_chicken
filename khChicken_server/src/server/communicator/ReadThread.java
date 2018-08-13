package server.communicator;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BestSaleMenuDto;
import dto.MemberDto;
import dto.MenuShowDto;
import dto.OrderedMenuDto;
import dto.ReviewDto;
import server.db.DBClose;
import server.db.DBConnection;
import server.singleton.Singleton;

public class ReadThread extends Thread {
	Socket sock;

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		super.run();
		Singleton s = Singleton.getInstance();
		ObjectInputStream ois = null;

		try {
			while (true) {
				ois = new ObjectInputStream(sock.getInputStream()); // dto받기

				int number = ois.readInt();
				Object obj = ois.readObject();

				if (obj instanceof MemberDto) { // 로그인, 회원가입
					s.getMemCtrl().execute(number, (MemberDto) obj, sock);
				} else if (obj instanceof MenuShowDto) { // 메뉴 보여주기, 추가 삭제
					s.getMenuCtrl().execute(number, (MenuShowDto) obj, sock);
				} else if ((obj instanceof OrderedMenuDto)) { // 주문하기, 매출관리
					s.getOrderCtrl().execute(number, (OrderedMenuDto) obj, sock);
				} else if (obj instanceof ReviewDto) { // 리뷰보기
					s.getRevCtrl().execute(number, (ReviewDto) obj, sock);
				} else if (obj instanceof String) {

					String sql = "SELECT DISTINCT SIDO, SIGUNGU, LOAD, EUBMEONDONG" + " FROM LOADNAME_ADD "
							+ " WHERE LOAD = ? ";

					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					List<String> list = new ArrayList<>();
					try {

						conn = DBConnection.getConnection();
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, obj.toString());
						rs = psmt.executeQuery();

						while (rs.next()) {
							if (rs.getString(4) == null) {
								list.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
							} else {
								list.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " ("
										+ rs.getString(4) + ")");
							}
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DBClose.close(psmt, conn, rs);
					}

					SocketWriter.Write(sock, list);

				}
				sleep(100);
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("소켓이 닫혔습니다");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
