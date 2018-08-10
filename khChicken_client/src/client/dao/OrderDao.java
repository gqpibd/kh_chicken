package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.OrderedMenuDto;



public class OrderDao {
	
	List<OrderedMenuDto> oList = new ArrayList<OrderedMenuDto>();
	//메뉴를 여러개 시킬 수 있어.
	
	public OrderDao() {
	}
	
	public List<OrderedMenuDto> insert() {
		OrderedMenuDto oDto = new OrderedMenuDto(1, 1, 20000);
		oList.add(oDto);
		
		return oList;
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
