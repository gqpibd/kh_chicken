package client.dao;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.controller.MenuController;
import client.singleton.Singleton;
import dto.MenuDto;
import dto.MenuShowDto;

public class MenuDao {

	List<MenuShowDto> menList = new ArrayList<MenuShowDto>();

	public MenuDao() {
		// menList.add(new MenuShowDto("후라이드 치킨", 11000,"fried.jpg", 9.0));
		// menList.add(new MenuShowDto("양념 치킨", 12000, "seasoning.png", 8));
		// menList.add(new MenuShowDto("반반 치킨", 12000, "half.png", 7.6));
		// menList.add(new MenuShowDto("콜라", 3000, "cola.jpg", 10));
		// menList.add(new MenuShowDto("사이다", 3000, "sprite.jpg", 10));
	}

	public void insert(MenuShowDto dto,String imgFilePath) {
		Singleton s = Singleton.getInstance();
		//s.getComm().makeConnection();

		s.getComm().SendMessage(Communicator.INSERT, dto);
		s.getComm().sendImage(imgFilePath);
		
	}

	public void initList() { // 서버에서 다 읽어오기
		Singleton s = Singleton.getInstance();
		s.getComm().makeConnection();
		// MenuController ctrl = s.getMenuCtrl();
		MenuShowDto dto = new MenuShowDto();

		menList.clear();

		s.getComm().SendMessage(Communicator.SELECT, dto);
		ArrayList<Object> resultList = s.getComm().receiveMessage();
	 
		for (int i = 0; i < resultList.size(); i++) {
			menList.add((MenuShowDto) resultList.get(i));
			//menList.get(i).setImg(img);
			System.out.println(resultList.get(i));
		}
	}

	public void update() {

	}

	public void delete() {

	}

	public int getSize() {
		return menList.size();
	}

	public MenuShowDto get(int i) {
		return menList.get(i);
	}

	public MenuDto getMenuByName(String name) {
		for (MenuDto dto : menList) {
			if (dto.getMenu_name().equals(name)) {
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
		
		// socket으로 전달

	}

}
