package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.MenuDto;
import dto.MenuShowDto;

public class MenuDao {
	// public static final String FOLDER_PATH = "\\\\127.0.0.1\\images\\";
	public static final String FOLDER_PATH = "images\\";
	private List<MenuShowDto> menList = new ArrayList<MenuShowDto>();

	public MenuDao() {
	}

	public void insert(MenuShowDto dto, String imgFilePath) {
		menList.add(dto);
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.INSERT, dto);
		s.getComm().sendImage(imgFilePath);
	}

	public void initList() { // 서버에서 다 읽어오기
		Singleton s = Singleton.getInstance();
		// s.getComm().makeConnection();
		MenuShowDto dto = new MenuShowDto();

		menList.clear();

		s.getComm().SendMessage(Communicator.SELECT, dto);
		ArrayList<Object> resultList = s.getComm().receiveMessage();

		for (int i = 0; i < resultList.size(); i++) {
			menList.add((MenuShowDto) resultList.get(i));
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

	public MenuShowDto getMenuByName(String name) {
		for (MenuShowDto dto : menList) {
			if (dto.getMenu_name().equals(name)) {
				return dto;
			}
		}
		return null;
	}

	public void delete(MenuDto menu) {
		menList.remove(menu);

		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.DELETE, menu);

	}

	public void updatePrice(MenuDto menu, int newPrice) {
		menu.setPrice(newPrice); // 리스트에서 업데이트

		// socket으로 전달
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.UPDATE, menu);
	}

	public void updateImage(MenuShowDto menu, String imagePath) {

		// socket으로 전달

	}

	// public List<MenuShowDto> getShowMenu(){
	// //서버에서 받은 dto저장할 리스트
	// List<MenuShowDto> showDtoList = new ArrayList<>();
	//
	// showDtoList = Singleton.getInstance().getComm().getShowMenu();
	// return showDtoList;
	//
	// }

}
