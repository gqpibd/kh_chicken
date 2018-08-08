package client.controller;

import client.dao.MenuDao;
import dto.MenuShowDto;

public class MenuController {
	
	private MenuDao menDao = new MenuDao();
	
	public MenuDao getMenDao() {
		return menDao;
	}
	
	public void insert(MenuShowDto dto, String imgPath) {
		menDao.insert(dto,imgPath);
	}
	
	public void select() {
		menDao.initList();
	}
	
	public void delete() {
		menDao.delete();
	}
	
	public void update() {
		menDao.update();
	}

}
