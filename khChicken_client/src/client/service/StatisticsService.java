package client.service;

import java.util.ArrayList;
import java.util.List;

import client.service.interfaces.StatisticsServiceImpl;
import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class StatisticsService implements StatisticsServiceImpl {

	public StatisticsService() {
	}

	public ArrayList<?> select(int number) {
		ArrayList<?> list = new ArrayList<>();
		Singleton s = Singleton.getInstance();

		s.getComm().SendMessage(number, new String());
		list = (ArrayList<?>) s.getComm().receiveObject();
		return list;
	}

}
