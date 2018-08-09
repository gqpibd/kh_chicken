package client.dao;

import java.awt.MenuShortcut;
import java.util.ArrayList;
import java.util.List;

import client.singleton.Singleton;
import dto.MenuDto;
import dto.MenuShowDto;

public class MenuDao {
	
	List<MenuDto> memList = new ArrayList<MenuDto>();
	Singleton s = Singleton.getInstance();
	
	public MenuDao() {
		
	}
	
	public void insert() {
		
	}
	
public List<MenuShowDto> getShowMenu(){
		
		//서버에서 받은 dto저장할 리스트
		List<MenuShowDto> showDtoList = new ArrayList<>();
		
		//comm연결	
		s.comm.makeConnection();
		
		showDtoList = s.comm.getShowMenu();
		

		return showDtoList;
		
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
