package client.controller;

import java.util.List;

import client.dao.OrderDao;
import client.dto.OrderedMenuDto;

public class OrderController {
	
	OrderDao ordDao = new OrderDao();
	
	public void insert() {
		ordDao.insert();
	}
	
	public List<OrderedMenuDto> select() {
		return ordDao.select();
	}
	
	public void delete() {
		ordDao.delete();
	}
	
	public void update() {
		ordDao.update();
	}

}
