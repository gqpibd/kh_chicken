package client.controller;

import client.dao.MenuDao;

public class MenuController {
	
	MenuDao menDao = new MenuDao();
	
	public void insert() {
		menDao.insert();
	}
	
	public void select() {
		menDao.select();
	}
	
	public void delete() {
		menDao.delete();
	}
	
	public void update() {
		menDao.update();
	}

}
