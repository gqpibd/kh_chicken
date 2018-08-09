package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.db.DBClose;
import server.db.DBConnection;
import server.dto.MemberDto;

public class MemberDao {
	
	public MemberDao() {
	}
	
	public void insert(client.dto.MemberDto dto) {
		
		
		 String id = dto.getId();
		 String pw = dto.getPw();
		 String name = dto.getName();
		 int coupon = dto.getCoupon();
		 int auth = dto.getAuth();
		 String address = dto.getAddress();
		 String phone = dto.getPhone();
		
		
		String sql = "INSERT INTO MEMBER (name, pw, id, coupon, auth, adr, phone)"
				+ " VALUES ( '"+ id + "', '" + pw + "', '"
							   + name + "', " + coupon + ", "
							   + auth + ", '"+ address +"', '" + phone + "' );";
		
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{			
			DBClose.close(psmt, conn, null);			
		}
		
		
	}
	
	public boolean select(client.dto.MemberDto dto) {
String id = dto.getId();
		
		String sql = "SELECT ID"
				+ "	FROM MEMBER"
				+ " WHERE ID = '"+id+"' ";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt  = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			//rs.updateString(1, id);
			
			dto.setId(null);
			while(rs.next()) {
				 dto.setId(rs.getString(1));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		if(dto.getId() == null) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public void update() {
		String sql = "";
		
	}
	
	public void delete() {
		String sql = "";
		
	}
	
	public boolean select_loging(client.dto.MemberDto dto) {
		   String id = dto.getId();
		   String pw = dto.getPw();
		   String sql = "SELECT ID ,PW "
		   		+ " FROM MEMBER "
		   		+ " WHERE ID = '"+id+ "' AND PW = '"+pw+"' ";
		   
			System.out.println(sql);
			Connection conn = null;
			PreparedStatement psmt  = null;
			ResultSet rs = null;
			
			dto.setId(null);
			dto.setPw(null);
			
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				
				
				
				while(rs.next()) {
					 System.out.println("11");
					 dto.setId(rs.getString(1));
					 dto.setPw(rs.getString(2));
					 System.out.println(dto.getId());
				}
				
			}catch(SQLException e ) {	
				e.printStackTrace();
			}finally {
				DBClose.close(psmt, conn, rs);
			}
		   if(dto.getId() == null && dto.getPw() == null) {
			   return false;
		   }else {
			   return true;
		   }
	   
	   }

	
	

}
