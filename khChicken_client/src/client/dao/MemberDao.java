package client.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;

public class MemberDao {
<<<<<<< HEAD
	
	
	
	
	private String id;
	private String pw;
	private String name;
	private int coupon;
	private int auth;
	private String address;
	private String phone;
	
=======

>>>>>>> refs/remotes/origin/도현+다슬+승지
	List<MemberDto> mList = new ArrayList<MemberDto>();
<<<<<<< HEAD
	
	Socket sock;
	
=======
	MemberDto CurrentUser;

>>>>>>> refs/remotes/origin/도현+다슬+승지
	public MemberDao() {
	}
<<<<<<< HEAD
	
	public void MemberDao(Socket sock) {
		this.sock=sock;
=======

	public void insert() {

>>>>>>> refs/remotes/origin/도현+다슬+승지
	}
<<<<<<< HEAD
	
	public void insert(MemberDto dto) {
  
		
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeInt(0);
			oos.flush();
			System.out.println(dto.getId() + "다오");
			 oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(dto);
			oos.flush();
			
			
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
=======

	public void select() {

>>>>>>> refs/remotes/origin/도현+다슬+승지
	}
<<<<<<< HEAD
	
	public boolean select(MemberDto dto) {
		
		boolean join = false;
		
		try {
	
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeInt(1);
			oos.flush();
		
		
		System.out.println(dto.getId() + "다오");
			oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject(dto);
		oos.flush();
		
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
				join = (boolean)ois.readObject();
				
			} catch (ClassNotFoundException e) {
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return join;
}
	
=======

>>>>>>> refs/remotes/origin/도현+다슬+승지
	public void update() {

	}

	public void delete() {

	}

	public String getLoginId() { // 로그인 아이디 가져오기
		if (CurrentUser == null) {
			return null;
		} else {
			return CurrentUser.getId();
		}
	}

	public int getAuth() {
		int auth = CurrentUser.getAuth();
		return auth;

	}
	
	public boolean select_loging(MemberDto dto) {
	      
	      boolean join = false;
	      
	      try {
	          PrintWriter pw;
	          
	          	ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
	          	oos.writeInt(4);
	          	oos.flush();
	      
	      
	      System.out.println(dto.getId() + "다오");
	      oos = new ObjectOutputStream(sock.getOutputStream());
	      oos.writeObject(dto);
	      oos.flush();
	      
	      ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
	      join =(boolean) ois.readObject();
	      	System.out.println(join);
	      }catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return join;
	}

}
