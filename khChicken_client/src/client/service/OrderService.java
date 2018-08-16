package client.service;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.service.interfaces.OrderServiceImpl;
import client.singleton.Singleton;
import dto.MemberDto;
import dto.OrderedMenuDto;

public class OrderService implements OrderServiceImpl {

	List<OrderedMenuDto> orderList = new ArrayList<>();

	public OrderService() {
	}

	public void insert(ArrayList<OrderedMenuDto> confirmedList) { // 주문하기

		Singleton s = Singleton.getInstance();
		for (int i = 0; i < confirmedList.size(); i++) {
			s.getComm().SendMessage(Communicator.INSERT, confirmedList.get(i));
		}

		// 리스트 초기화
		orderList.clear(); // 주문이 끝났으니 리스트를 비워준다.
	}

	public int getCoupon() { // 사용할수 있는 쿠폰 (writer 번호, 자신의 id)
		Singleton s = Singleton.getInstance();

		// id 보내기
		MemberDto mDto = s.getMemCtrl().getCurrentUser();
		if (mDto == null) { // 로그인상태가 아니면 쿠폰 0
			return 0;
		} else {
			OrderedMenuDto oDto = new OrderedMenuDto(null, mDto.getId(), null, null, 0, 0, 0);
			s.getComm().SendMessage(Communicator.SELECT, oDto); // 리뷰 작성한 개수에 대한 쿠폰 수를 받아온다.

			// 다시 받아오기
			int couponEA = Integer.parseInt(s.getComm().receiveObject().toString());
			couponEA++; // 회원이면 기보적으로 1개의 쿠폰이 제공된다.
			couponEA -= mDto.getCoupon(); // 내가 받은 총 쿠폰에서 사용한 쿠폰만큼 빼준다.

			return couponEA;
		}
	}

	public List<OrderedMenuDto> getList() {
		return orderList;
	}

	public void clearList() {
		orderList.clear();

	}

}
