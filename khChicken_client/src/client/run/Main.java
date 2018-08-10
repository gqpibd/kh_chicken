package client.run;

import client.singleton.Singleton;
import client.view.mainView;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance(); 
		s.getComm().makeConnection(); // 소켓 연결을 먼저 한다.
		new mainView();
		
	}

}
