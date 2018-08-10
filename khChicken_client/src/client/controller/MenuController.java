package client.controller;

import java.util.List;

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
	
//	public List<MenuShowDto> getShowMenu (){
//		return menDao.getShowMenu();
//	}
	
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
