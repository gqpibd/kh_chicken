package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;
import client.singleton.Singleton;

public class OrderDao {
	
	ArrayList<Object> orderList = new ArrayList<Object>();
	
	
	public OrderDao() {
	}
	
	
	public void insert() {
		
	}
	
	public ArrayList<Object> select(int number) {
		Singleton s = Singleton.getInstance();
		//s.getComm().makeConnection();
		
		//OrderedMenuDto dto = new OrderedMenuDto();
		
		// 리스트 초기화
		orderList.clear();
		
		// 6번 실행하라! 시그널 보내
		s.getComm().SendMessage(number, null);
		// db 결과 받아오기
		ArrayList<Object> resultList = s.getComm().receiveMessage();
		
		// 날짜별 매출
		if(number== 6) {
			for (int i = 0; i < resultList.size(); i++) {
				// 받은 dto 형식에 맞게 변환해 저장
				orderList.add((OrderedMenuDto)resultList.get(i));
				// 받은 값 확인용
				System.out.println(resultList.get(i));
			}
		}
		// 판매총액 별 매출
		else if(number== 7) {
			for (int i = 0; i < resultList.size(); i++) {
				// 받은 dto 형식에 맞게 변환해 저장
				orderList.add((BestSaleMenuDto)resultList.get(i));
				// 받은 값 확인용
				System.out.println(resultList.get(i));
			}
		}
		
		return orderList;
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
