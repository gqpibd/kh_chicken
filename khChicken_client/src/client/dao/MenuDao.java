package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.MenuDto;
import dto.OrderedMenuDto;



public class MenuDao {
	
	List<MenuDto> memList = new ArrayList<MenuDto>();
	
	public MenuDao() {
	}

	
	public List<MenuDto> insert() {
		MenuDto nDto = new MenuDto("후라이드 치킨", 12000);
		memList.add(nDto);
		/*OrderedMenuDto oDto = new OrderedMenuDto(1, 1, 20000);
		oList.add(oDto);*/
		
		return memList;
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
