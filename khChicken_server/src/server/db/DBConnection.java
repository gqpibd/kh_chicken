package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// connection을 만들 때 사용하는 static class

public class DBConnection {
	public static void initConnection() {
		try {
			// 오라클 들어가기 전에 항상 확인해 줘야함
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Class.forName(클래스이름) : 클래스 찾는 것
			System.out.println("Driver Loading Success!!");
		} catch (ClassNotFoundException e) { // Class.forName()가 클래스를 못 찾는 경우
			e.printStackTrace();
		}		
	}
	public static Connection getConnection() {
		Connection conn = null;

		try {
			// DriverManager.getConnection(db 주소, 계정 id,계정 pw) : DB 계정에 연결을 시도한다.
			// 192.168.30.16 승지
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.30.35:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!!");
		} catch (SQLException e) {
			System.out.println("DB Connection Failed!!");
			//e.printStackTrace();
		}
		return conn;
	}
}