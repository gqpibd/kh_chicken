package client.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.dao.OrderDao;
import client.singleton.Singleton;
import client.view.OrderView;
import client.view.Window_information;
import client.view.manager.SaleManageView;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class OrderController {

	private OrderDao ordDao = new OrderDao();

	private SaleManageView salManView; // 매출관리창
	private OrderView ordView;
	private Window_information Window_inform;

	public void insert(ArrayList<OrderedMenuDto> confirmedList) { // 주문 내역을 전송한다.
		ordDao.insert(confirmedList);
		JOptionPane.showMessageDialog(null, "주문이 완료되었습니다");
		Singleton.getInstance().backToMain(ordView);
	}

	
	public ArrayList<OrderedMenuDto> selectByDate(int number) {

		return ordDao.selectByDate(number);

	}

	public ArrayList<BestSaleMenuDto> selectBySales(int number) {

		return ordDao.selectBySales(number);
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

	public List<OrderedMenuDto> getList() {
		return ordDao.getList();
	}

	public void OrderView(JFrame currentView) {
		currentView.setVisible(false);
		// 주문 창을 보여준다.
		if (ordView == null) { // 없을 땐
			ordView = new OrderView(); // 만들고
		} else { // 있을 땐
			ordView.setVisible(true); // 보여만 준다.
		}
	}
	
	public void InFormationview(JFrame currentView) {
		currentView.setVisible(false);
		if(Window_inform == null) {
			Window_inform = new Window_information();
		} else {
			Window_inform.setVisible(true);
		}
	}

}
