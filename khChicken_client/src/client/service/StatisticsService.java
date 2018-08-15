package client.service;

import java.util.ArrayList;
import java.util.List;

import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class StatisticsService implements StatisticsServiceInter {

	public StatisticsService() {
	}

	public ArrayList<?> select(int number) {
		ArrayList<?> list = new ArrayList<>();
		Singleton s = Singleton.getInstance();

		// number번 실행하라! 시그널 보내
		s.getComm().SendMessage(number, new String());
		// db 결과 받아오기
		list = (ArrayList<?>) s.getComm().receiveObject();
		return list;
	}

}
