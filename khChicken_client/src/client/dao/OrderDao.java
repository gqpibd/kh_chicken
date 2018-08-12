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

	public ArrayList<OrderedMenuDto> selectByDate(int number) { // 4번인경우
		ArrayList<OrderedMenuDto> list = new ArrayList<>();
		Singleton s = Singleton.getInstance();

		// 날짜별 매출
		// number번 실행하라! 시그널 보내
		s.getComm().SendMessage(number, new OrderedMenuDto());
		// db 결과 받아오기
		ArrayList<Object> resultList = s.getComm().receiveMessage();
		for (int i = 0; i < resultList.size(); i++) {

			// 받은 dto 형식에 맞게 변환해 저장
			list.add((OrderedMenuDto) resultList.get(i));
			// 받은 값 확인용
			System.out.println(resultList.get(i));
		}

		return list;
	}

	public ArrayList<BestSaleMenuDto> selectBySales(int number) { // 5번인경우
		ArrayList<BestSaleMenuDto> list = new ArrayList<>();
		Singleton s = Singleton.getInstance();

		// 판매총액 별 매출
		s.getComm().SendMessage(number, new OrderedMenuDto()); // 서버가 어떤 명령인지 확인하기 위한 것
		ArrayList<Object> resultList = s.getComm().receiveMessage();
		for (int i = 0; i < resultList.size(); i++) {
			// 받은 dto 형식에 맞게 변환해 저장
			list.add((BestSaleMenuDto) resultList.get(i));
			// 받은 값 확인용
			System.out.println(resultList.get(i));
		}
		return list;

	}

	public void update() {

	}

	public void delete() {

	}

	public List<OrderedMenuDto> getList() {
		return orderList;
	}

}
