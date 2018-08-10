package client.dao;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.dto.ReviewDto;

public class ReviewDao {
	
	List<ReviewDto> rList = new ArrayList<ReviewDto>();
	Socket sock;
	
	public ReviewDao() {
	}
	
	public void SockDao(Socket sock) {
		this.sock = sock;
		
	}
	
	public void insert() {
		
	}
	
	public List<ReviewDto> select() {
		
		List<ReviewDto> list = new ArrayList<>();
		ReviewDto dto = new ReviewDto();
		ObjectOutputStream oos;
		try {
			oos= new ObjectOutputStream(sock.getOutputStream());
			oos.writeInt(1);
			oos.flush();
			oos= new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(dto);
			oos.flush();
			System.out.println(11);
			
			ObjectInputStream ois;
			ois = new ObjectInputStream(sock.getInputStream());
			System.out.println(ois);
			list =(List<ReviewDto>)ois.readObject();
			System.out.println(22);
			
		
		
		
		
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
