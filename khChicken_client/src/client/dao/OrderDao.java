package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class OrderDao {

	List<OrderedMenuDto> orderList = new ArrayList<>();

	public OrderDao() {
	}

	public void insert(ArrayList<OrderedMenuDto> confirmedList) {

		Singleton s = Singleton.getInstance();
		for (int i = 0; i < confirmedList.size(); i++) {
			s.getComm().SendMessage(Communicator.INSERT, confirmedList.get(i));
		}

		// 리스트 초기화
		orderList.clear(); // 주문이 끝났으니 리스트를 비워준다.
	}

	public void update() {

	}

	public void delete() {

	}

	public List<OrderedMenuDto> getList() {
		return orderList;
	}

}
