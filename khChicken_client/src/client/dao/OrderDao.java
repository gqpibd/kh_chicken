package client.dao;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import client.dto.OrderedMenuDto;

public class OrderDao {
	
	private Object obj;
	List<OrderedMenuDto> oList = new ArrayList<OrderedMenuDto>();
	
	public OrderDao() {
	}
	
	
	public void insert() {
		
	}
	
	public List<OrderedMenuDto> select(Object obj) {
		this.obj = obj;
		
		// reader
		oList = (ArrayList<OrderedMenuDto>)obj;
		
		
		return oList;
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
