package client.service;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.OrderedMenuDto;

public class OrderService  implements OrderServiceInter {

	List<OrderedMenuDto> orderList = new ArrayList<>();

	public OrderService() {
	}

	public void insert(ArrayList<OrderedMenuDto> confirmedList) {

		Singleton s = Singleton.getInstance();
		for (int i = 0; i < confirmedList.size(); i++) {
			s.getComm().SendMessage(Communicator.INSERT, confirmedList.get(i));
		}

		// 리스트 초기화
		orderList.clear(); // 주문이 끝났으니 리스트를 비워준다.
	}

	public String getCoupon() { // 사용할수 있는 쿠폰 (writer 번호, 자신의 id)
		ObjectOutputStream oos = null;
		Singleton s = Singleton.getInstance();

		// id 보내기
		String id = s.getMemCtrl().getLoginId();
		OrderedMenuDto oDto = new OrderedMenuDto(null, id, null, null, 0, 0, 0);
		s.getComm().SendMessage(Communicator.SELECT, oDto);

		// 다시 받아오기
		String couponEA = (String) s.getComm().receiveObject();
		System.out.println("message : " + couponEA);
		return couponEA;
	}

	public List<OrderedMenuDto> getList() {
		return orderList;
	}

	public void clearList() {
		orderList.clear();

	}

}
