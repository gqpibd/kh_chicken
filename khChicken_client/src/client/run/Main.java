package client.run;

import client.singleton.Singleton;
import client.view.Window_Account;
import client.view.Window_Review;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance(); 
		s.getComm().makeConnection(); // 소켓 연결을 먼저 한다.
		s.getMainView();
		//new Window_Account();
		new Window_Review("후라이드 치킨");
	}

}
