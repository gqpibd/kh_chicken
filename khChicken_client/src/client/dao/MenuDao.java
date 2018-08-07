package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.dto.MenuDto;
import client.dto.MenuShowDto;

public class MenuDao {

	List<MenuDto> menList = new ArrayList<MenuDto>();

	public MenuDao() {
		menList.add(new MenuShowDto("후라이드 치킨", 11000,"fried.jpg", 9.0));
		menList.add(new MenuShowDto("양념 치킨", 12000, "seasoning.png", 8));
		menList.add(new MenuShowDto("반반 치킨", 12000, "half.png", 7.6));
		menList.add(new MenuShowDto("콜라", 3000, "cola.jpg", 10));
		menList.add(new MenuShowDto("사이다", 3000, "sprite.jpg", 10));
	}

	public void insert() {

	}

	public void select() {

	}

	public void update() {

	}

	public void delete() {

	}

	public int getSize() {
		return menList.size();
	}

	public MenuDto get(int i) {
		return menList.get(i);
	}
	
	public MenuDto getMenuByName(String name) {
		for(MenuDto dto : menList) {
			if(dto.getMenu_name().equals(name)) {
				return dto;
			}
		}
		return null;
	}

	public void delete(MenuDto menu) {
		menList.remove(menu);
		// socket으로 전달
		
	}

	public void updatePrice(MenuDto menu, int newPrice) {
		menu.setPrice(newPrice);
		// socket으로 전달
		
	}

	public void updateImage(MenuShowDto menu, String image) {
		menu.setImage(image);
		// socket으로 전달
		
	}

}
