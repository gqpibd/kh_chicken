package server.db;
import java.sql.Connection;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
	
	public static void close(PreparedStatement psmt, Connection conn, ResultSet rs) {
		
		try {
			if(psmt != null) {
				psmt.close();
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
	
	public static void close(Statement stmt, Connection conn, ResultSet rs) {
		
		try {
			if(stmt != null) {
				stmt.close();
>>>>>>> refs/remotes/origin/도현+다슬+승지
			}
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {
				rs.close();
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}

}
