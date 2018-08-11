package client.controller;

import javax.swing.JFrame;

import client.dao.MenuDao;
import client.view.manager.AddMenuView;
import client.view.manager.MenuManageView;
import client.view.manager.UpdateMenuView;
import dto.MenuShowDto;
 
public class MenuController {

	private MenuDao menDao = new MenuDao();
	
	private MenuManageView menManView;
	private UpdateMenuView updManView;
	private AddMenuView addManView;

	public MenuDao getMenDao() {
		return menDao;
	}

	public void insert(MenuShowDto dto, String imgPath) {
		menDao.insert(dto, imgPath);
	}

	public void initMenuList() {
		menDao.initList();
	}

	public void delete() {
		menDao.delete();
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

	public void update(MenuShowDto menu, int newPrice, String description) {
		menDao.update(menu, newPrice, description);
	}

	public void menuManageView(JFrame currentView) {
		currentView.setVisible(false);
		// 메뉴관리 창을 보여준다.
		if (menManView == null) { // 없을 땐
			menManView = new MenuManageView(); // 만들고
		} else { // 있을 땐
			menManView.setVisible(true); // 보여만 준다.
		}
	}

	public void addMenuView() {
		menManView.setVisible(false);
		// 메뉴관리 창을 보여준다.
		if (addManView == null) { // 없을 땐
			addManView = new AddMenuView(); // 만들고
		} else { // 있을 땐
			addManView.setVisible(true); // 보여만 준다.
		}
	}

	public void updateMenuView() {
		menManView.setVisible(false);
		// 메뉴관리 창을 보여준다.
		if (updManView == null) { // 없을 땐
			updManView = new UpdateMenuView(); // 만들고
		} else { // 있을 땐
			updManView.setVisible(true); // 보여만 준다.
		}
	}

}
