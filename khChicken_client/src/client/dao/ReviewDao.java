package client.dao;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.dto.ReviewDto;

public class ReviewDao {
	
	List<ReviewDto> rList = new ArrayList<ReviewDto>();
	Socket sock
	
	public ReviewDao() {
	}
	
	public void SockReview(Socket sock) {
		this.sock = sock;
	}
	
	public void insert() {
		
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
