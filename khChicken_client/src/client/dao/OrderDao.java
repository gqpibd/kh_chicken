package client.dao;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dto.OrderedMenuDto;
import client.singleton.Singleton;

public class OrderDao {
	
	ArrayList<Object> orderList = new ArrayList<Object>();
	
	
	public OrderDao() {
	}
	
	
	public void insert() {
		
	}
	
	public ArrayList<Object> select() {
		Singleton s = Singleton.getInstance();
		s.getComm().makeConnection();
		
		OrderedMenuDto dto = new OrderedMenuDto();
		
		// 리스트 초기화
		orderList.clear();
		
		// 6번 실행하라! 시그널 보내
		s.getComm().SendMessage(6, dto);
		// db 결과 받아오기
		ArrayList<Object> resultList = s.getComm().receiveMessage();
		
		/*for (int i = 0; i < resultList.size(); i++) {
			// 실시간으로 받은 dto를 리스트에 저장
			orderList.add((OrderedMenuDto)resultList.get(i));
			// 받은 값 확인용
			System.out.println(resultList.get(i));
		}*/
		
		return resultList;
		
		
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
