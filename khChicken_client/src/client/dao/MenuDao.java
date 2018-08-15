package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.MenuShowDto;

public class MenuDao {
	public static final String FOLDER_PATH = "images\\";
	//public static final String FOLDER_PATH = "\\\\192.168.30.35\\share\\images\\";
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
		menList = (ArrayList<MenuShowDto>) s.getComm().receiveObject();

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

	public void delete(MenuShowDto menu) {
		menList.remove(menu);

		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.DELETE, menu);

	}

	public void update(MenuShowDto menu) {
		// socket으로 전달
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.UPDATE, menu);
	}

	public void updateImage(MenuShowDto menu, String newImgPath) {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(4, menu);
		s.getComm().sendImage(newImgPath);
	}

	public void sco_Update(MenuShowDto dto) {

		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(5, dto);
	}

	public List<MenuShowDto> get_List() {
		return menList;
	}
}
