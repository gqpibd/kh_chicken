package client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import client.dao.OrderDao;
import client.view.manager.SaleManageView;
import dto.OrderedMenuDto;

public class OrderController {
	
	private OrderDao ordDao = new OrderDao();	
	
	private SaleManageView salManView; // 매출관리창
	
	public void insert() {
		ordDao.insert();
	}
	
	public ArrayList<Object> select(int number) {
		return ordDao.select(number);
	}
	
	public void delete() {
		ordDao.delete();
	}
	
	public void update() {
		ordDao.update();
	}
	
	public void saleManageView(JFrame currentView) {
		currentView.setVisible(false);
		// 매출관리 창을 보여준다.
		if (salManView == null) { // 없을 땐
			salManView = new SaleManageView(); // 만들고
		} else { // 있을 땐
			salManView.setVisible(true); // 보여만 준다.
		}
	}

}
