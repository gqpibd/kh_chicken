package client.controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import client.service.StatisticsService;
import client.view.manager.CustomerManageView;
import client.view.manager.SaleManageView;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class StatisticsController {
	public static final int DATE = 0;
	public static final int SALES = 1;
	public static final int SCORE = 2;
	public static final int CUSTOMER = 3;
	public static final int ADDRESS = 4; // 주소검색

	private StatisticsService manDao = new StatisticsService();
	private CustomerManageView cusManView; // 회원관리창
	private SaleManageView salManView; // 매출관리창
	
	public void CustomerManageView(JFrame currentView) {
		currentView.setVisible(false);
		// 고객관리 창을 보여준다.
		if (cusManView == null) {
			cusManView = new CustomerManageView();
		} else {
			cusManView.setVisible(true);
		}
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

	public ArrayList<?> getStatistics(int number) { // 서버에서 조회할 데이터를 받아온다.
		return manDao.select(number);
	}

}
