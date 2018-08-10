package client.controller;

import java.net.Socket;

import client.dao.ReviewDao;

public class ReviewController {
	
	ReviewDao revDao = new ReviewDao();
	
	
	public void socketDao(Socket sock) {
		revDao.SockReview(sock);
	}
	
	public void insert() {
		revDao.insert();
	}
	
	public void select() {
		revDao.select();
	}
	
	public void delete() {
		revDao.delete();
	}
	
	public void update() {
		revDao.update();
	}
	

}
