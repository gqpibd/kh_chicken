package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.dto.MenuShowDto;
import client.singleton.Singleton;

public class MenuShowDao {
	
	Singleton s = Singleton.getInstance();
	
	public MenuShowDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<MenuShowDto> getShowMenu(){
		
		List<MenuShowDto> showDtoList = new ArrayList<>();
		
		//comm연결	
		s.comm.getShowMenu();
		
		
		
		return showDtoList;
		
	}
}
