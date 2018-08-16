package client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.service.OrderService;
import client.service.interfaces.OrderServiceImpl;
import client.singleton.Singleton;
import client.view.MyInfoView;
import client.view.OrderView;
import dto.OrderedMenuDto;

public class OrderController {

	private OrderServiceImpl ordDao = new OrderService();

	private OrderView ordView;
	private MyInfoView Window_inform;

	public void insert(ArrayList<OrderedMenuDto> confirmedList) { // 주문 내역을 전송한다.
		ordDao.insert(confirmedList);
		JOptionPane.showMessageDialog(null, "주문이 완료되었습니다");
		Singleton.getInstance().backToMain(ordView);
	}

	public void clearList() {
		ordDao.clearList();
	}

	public int getCoupons() {
		return ordDao.getCoupon();
	}	

	public List<OrderedMenuDto> getList() {
		return ordDao.getList();
	}
	

	public void OrderView(JFrame currentView) {
		currentView.setVisible(false);	// 주문 창을 보여준다.
		ordView = new OrderView(); 
	}

	public void InFormationview(JFrame currentView) {
		currentView.setVisible(false);
		if (Window_inform == null) {
			Window_inform = new MyInfoView();
		} else {
			Window_inform.setVisible(true);
		}
	}
}
