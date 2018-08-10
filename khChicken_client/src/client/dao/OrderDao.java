package client.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.OrderedMenuDto;



public class OrderDao {
	
	List<OrderedMenuDto> oList = new ArrayList<OrderedMenuDto>();
	//메뉴를 여러개 시킬 수 있어.
	
	public OrderDao() {
	}
	
	public List<OrderedMenuDto> insert() {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YY/MM/dd hh:mm:ss");
		//Date time = date.format(today);
		OrderedMenuDto oDto = new OrderedMenuDto(today, "test1", "main", "후라이드치킨", 1, 1, 15000);
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
