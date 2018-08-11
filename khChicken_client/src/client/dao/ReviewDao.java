package client.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dto.ReviewDto;

public class ReviewDao {
	
	List<ReviewDto> rList = new ArrayList<ReviewDto>();
	Socket sock;
	
	public ReviewDao() {
	}
	
	public void SockDao(Socket sock) {
		this.sock = sock;
		
	}
	
	public void insert(ReviewDto dto) {
		try {
			
			
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeInt(0);
			oos.flush();
			
			
			System.out.println("ss");
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(dto);
			oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
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
			
			ObjectInputStream ois;
			ois = new ObjectInputStream(sock.getInputStream());
			list =(List<ReviewDto>)ois.readObject();
		
		
		
		
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
