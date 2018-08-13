package client.dao;

import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.CustomerManageDto;
import dto.OrderedMenuDto;

public class CustomerManageDao {
	
	List<CustomerManageDto> cusList = new ArrayList<>();

	public CustomerManageDao() {
	}


	public ArrayList<CustomerManageDto> customerOrder(int number) { // 4번인경우(기본. 날짜별 정렬 list받아오기)
		ArrayList<CustomerManageDto> list = new ArrayList<>();
		Singleton s = Singleton.getInstance();

		// 고객별 주문량 내역
		s.getComm().SendMessage(number, new CustomerManageDto());
		// db 결과 받아오기
		ArrayList<Object> resultList = s.getComm().receiveMessage();
		for (int i = 0; i < resultList.size(); i++) {

			// 받은 dto 형식에 맞게 변환해 저장
			list.add((CustomerManageDto) resultList.get(i));
			// 받은 값 확인용
			System.out.println(resultList.get(i));
		}

		return list;
	}


	public void update() {

	}

	public void delete() {

	}


}
