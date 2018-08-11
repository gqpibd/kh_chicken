package client.controller;

import java.net.Socket;
import java.util.List;

import client.dao.ReviewDao;
import dto.ReviewDto;

public class ReviewController {
	
	ReviewDao revDao = new ReviewDao();
	
	
	public void socketDao(Socket sock) {
		revDao.SockDao(sock);
	}
	
	public void insert(ReviewDto dto) {
		revDao.insert(dto);
	}
	
	public List<ReviewDto> select() {
		return revDao.select();
	}
	
	public void delete() {
		revDao.delete();
	}
	
	public void update() {
		revDao.update();
	}
	

}
