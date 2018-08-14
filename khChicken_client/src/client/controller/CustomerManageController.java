package client.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import client.dao.CustomerManageDao;
import client.view.manager.CustomerManageView;
import client.view.manager.SaleManageView;
import dto.CustomerManageDto;

public class CustomerManageController {
	
	private CustomerManageDao cusDao = new CustomerManageDao();
	private CustomerManageView cusManView;
	
	public ArrayList<CustomerManageDto> customerOrder() {
		return cusDao.customerOrder();
	}
	
	public void CustomerManageView(JFrame currentView) {
		currentView.setVisible(false);
		// 고객관리 창을 보여준다.
		if (cusManView == null) {
			cusManView = new CustomerManageView();
		} else {
			cusManView.setVisible(true);
		}
	}

}
