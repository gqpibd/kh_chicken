package client.run;

import client.singleton.Singleton;
import client.view.mainView;

public class Main {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		//s.getMenuCtrl().getMenDao().initList();
		new mainView();
		//s.getComm().SendMessage(3, new MenuShowDto("후라이드 치킨", 11000,"fried.jpg", 9.0));
		//new MenuManagementView();
		
	}

}
