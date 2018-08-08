package client.controller;

import java.util.List;

import client.dao.MenuShowDao;
import client.dao.OrderDao;
import client.dto.MenuShowDto;

public class OrderController {
	
	OrderDao ordDao = new OrderDao();
	MenuShowDao showDao = new MenuShowDao();
	
	public void insert() {
		ordDao.insert();
	}
	
	public void select() {
		ordDao.select();
	}
	
	public List<MenuShowDto> getShowMenu (){
		return showDao.getShowMenu();
	}
	
	public void delete() {
		ordDao.delete();
	}
	
	public void update() {
		ordDao.update();
	}

}
