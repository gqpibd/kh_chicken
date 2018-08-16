package client.service;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.service.interfaces.MenuServiceImpl;
import client.singleton.Singleton;
import dto.MenuShowDto;

public class MenuService implements MenuServiceImpl{
	//public static final String FOLDER_PATH = "\\\\127.0.0.1\\images\\";
	public static final String FOLDER_PATH = "\\\\192.168.30.35\\share\\images\\";
	private List<MenuShowDto> menList = new ArrayList<MenuShowDto>();

	public MenuService() {
	}

	public void insert(MenuShowDto dto, String imgFilePath) { // 메뉴 추가
		menList.add(dto);
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.INSERT, dto);
		s.getComm().sendImage(imgFilePath);
	}
	
	public void delete(MenuShowDto menu) { // 메뉴 삭제
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

	public void initList() { // 서버에서 다 읽어오기
		Singleton s = Singleton.getInstance();
		MenuShowDto dto = new MenuShowDto();
		menList.clear();
		s.getComm().SendMessage(Communicator.SELECT, dto);
		menList = (ArrayList<MenuShowDto>) s.getComm().receiveObject();

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

	public List<MenuShowDto> get_List() {
		return menList;
	}
}
