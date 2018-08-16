package client.run;

import client.singleton.Singleton;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance(); 
		s.getComm().makeConnection(); // 소켓 연결을 먼저 한다.
		s.getMainView().setVisible(true); // 메인화면을 띄워준다.		
	}

}
