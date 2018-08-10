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
	
	public void initMenuList() {
		menDao.initList();
	}
	
	public void delete() {
		menDao.delete();
	}
	
	public void update() {
		menDao.update();
	}

	public MenuShowDto getMenuDto(String name) {
		return menDao.getMenuByName(name);
		
	}

	public int getSize() {
		return menDao.getSize();
	}

	public MenuShowDto get(int i) {
		return menDao.get(i);
	}

}
