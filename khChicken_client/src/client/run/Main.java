package client.run;

import client.singleton.Singleton;
import dto.MenuDto;
import dto.MenuShowDto;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		s.getComm().makeConnection();
		s.getComm().SendMessage(3, new MenuShowDto("후라이드 치킨", 11000,"fried.jpg", 9.0));
		
	}

}
