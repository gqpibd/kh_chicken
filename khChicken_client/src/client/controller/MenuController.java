package client.controller;

import java.util.List;

import client.dao.MenuDao;
import dto.MenuShowDto;

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
	
	public List<MenuShowDto> getShowMenu (){
		return menDao.getShowMenu();
	}
	
	public void update() {
		menDao.update();
	}

}
