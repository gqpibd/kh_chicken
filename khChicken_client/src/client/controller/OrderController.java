package client.controller;

import client.dao.OrderDao;

public class OrderController {
	
	OrderDao ordDao = new OrderDao();
	
	public void insert() {
		ordDao.insert();
	}
	
	public void select() {
		ordDao.select();
	}
	
	public void delete() {
		ordDao.delete();
	}
	
	public void update() {
		ordDao.update();
	}

}
