package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.MenuDto;
import dto.MenuShowDto;

public class MenuDao {
	
	List<MenuDto> memList = new ArrayList<MenuDto>();
	
	public MenuDao() {
		
	}
	
	public void insert() {
		
	}
	
public List<MenuShowDto> getShowMenu(){
		
		List<MenuShowDto> showDtoList = new ArrayList<>();
		
		//comm연결	
		s.comm.getShowMenu();
		
		
		
		return showDtoList;
		
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
