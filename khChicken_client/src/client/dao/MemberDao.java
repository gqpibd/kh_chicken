package client.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.corba.se.pept.encoding.OutputObject;

import client.dto.MemberDto;
import client.singleton.Singleton;
import sun.misc.Signal;

public class MemberDao {
	
	
	
	
	private String id;
	private String pw;
	private String name;
	private int coupon;
	private int auth;
	private String address;
	private String phone;
	
	List<MemberDto> mList = new ArrayList<MemberDto>();
	
	Socket sock;
	
	public MemberDao() {
	}
	
	public void MemberDao(Socket sock) {
		this.sock=sock;
	}
	
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
	
	
	}
	
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
	
	public void update() {
		
	}
	
	public void delete() {
		
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
